package com.example.admin_umkm_sambongrejo.activity.form;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin_umkm_sambongrejo.R;
import com.example.admin_umkm_sambongrejo.models.ProductModel;
import com.example.admin_umkm_sambongrejo.rest_api.EndPoint;
import com.example.admin_umkm_sambongrejo.rest_api.controller.ProductController;

public class FormInputEditActivity extends AppCompatActivity {
    private ImageView img_thumb;
    private EditText txt_name;
    private EditText txt_desc;
    private EditText txt_price;
    private Button btnSave;
    String part_image;
    private TextView titleForm;
    private TextView titleImage;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_input_edit);
        initForm();

        // select image
        selectImage();
        // save product
        onSaveData();
    }

    private void initForm(){
        img_thumb = findViewById(R.id.img_thumb);
        txt_name = findViewById(R.id.eName_product);
        txt_desc = findViewById(R.id.eDescription);
        txt_price = findViewById(R.id.ePrice);
        btnSave = findViewById(R.id.btnSave);
        titleForm = findViewById(R.id.title_form);
        titleImage = findViewById(R.id.title_img);
        //
        intent = getIntent();
        int isEdit = intent.getIntExtra("isEdit",-1);
        ProductModel model = intent.getParcelableExtra("product");
       if(intent.hasExtra("isEdit")){
           if(isEdit == 0){
               titleForm.setText(R.string.save_new_product);
               titleImage.setText(R.string.image_product);
           }else if(isEdit == 1){
               titleImage.setText("Image Product (Opsional)");
               titleForm.setText(R.string.edit_product);
               txt_name.setText(model.getName());
               txt_price.setText(model.getPrice());
               txt_desc.setText(model.getDescription());
               Glide.with(this).load(EndPoint.IMG_URL+model.getPhoto()).into(img_thumb);
           }else{
               titleForm.setText(R.string.save_new_product);
           }
       }
    }
    private void selectImage(){
        ActivityCompat.requestPermissions(FormInputEditActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
        img_thumb.setOnClickListener(view -> {
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, 1);
        });
    }

    // show image in imageview after select image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            part_image = picturePath;
            img_thumb.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
            } else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Toast.makeText(FormInputEditActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Boolean validateForm(){
        if(txt_name.length() == 0){
            txt_name.setError("This field is required");
            return false;

        }
        if(txt_desc.length() == 0){
            txt_desc.setError("This field is required");
            return false;
        }
        if(txt_price.length() == 0){
            txt_price.setError("This field is required");
            return false;
        }
        return true;
    }

    private void onSaveData(){
        ProductController productController = new ProductController();
        intent = getIntent();
        int isEdit = intent.getIntExtra("isEdit",-1);
        ProductModel model = intent.getParcelableExtra("product");

        btnSave.setOnClickListener(view -> {
            validateForm();
            // save product
            String name = txt_name.getText().toString();
            String desc = txt_desc.getText().toString();
            String price = txt_price.getText().toString();
            if(intent.hasExtra("isEdit")){
                if(isEdit == 0){
                    if(part_image == null){
                        Toast.makeText(this,"Select Product Image Please",Toast.LENGTH_LONG).show();
                    }else{
                        productController.saveProduct(FormInputEditActivity.this,FormInputEditActivity.this,name,desc,price,part_image);
                    }
                }else if(isEdit == 1){
                    if(part_image == null){
                        productController.updateProduct(FormInputEditActivity.this,FormInputEditActivity.this,model.getId(),name,desc,price);
                    }else{
                        productController.updateProductWithImage(FormInputEditActivity.this,FormInputEditActivity.this,model.getId(),name,desc,price,part_image);
                    }
                }
            }
        });
    }
}