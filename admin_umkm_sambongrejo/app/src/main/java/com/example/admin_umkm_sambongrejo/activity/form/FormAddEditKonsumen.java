package com.example.admin_umkm_sambongrejo.activity.form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin_umkm_sambongrejo.R;
import com.example.admin_umkm_sambongrejo.models.KonsumenModel;
import com.example.admin_umkm_sambongrejo.rest_api.controller.KonsumenController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormAddEditKonsumen extends AppCompatActivity {
    private EditText txt_name;
    private EditText txt_username;
    private EditText txt_password;
    private EditText txt_address;
    private Spinner txt_gender;
    private Button btnSave;
    private Intent intent;
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_edit_konsumen);
        initForm();
        //
        onSaveData();
    }

    private void initForm(){
        txt_name = findViewById(R.id.eName_konsumen);
        txt_username = findViewById(R.id.eUsername_konsumen);
        txt_password = findViewById(R.id.etPassword_konsumen);
        txt_address = findViewById(R.id.eAddress_konsumen);
        txt_gender = findViewById(R.id.gender_select);
        btnSave = findViewById(R.id.btnSaveKonsumen);
        tv_title = findViewById(R.id.title_form_konsumen);
        //
        intent = getIntent();
        int isEdit = intent.getIntExtra("isEdit",-1);
        KonsumenModel konsumenModel = intent.getParcelableExtra("konsumen");
        if(intent.hasExtra("isEdit")){
            if(isEdit == 1) {
                tv_title.setText("Edit Konsumen");
                txt_name.setText(konsumenModel.getName());
                txt_username.setText(konsumenModel.getEmail());
                txt_address.setText(konsumenModel.getAddress());
                String[] gender = new String[]{
                        "Laki-Laki",
                        "Perempuan"
                };
                final List<String> genderList = new ArrayList<>(Arrays.asList(gender));

                // Initializing an ArrayAdapter
                final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                        this,R.layout.spinner_item,genderList);
                spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                txt_gender.setAdapter(spinnerArrayAdapter);
                if(konsumenModel.getGender().equalsIgnoreCase("Laki-Laki")){
                    txt_gender.setSelection(0);
                }else{
                    txt_gender.setSelection(1);
                }
            }else if(isEdit == 0){
                tv_title.setText(R.string.save_new_konsumen);
            }else{
                tv_title.setText(R.string.save_new_konsumen);
            }
        }
    }

    private Boolean validateForm(){
        if(txt_name.length() == 0){
            txt_name.setError("This field is required");
            return false;

        }
        if(txt_address.length() == 0){
            txt_address.setError("This field is required");
            return false;
        }
        if(txt_password.length() == 0){
            txt_password.setError("This field is required");
            return false;
        }
        if(txt_username.length() == 0){
            txt_username.setError("This field is required");
            return false;
        }
        if(txt_gender.getSelectedItem().toString().equalsIgnoreCase("Select Gender")){
            Toast.makeText(this,"Please select your gender",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void onSaveData(){
        intent = getIntent();
        int isEdit = intent.getIntExtra("isEdit",-1);
        KonsumenModel konsumenModel = intent.getParcelableExtra("konsumen");
        KonsumenController konsumenController = new KonsumenController();
        btnSave.setOnClickListener(view -> {
            validateForm();
            //
            if(!txt_gender.getSelectedItem().toString().equalsIgnoreCase("Select Gender")){
                String name = txt_name.getText().toString();
                String username = txt_username.getText().toString();
                String password = txt_password.getText().toString();
                String address = txt_address.getText().toString();
                String gender = txt_gender.getSelectedItem().toString();
                if(intent.hasExtra("isEdit")){
                    if(isEdit == 1){
                        konsumenController.updateKonsumen(FormAddEditKonsumen.this,FormAddEditKonsumen.this,konsumenModel.getId(),name,username,password,address,gender);
                    }else{
                        konsumenController.saveKonsumen(FormAddEditKonsumen.this,FormAddEditKonsumen.this,name,username,password,address,gender);
                    }
                }
            }

        });
    }
}