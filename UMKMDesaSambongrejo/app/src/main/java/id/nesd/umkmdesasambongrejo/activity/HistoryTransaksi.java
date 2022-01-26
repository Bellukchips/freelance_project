package id.nesd.umkmdesasambongrejo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;

import id.nesd.umkmdesasambongrejo.R;
import id.nesd.umkmdesasambongrejo.rest_api.controller.ProductController;
import id.nesd.umkmdesasambongrejo.rest_api.controller.TransaksiController;
import id.nesd.umkmdesasambongrejo.tool.Helper;
import id.nesd.umkmdesasambongrejo.tool.PreferenceManager;

public class HistoryTransaksi extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_transaksi);
        new Helper().setTitleAppBar(this,"History");
        recyclerView = findViewById(R.id.rc_history);
        refreshLayout = findViewById(R.id.refresh_history);

        setupRecycleView(recyclerView);
        refreshLayout.setColorSchemeResources(R.color.purple_500);
        refreshLayout.setOnRefreshListener(this::refreshContent);
    }

    private void refreshContent(){
        new Handler().postDelayed(() -> {
            setupRecycleView(recyclerView);
            refreshLayout.setRefreshing(false);
        }, 3000);
    }
    private void setupRecycleView(@NonNull RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        PreferenceManager preferenceManager = new PreferenceManager("LOGINPREFERENCE",this);
        TransaksiController transaksiController = new TransaksiController();
        transaksiController.getTransactionByIdUser(this,recyclerView,preferenceManager.getPreference("id_user"));
    }

    @Override
    protected void onResume() {
        setupRecycleView(recyclerView);
        super.onResume();
    }
}