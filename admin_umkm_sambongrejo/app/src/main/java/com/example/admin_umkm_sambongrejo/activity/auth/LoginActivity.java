package com.example.admin_umkm_sambongrejo.activity.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin_umkm_sambongrejo.R;
import com.example.admin_umkm_sambongrejo.activity.HomeActivity;
import com.example.admin_umkm_sambongrejo.rest_api.controller.UserController;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText txt_username;
    private EditText txt_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // find id
        txt_password= findViewById(R.id.etPassword);
        txt_username = findViewById(R.id.etUsername);
        btnLogin = findViewById(R.id.btMasuk);
        //
        onLogin();
    }
    private Boolean validateForm(){
        if(txt_username.length() == 0){
            txt_username.setError("This field is required");
            return false;

        }
        if(txt_password.length() == 0){
            txt_username.setError("This field is required");
            return false;
        }
        return true;
    }
    private void onLogin(){
        UserController userController = new UserController();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateForm();
                userController.loginUser(LoginActivity.this,LoginActivity.this,txt_username.getText().toString(),txt_password.getText().toString());
            }
        });
    }

}