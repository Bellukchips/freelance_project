package id.nesd.umkmdesasambongrejo.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.nesd.umkmdesasambongrejo.R;
import id.nesd.umkmdesasambongrejo.data.SPData;
import id.nesd.umkmdesasambongrejo.rest_api.controller.KonsumenController;
import id.nesd.umkmdesasambongrejo.tool.Helper;

public class RegisterActivity extends AppCompatActivity {


    private EditText etNama;
    private EditText etUsername;
    private EditText etPassword;
    private Button btRegis;
    private Spinner txt_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        new Helper().hideAppBar(this);

        etNama = findViewById(R.id.etNama);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btRegis = findViewById(R.id.btRegis);
        txt_gender = findViewById(R.id.gender_select);
        Button btReset = findViewById(R.id.btReset);

        btRegis.setOnClickListener(v -> {
            String nama = etNama.getText().toString().trim();
            String email = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String gender = txt_gender.getSelectedItem().toString();

            if (nama.isEmpty()) {
                etNama.setError("Kolom Wajib diisi!");
            } else if (email.isEmpty()) {
                etUsername.setError("Kolom Wajib diisi!");
            } else if (password.isEmpty()) {
                etPassword.setError("Kolom Wajib diisi!");
            } else if(txt_gender.getSelectedItem().toString().equalsIgnoreCase("Select Gender")){
                Toast.makeText(this,"Please select your gender",Toast.LENGTH_LONG).show();
            }else {
                daftar(nama,  email, password,gender);
            }
        });

        btReset.setOnClickListener(view -> {
            etNama.setText("");
            etUsername.setText("");
            etPassword.setText("");
        });
    }

    @SuppressLint("SetTextI18n")
    private void daftar(String nama, String email, String password,String gender) {
        KonsumenController konsumenController = new KonsumenController();
        konsumenController.saveKonsumen(this,this,nama,email,password,gender);
    }
}