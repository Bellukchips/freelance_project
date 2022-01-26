package com.example.admin_umkm_sambongrejo.activity.report;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.content.Intent;

import android.net.Uri;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;


import com.example.admin_umkm_sambongrejo.R;

import com.example.admin_umkm_sambongrejo.rest_api.EndPoint;
import com.example.admin_umkm_sambongrejo.rest_api.controller.TransaksiController;

import com.example.admin_umkm_sambongrejo.utils.Helper;


import java.text.DateFormat;

import java.util.Date;


public class ReportPeriodicActivity extends AppCompatActivity {



    //
    private RecyclerView recyclerView;
    private Button btnSearch;
    private EditText txt_year;
    Date dateTime;
    DateFormat dateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_periodic);
        new Helper().setTitleAppBar(this,"Report Periodik");
        recyclerView = findViewById(R.id.rc_report_periodic);
        btnSearch = findViewById(R.id.btnSearch);
        txt_year = findViewById(R.id.etYear);
        getData();
        searchData();

    }

    private  Boolean validate(){
        if(txt_year.length() == 0){
            txt_year.setError("This field is required ");
            return false;
        }
        return true;
    }
    private void searchData(){
        btnSearch.setOnClickListener(view -> {
            validate();
            getDataByYear(txt_year.getText().toString());
        });
    }
    private void getDataByYear(String year){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        TransaksiController transaksiController = new TransaksiController();
        transaksiController.getTransactionByYear(this,recyclerView,year);
    }
    private void getData(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        TransaksiController transaksiController = new TransaksiController();
        transaksiController.getAllTransaksi(this,recyclerView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cetak, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if(id == R.id.cetak){
            createPdf();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressLint("SimpleDateFormat")
    private void createPdf(){
        if(txt_year.length() == 0){
            txt_year.setError("This field is required ");
        }else {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(EndPoint.URL+"report/report_periodic.php/?year="+txt_year.getText().toString().trim()));
            startActivity(intent);
        }
    }

}