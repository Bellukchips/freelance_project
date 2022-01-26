package id.nesd.umkmdesasambongrejo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import id.nesd.umkmdesasambongrejo.R;
import id.nesd.umkmdesasambongrejo.rest_api.controller.KonsumenController;
import id.nesd.umkmdesasambongrejo.tool.Helper;
import id.nesd.umkmdesasambongrejo.tool.PreferenceManager;

public class AkunActivity extends AppCompatActivity {
    private EditText etNama;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etAddress;
    private Spinner etGender;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);
        new Helper().setTitleAppBar(this, "Akun");

        etNama = findViewById(R.id.etNama);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etAddress = findViewById(R.id.etAddress);
        etGender = findViewById(R.id.gender_select);
        Button btUpdate = findViewById(R.id.btUpdate);
        Button btCancel = findViewById(R.id.btCancel);
        Button btLogout = findViewById(R.id.btLogout);

        //
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        PreferenceManager preferenceManager = new PreferenceManager("LOGINPREFERENCE",this);
        btUpdate.setOnClickListener(v -> {
            String nama = etNama.getText().toString().trim();
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String gender = etGender.getSelectedItem().toString();

            if (nama.isEmpty()) {
                etNama.setError("Kolom Wajib diisi!");
            } else if (username.isEmpty()) {
                etUsername.setError("Kolom Wajib diisi!");
            } else if (password.isEmpty()) {
                etPassword.setError("Kolom Wajib diisi!");
            } else if(address.isEmpty()){
                etAddress.setError("Kolom Wajib diisi!");
            }else {
                update(preferenceManager,preferenceManager.getPreference("id"),nama,username,password,gender,address);
            }
        });
        //

        btCancel.setOnClickListener(v -> finish());
        btLogout.setOnClickListener(v -> {
            preferenceManager.setPreference("login","0");
            preferenceManager.setPreference("name","");
            preferenceManager.setPreference("address","");
            preferenceManager.setPreference("gender","");
            preferenceManager.setPreference("email","");
            preferenceManager.setPreference("id_user","");
            if(preferenceManager.getPreference("is_google").equalsIgnoreCase("1")){
                mGoogleSignInClient.signOut().addOnCompleteListener(this,task->{
                    Toast.makeText(this,"Sign Out google",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                    FirebaseAuth.getInstance().signOut();
                });
            }else{
                Toast.makeText(this,"Sign Out",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }

        });


        dataUser(preferenceManager);
    }

    private void dataUser(PreferenceManager preferenceManager){
        etNama.setText(preferenceManager.getPreference("name"));
        etAddress.setText(preferenceManager.getPreference("address"));
        etUsername.setText(preferenceManager.getPreference("username"));
        String[] gender = new String[]{
                "Laki-Laki",
                "Perempuan"
        };
        final List<String> genderList = new ArrayList<>(Arrays.asList(gender));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,genderList);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        etGender.setAdapter(spinnerArrayAdapter);
        if(preferenceManager.getPreference("gender").equalsIgnoreCase("Laki-Laki")){
            etGender.setSelection(0);
        }else{
            etGender.setSelection(1);
        }
    }

    private void update(PreferenceManager preferenceManager,String id, String nama, String username, String password,String gender,String address) {
        KonsumenController konsumenController = new KonsumenController();
        konsumenController.updateKonsumen(this,this,id,nama,username,password,address,gender);
        preferenceManager.setPreference("name",nama);
        preferenceManager.setPreference("address",address);
        preferenceManager.setPreference("gender",gender);
        preferenceManager.setPreference("username",username);
        Toast.makeText(this, "Berhasil Update Data!", Toast.LENGTH_SHORT).show();
    }
}