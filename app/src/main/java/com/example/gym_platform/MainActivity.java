package com.example.gym_platform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //로그인 모듈 변수
    private FirebaseAuth mAuth;
    //현재 로그인 된 유저 정보 담을 변수
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Log.d("a","MainActivity 실행//1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart(){
        //Log.d("a","MainActivity 실행//2");
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            //Log.d("a","MainActivity 실행//3");
            startActivity(new Intent(MainActivity.this, LoginActivity.class));

            // finish();
        }
        // if(currentUser!=null) {
        //     TextView TextView_main = findViewById(R.id.TextView_main);
        //    TextView_main.setText(currentUser.getEmail());
        //    Log.d("a",currentUser.getEmail());
        // }
    }
}
