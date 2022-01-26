package com.example.admin_umkm_sambongrejo.activity.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.admin_umkm_sambongrejo.R;
import com.example.admin_umkm_sambongrejo.activity.HomeActivity;
import com.example.admin_umkm_sambongrejo.activity.auth.LoginActivity;
import com.example.admin_umkm_sambongrejo.utils.Helper;
import com.example.admin_umkm_sambongrejo.utils.PreferenceManager;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Helper().hideAppBar(this);
        PreferenceManager preferenceManager = new PreferenceManager("LOGINPREFERENCE",this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(preferenceManager.getPreference("login").equals("1")){
                    Intent login = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(login);
                    finish();
                    overridePendingTransition(0, 0);
                    getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                }else{
                    preferenceManager.setPreference("login","0");
                    Intent login = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(login);
                    finish();
                    overridePendingTransition(0, 0);
                    getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                }

            }
        },1000);
    }
}