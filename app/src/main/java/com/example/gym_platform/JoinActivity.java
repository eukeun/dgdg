package com.example.gym_platform;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class JoinActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        //Log.d("a","JoinActivity 실행");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mAuth = FirebaseAuth.getInstance();

        final EditText EditText_eamil = (EditText)findViewById(R.id.EditText_Emailsubmit);
        final EditText EditText_password = (EditText)findViewById(R.id.EditText_Passwordsubmit);
        final EditText EditText_name = (EditText)findViewById(R.id.EditText_Namesubmit);
        Button ButtonJoinsubmit = (Button)findViewById(R.id.Button_Joinsubmit);

        ButtonJoinsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EditText_eamil.getText().toString();
                String password = EditText_password.getText().toString();
                String name = EditText_name.getText().toString();

                JoinStart(email,password,name);
            }
        });
    }

    public void JoinStart(String email, String password, final String name){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful()) {
                            try {
                                throw task.getException();
                            } catch(FirebaseAuthWeakPasswordException e) {
                                Toast.makeText(JoinActivity.this,"비밀번호가 간단해요.." ,Toast.LENGTH_SHORT).show();
                            } catch(FirebaseAuthInvalidCredentialsException e) {
                                Toast.makeText(JoinActivity.this,"email 형식에 맞지 않습니다." ,Toast.LENGTH_SHORT).show();
                            } catch(FirebaseAuthUserCollisionException e) {
                                Toast.makeText(JoinActivity.this,"이미존재하는 email 입니다." ,Toast.LENGTH_SHORT).show();
                            } catch(Exception e) {
                                Toast.makeText(JoinActivity.this,"다시 확인해주세요.." ,Toast.LENGTH_SHORT).show();
                            }
                        }else{

                            currentUser = mAuth.getCurrentUser();

                            Toast.makeText(JoinActivity.this, "가입 성공  " + name + currentUser.getEmail() + "/" + currentUser.getUid() ,Toast.LENGTH_SHORT).show();
                            addDocument(name);
                            startActivity(new Intent(JoinActivity.this, NavigationActivity.class));
                            finish();
                        }
                    }
                });
    }

    public void addDocument(String name) {
        currentUser = mAuth.getCurrentUser();
        Map<String, Object> User = new HashMap<>();
        User.put("userName", name);
        User.put("userEmail", currentUser.getEmail());
        User.put("userPoint", 0);
        User.put("userUID", currentUser.getUid());

        db.collection("User").document(currentUser.getUid())
                .set(User)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("a", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("a", "Error writing document", e);
                    }
                });
    }
}
