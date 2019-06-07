package com.example.gym_platform;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HealthpayActivity6 extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthpay_mon6);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        final TextView Text_start = (TextView)findViewById(R.id.start);
        final TextView Text_end = (TextView)findViewById(R.id.end);
        final TextView price   = (TextView)findViewById(R.id.price);


        // 예외처리용) 포인트가 부족할 시 보여주는 문구.
        toast = Toast.makeText(this,"보유하고 계신 포인트가 부족합니다.",Toast.LENGTH_SHORT);



        Button btnPayReserve = (Button) findViewById(R.id.botton_mon6);
        btnPayReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UID = currentUser.getUid();
                Date Ustart = new Date();
                Calendar cal = Calendar.getInstance();
                cal.add(cal.MONTH,6);
                Date Uend = cal.getTime();
                String Ukind = "6개월";
                String type = "gym";
                String Price = price.getText().toString();
                String gymName = "451헬스클럽";
                addDocument(UID,Ustart,Uend,Ukind, Price, gymName, type);

            }


        });
    }


    public void addDocument(String UID, Date start, Date end, String kind, String price, String gymName, String type){
        Map<String, Object> user = new HashMap<>();

        user.put("content",kind);
        user.put("end", end);
        user.put("gymName",gymName);
        user.put("price",price);
        user.put("start", start);
        user.put("type",type);
        user.put("userUID",UID);

        // Add a new document with a generated ID
        db.collection("Reservation")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("a","결제 성공 " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("a", "결제 실패", e);
                    }
                });

        UsePoint(953); // 95,300원이므로 953근 삭감.
    }

    public void UsePoint(final int point) {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        DocumentReference docRef = db.collection("User").document(currentUser.getUid());

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document= task.getResult();
                    if (document.exists()) {
                        StringBuilder fields = new StringBuilder("");

                        Map<String, Object> User = new HashMap<>();

                        // 보유하고있는 포인트가 구매하고자 하는 상품의 가격보다 많을 시 (정상수행)
                        if( point < Integer.parseInt(document.get("userPoint").toString())) {
                            User.put("userPoint", Integer.parseInt(document.get("userPoint").toString()) - point);
                            // 포인트 차감


                            db.collection("User").document(currentUser.getUid())
                                    .update(User)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                        }
                                    });
                            Intent intent = new Intent(HealthpayActivity6.this, NavigationActivity.class);
                            startActivity(intent);
                            finish();

                        }else{
                            // 보유하고 있는 포인트가 상품의 가격보다 적을 시 문구 출력.
                            toast.show();
                        }
                    } else {
                    }
                } else {
                }
            }
        });
    }
}