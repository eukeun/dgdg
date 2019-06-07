package com.example.gym_platform;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.Distribution;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PayActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        TextView pay_100 = (TextView)findViewById(R.id.TextView_Point_100);
        TextView pay_250 = (TextView)findViewById(R.id.TextView_Point_250);
        TextView pay_500 = (TextView)findViewById(R.id.TextView_Point_500);
        TextView pay_1000 = (TextView)findViewById(R.id.TextView_Point_1000);
        TextView pay_2500 = (TextView)findViewById(R.id.TextView_Point_2500);
        TextView pay_5000 = (TextView)findViewById(R.id.TextView_Point_5000);

        toast = Toast.makeText(this,"시작이 반이에요",Toast.LENGTH_SHORT);
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.toast);
        toast.setView(imageView);
        toast.setGravity(Gravity.CENTER,0,0);





        pay_100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePoint(100);
                toast.show();
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        Intent intent = new Intent(PayActivity.this, NavigationActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },2000);

            }
        });
        pay_250.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePoint(250);
                toast.show();
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        Intent intent = new Intent(PayActivity.this, NavigationActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },2000);
            }
        });
        pay_500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePoint(500);
                toast.show();
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        Intent intent = new Intent(PayActivity.this, NavigationActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },2000);
            }
        });
        pay_1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePoint(1000);
                toast.show();
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        Intent intent = new Intent(PayActivity.this, NavigationActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },2000);
            }
        });
        pay_2500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePoint(2500);
                toast.show();
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        Intent intent = new Intent(PayActivity.this, NavigationActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },2000);
            }
        });
        pay_5000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePoint(5000);
                toast.show();
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        Intent intent = new Intent(PayActivity.this, NavigationActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },2000);
            }
        });
    }

    public void updatePoint(final int point) {
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
                        User.put("userPoint", point+Integer.parseInt(document.get("userPoint").toString()));
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
                    } else {
                    }
                } else {
                }
            }
        });
    }
}
