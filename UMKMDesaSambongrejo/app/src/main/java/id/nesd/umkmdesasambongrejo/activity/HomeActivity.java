package id.nesd.umkmdesasambongrejo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import id.nesd.umkmdesasambongrejo.R;
import id.nesd.umkmdesasambongrejo.rest_api.controller.KonsumenController;
import id.nesd.umkmdesasambongrejo.rest_api.controller.ProductController;
import id.nesd.umkmdesasambongrejo.tool.FormatCurrency;
import id.nesd.umkmdesasambongrejo.tool.Helper;
import id.nesd.umkmdesasambongrejo.tool.PreferenceManager;

public class HomeActivity extends AppCompatActivity {
    public static int total;
    public static int qty;
    public static String id_product;
    public static String name_product;
    public static String price_product;
    @SuppressLint("StaticFieldLeak")
    private static LinearLayout llTotal;
    @SuppressLint("StaticFieldLeak")
    private static TextView tvTotal;
    private static TextView tvQty;
    private RecyclerView rc_product;
    private ProductController productController;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new Helper().setTitleAppBar(this, "Beranda");
        rc_product = findViewById(R.id.rc_product);
        refreshLayout = findViewById(R.id.refresh_product);
        setupRecycleView(rc_product);
        total = 0;
        qty = 0;
        id_product = "";
        name_product = "";
        price_product = "";
        llTotal = findViewById(R.id.llTotal);
        tvTotal = findViewById(R.id.tvTotal);
        tvQty = findViewById(R.id.qty);
        llTotal.setOnClickListener(view -> {
            Intent i = new Intent(this, BayarActivity.class);
            i.putExtra("total", total);
            i.putExtra("qty",qty);
            i.putExtra("id_product",id_product);
            i.putExtra("name_product",name_product);
            i.putExtra("price_product",price_product);
            startActivity(i);
            total = 0;
            qty = 0;
            id_product = "";
            name_product = "";
            price_product = "";
            update();
        });
        update();

        //refresh data
        setupRecycleView(rc_product);
        refreshLayout.setColorSchemeResources(R.color.purple_500);
        refreshLayout.setOnRefreshListener(this::refreshContent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //
        PreferenceManager preferenceManager = new PreferenceManager("LOGINPREFERENCE",HomeActivity.this);
        if(preferenceManager.getPreference("id_user").equals("")){
            KonsumenController konsumenController = new KonsumenController();
            konsumenController.getKonsumenByEmail(HomeActivity.this,preferenceManager.getPreference("email"));
        }
    }

    @Override
    protected void onResume() {
        setupRecycleView(rc_product);
        super.onResume();
    }

    private void refreshContent(){
        new Handler().postDelayed(() -> {
            setupRecycleView(rc_product);
            refreshLayout.setRefreshing(false);
        }, 3000);
    }
    private void setupRecycleView(@NonNull RecyclerView recyclerView){
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        productController = new ProductController();
        productController.getAllProduct(this,recyclerView);
    }

    @SuppressLint("SetTextI18n")
    public static void update() {
        if (total == 0) {
            llTotal.setVisibility(View.GONE);
        } else {
            llTotal.setVisibility(View.VISIBLE);
        }
        FormatCurrency formatCurrency = new FormatCurrency(total);
        tvTotal.setText(formatCurrency.getFormat());
        tvQty.setText("Total : ("+qty+")");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String number = "088123456789";
        if (id == R.id.menu_call) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_sms) {
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
            smsIntent.setData(Uri.parse("smsto:" + number));
            smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
            smsIntent.putExtra("sms_body", "Halo UMKM Desa Sambongrejo");
            startActivity(smsIntent);
            return true;
        } else if (id == R.id.menu_lokasi) {
            String lat = "-6.982851360196596";
            String lon = "110.4090946209632";
            String uri = "http://maps.google.com/maps?q=loc:" + lat + "," + lon;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_akun) {
            startActivity(new Intent(this, AkunActivity.class));
            return true;
        } else if (id == R.id.menu_tentang) {
            startActivity(new Intent(this, TentangActivity.class));
            return true;
        }else if(id == R.id.history){
            startActivity(new Intent(this,HistoryTransaksi.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}