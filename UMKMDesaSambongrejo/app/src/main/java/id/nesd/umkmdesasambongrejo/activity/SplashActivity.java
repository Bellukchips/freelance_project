package id.nesd.umkmdesasambongrejo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import id.nesd.umkmdesasambongrejo.R;
import id.nesd.umkmdesasambongrejo.data.SPData;
import id.nesd.umkmdesasambongrejo.tool.Helper;
import id.nesd.umkmdesasambongrejo.tool.PreferenceManager;

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