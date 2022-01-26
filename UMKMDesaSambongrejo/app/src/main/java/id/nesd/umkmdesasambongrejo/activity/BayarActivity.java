package id.nesd.umkmdesasambongrejo.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import id.nesd.umkmdesasambongrejo.R;
import id.nesd.umkmdesasambongrejo.rest_api.controller.TransaksiController;
import id.nesd.umkmdesasambongrejo.tool.FormatCurrency;
import id.nesd.umkmdesasambongrejo.tool.Helper;
import id.nesd.umkmdesasambongrejo.tool.PreferenceManager;

public class BayarActivity extends AppCompatActivity {
    private EditText etJumlah;
    private EditText etKembali;
    private EditText etTotal;
    private EditText etQty;
    private Spinner jasa_paket;
    private EditText etOngkir;
    private EditText etTujuan;
    private Button btnBayar;
    private Button btnCheck;
    private ImageView img_thumb;
    private String part_image;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);
        new Helper().setTitleAppBar(this, "Pembayaran");
        //
        int total = getIntent().getIntExtra("total", -1);
        int qty = getIntent().getIntExtra("qty",-1);
        String id_product  = getIntent().getStringExtra("id_product");
        String name_product = getIntent().getStringExtra("name_product");
        String price_product = getIntent().getStringExtra("price_product");
        //
        initForm();
        etTotal.setText(String.valueOf(total));
        etQty.setText(String.valueOf(qty));
        etJumlah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String j = etJumlah.getText().toString();
                if(!j.isEmpty()){
                    int j1 = Integer.parseInt(j);
                    etKembali.setText("Rp. " + (j1 -total));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        //
        selectOngkir();
        selectImage();

        bayar(id_product,String.valueOf(total),name_product,price_product);
    }
    private void initForm(){
        //
        etTotal = findViewById(R.id.etTotal);
        etJumlah = findViewById(R.id.etJumlah);
        etKembali = findViewById(R.id.etKembali);
        etQty = findViewById(R.id.etQty);
        jasa_paket = findViewById(R.id.jasa_paket);
        etOngkir = findViewById(R.id.etOngkir);
        btnBayar = findViewById(R.id.btn_bayar);
        btnCheck = findViewById(R.id.btn_check_ongkir);
        etTujuan = findViewById(R.id.etTujuan);
        img_thumb = findViewById(R.id.img_thumb);
        //
    }
    private Boolean validateForm(){
        if(etJumlah.length() == 0){
            etJumlah.setError("The field is required");
            return false;
        }
        if(etTujuan.length() == 0){
            etJumlah.setError("The field is required");
            return false;
        }

        return true;
    }

    private void selectOngkir(){
        btnCheck.setOnClickListener(view -> {
            if(jasa_paket.getSelectedItem().toString().equalsIgnoreCase("OKE")){
                etOngkir.setText("12000");
            }else if(jasa_paket.getSelectedItem().toString().equalsIgnoreCase("YES")){
                etOngkir.setText("40000");
            }else if(jasa_paket.getSelectedItem().toString().equalsIgnoreCase("SS")){
                etOngkir.setText("50000");
            }else if(jasa_paket.getSelectedItem().toString().equalsIgnoreCase("REG")){
                etOngkir.setText("20000");
            }
        });
    }
    private void selectImage(){
        ActivityCompat.requestPermissions(BayarActivity.this,
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
                Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void bayar(String id_product,String total,String name_product,String price_product){
        TransaksiController transaksiController = new TransaksiController();
        PreferenceManager preferenceManager = new PreferenceManager("LOGINPREFERENCE",this);
        btnBayar.setOnClickListener(view -> {
            validateForm();
            //
            if(Integer.parseInt(etJumlah.getText().toString()) < Integer.parseInt(total)){
                Toast.makeText(this,"Input jumlah is error",Toast.LENGTH_LONG).show();
            }else if(part_image == null){
                Toast.makeText(this,"Upload bukti pembayaran kosong",Toast.LENGTH_LONG).show();
            }else{
                transaksiController.saveTransaction(BayarActivity.this,BayarActivity.this,id_product,preferenceManager.getPreference("id_user"),total,etOngkir.getText().toString().trim(),etTujuan.getText().toString().trim(),etQty.getText().toString().trim(),part_image);
                createNota(name_product,price_product);
            }
        });
    }

    private void createNota(String name_product,String price_product){
        PreferenceManager preferenceManager = new PreferenceManager("LOGINPREFERENCE",this);
        PdfDocument document = new PdfDocument();
        Paint paint = new Paint();
        Paint forLinePaint = new Paint();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(250,350,1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        paint.setTextSize(15.5f);
        paint.setColor(Color.rgb(0,50,250));

        canvas.drawText("UMKM Desa Sambongrejo",20,20,paint);
        paint.setTextSize(8.5f);
        canvas.drawText("Nota Pembelian",20,40,paint);
        forLinePaint.setStyle(Paint.Style.STROKE);
        forLinePaint.setPathEffect(new DashPathEffect(new float[]{5,5},0));
        forLinePaint.setStrokeWidth(2);
        canvas.drawLine(20,65,230,65,forLinePaint);
        canvas.drawText("Customer Name :"+preferenceManager.getPreference("name"),20,80,paint);
        canvas.drawLine(20,90,230,90,forLinePaint);

        canvas.drawText("Pembelian :",20,105,paint);
        canvas.drawText(name_product,20,135,paint);
        canvas.drawText(etQty.getText().toString().trim()+"x",120,135,paint);

        paint.setTextAlign(Paint.Align.RIGHT);
        FormatCurrency formatCurrency = new FormatCurrency(Integer.parseInt(price_product));
        canvas.drawText(formatCurrency.getFormat(),230,135,paint);
        paint.setTextAlign(Paint.Align.LEFT);

        canvas.drawLine(20,210,230,210,forLinePaint);
        paint.setTextSize(10f);
        canvas.drawText("Total",120,225,paint);
        FormatCurrency formatCurrency1 = new FormatCurrency(Integer.parseInt(etTotal.getText().toString().trim()));
        canvas.drawText(formatCurrency1.getFormat(),170,225,paint);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(8.5f);

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(12f);

        canvas.drawText("*Terima Kasih*",canvas.getWidth() / 2,260,paint);

        document.finishPage(page);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString(), "nota_"+name_product+".pdf");
        try {

            document.writeTo(new FileOutputStream(file));

            Toast.makeText(BayarActivity.this, "Nota downloaded", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {

            e.printStackTrace();
        }

        document.close();
    }
}