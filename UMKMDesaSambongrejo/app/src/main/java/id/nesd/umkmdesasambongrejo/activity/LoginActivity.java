package id.nesd.umkmdesasambongrejo.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import id.nesd.umkmdesasambongrejo.R;
import id.nesd.umkmdesasambongrejo.rest_api.controller.KonsumenController;
import id.nesd.umkmdesasambongrejo.tool.Helper;
import id.nesd.umkmdesasambongrejo.tool.PreferenceManager;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btMasuk;
    private ImageView btnGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new Helper().hideAppBar(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btMasuk = findViewById(R.id.btMasuk);
        btnGoogle = findViewById(R.id.btnGoogle);
        TextView tvRegis = findViewById(R.id.tvRegis);
        // google sign in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();
        //
        btMasuk.setOnClickListener(v -> {
            String email = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty()) {
                etUsername.setError("Kolom Wajib diisi!");
            } else if (password.isEmpty()) {
                etPassword.setError("Kolom Wajib diisi!");
            } else {
                masuk(email, password);
            }
        });

        tvRegis.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class))
        );


        btnGoogle.setOnClickListener(view->{
            signIn();
        });
    }

    @Override
    protected void onResume() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            Intent home = new Intent(this,HomeActivity.class);
            startActivity(home);
        }
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("GOOGLESIGNIN", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("GOOGLESIGNIN", "Google sign in failed", e);
            }
        }
    }
    // [START auth_with_google]
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("GOOGLESIGNIN", "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        saveUserGoogle(user);
                        Intent home = new Intent(this,HomeActivity.class);
                        startActivity(home);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("GOOGLESIGNIN", "signInWithCredential:failure", task.getException());
                        saveUserGoogle(null);
                    }
                });
    }
    //
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void saveUserGoogle(FirebaseUser user){
        KonsumenController konsumenController = new KonsumenController();
        konsumenController.saveKonsumenGoogle(this,this, user.getDisplayName(), user.getEmail(),null,null);
        PreferenceManager preferenceManager = new PreferenceManager("LOGINPREFERENCE",this);
        preferenceManager.setPreference("is_google","1");
        preferenceManager.setPreference("login","1");
        preferenceManager.setPreference("name",user.getDisplayName());
        preferenceManager.setPreference("address","");
        preferenceManager.setPreference("gender","");
        preferenceManager.setPreference("email",user.getEmail());
        preferenceManager.setPreference("id_user","");

    }
    @SuppressLint("SetTextI18n")
    private void masuk(String email, String password) {
        KonsumenController konsumenController = new KonsumenController();
        konsumenController.loginUser(LoginActivity.this,LoginActivity.this,email,password);
    }
}