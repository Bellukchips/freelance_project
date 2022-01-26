package com.example.admin_umkm_sambongrejo.activity.report;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin_umkm_sambongrejo.R;
import com.example.admin_umkm_sambongrejo.rest_api.EndPoint;
import com.example.admin_umkm_sambongrejo.rest_api.controller.TransaksiController;
import com.example.admin_umkm_sambongrejo.utils.Helper;

public class ReportGlobal extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_global);
        new Helper().setTitleAppBar(this,"Report Global");

        recyclerView = findViewById(R.id.rc_report_global);
        getData();
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
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(EndPoint.URL+"report/report_all_transaksi.php"));
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}