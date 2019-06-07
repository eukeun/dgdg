package com.example.gym_platform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget ;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashActivity extends Activity {
    //로그인 모듈 변수
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    //현재 로그인 된 유저 정보 담을 변수
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 2000);


        ImageView image = (ImageView) findViewById(R.id.gif_image);
        DrawableImageViewTarget gifImage = new DrawableImageViewTarget(image);
        Glide.with(this).load(R.drawable.splash).into(gifImage);

        thread_sleep sleep = new thread_sleep(this);

        sleep.start();


    }

    public class thread_sleep extends Thread {
        Activity thisAct;
        thread_sleep(Activity theAct){
            thisAct = theAct;
        }
        public void run(){
            try{
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class splashhandler implements Runnable {
        public void run(){
            currentUser = mAuth.getCurrentUser();
            if(currentUser!=null) {
                startActivity(new Intent(getApplication(), NavigationActivity.class));
                SplashActivity.this.finish();
            }
            else{
                startActivity(new Intent(getApplication(), LoginActivity.class));
                SplashActivity.this.finish();
            }
        }
    }

    public void onBackPressed() {
    }
}
