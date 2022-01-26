package com.example.admin_umkm_sambongrejo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin_umkm_sambongrejo.R;
import com.example.admin_umkm_sambongrejo.activity.auth.LoginActivity;
import com.example.admin_umkm_sambongrejo.activity.fragment.ProductFragment;
import com.example.admin_umkm_sambongrejo.activity.fragment.UserFragment;
import com.example.admin_umkm_sambongrejo.activity.report.ReportGlobal;
import com.example.admin_umkm_sambongrejo.activity.report.ReportPeriodicActivity;
import com.example.admin_umkm_sambongrejo.utils.Helper;
import com.example.admin_umkm_sambongrejo.utils.PreferenceManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigationView;
    ProductFragment productFragment = new ProductFragment();
    UserFragment userFragment = new UserFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        setFragment(productFragment);
        Intent intent = getIntent();
        int isProduct = intent.getIntExtra("isProduct",-1);
        if(intent.hasExtra("isProduct")){
            if(isProduct == 1){
                setFragment(productFragment);
                bottomNavigationView.setSelectedItemId(R.id.product_tab);
            }else{
                setFragment(userFragment);
                bottomNavigationView.setSelectedItemId(R.id.user_tab);
            }
        }
    }
    // bottom nav bar
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.product_tab:
                new Helper().setTitleAppBar(this,"Data Product");
                setFragment(productFragment);
                return true;

            case R.id.user_tab:
                new Helper().setTitleAppBar(this,"Data User");
                setFragment(userFragment);
                return true;
        }
        return false;
    }
    private void setFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment,fragment);
        fragmentTransaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if(id == R.id.report_global){
            Intent report = new Intent(HomeActivity.this, ReportGlobal.class);
            startActivity(report);
            return true;
        }else if(id == R.id.report_periodic){
            Intent report = new Intent(HomeActivity.this, ReportPeriodicActivity.class);
            startActivity(report);
            return true;
        }else if(id == R.id.logout_tab){
            Intent logout = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(logout);
            PreferenceManager preferenceManager = new PreferenceManager("LOGINPREFERENCE",this);
            preferenceManager.setPreference("login","0");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}