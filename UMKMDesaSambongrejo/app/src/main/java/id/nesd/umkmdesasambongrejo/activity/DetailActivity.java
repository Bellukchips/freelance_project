package id.nesd.umkmdesasambongrejo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

import id.nesd.umkmdesasambongrejo.R;
import id.nesd.umkmdesasambongrejo.model.MenuModel;
import id.nesd.umkmdesasambongrejo.model.ProductModel;
import id.nesd.umkmdesasambongrejo.rest_api.EndPoint;
import id.nesd.umkmdesasambongrejo.tool.FormatCurrency;

public class DetailActivity extends AppCompatActivity {
    private AppBarLayout barLayout;
    private CollapsingToolbarLayout toolbarLayout;
    private ImageView ivImage;
    private TextView tvName;
    private TextView tvPrice;
    private TextView tvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        barLayout = findViewById(R.id.app_bar_layout);
        toolbarLayout = findViewById(R.id.coll);
        ivImage = findViewById(R.id.ivImage);
        tvName = findViewById(R.id.tvName);
        tvPrice = findViewById(R.id.tvPrice);
        tvDesc = findViewById(R.id.tvDesc);

        assignData();
    }

    @SuppressLint("SetTextI18n")
    private void assignData() {
        Intent intent = getIntent();
        ProductModel model = intent.getParcelableExtra("product");

        Glide.with(this).load(EndPoint.IMG_URL+model.getPhoto())
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(ivImage);
        FormatCurrency formatCurrency = new FormatCurrency(Integer.parseInt(model.getPrice()));
        tvName.setText(model.getName());
        tvPrice.setText(formatCurrency.getFormat());
        tvDesc.setText(model.getName());
        handleBar(model.getName());
    }

    private void handleBar(String title) {
        barLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            Resources r = getResources();
            Resources.Theme t = getApplicationContext().getTheme();
            if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                //  on Collapse
                toolbarLayout.setCollapsedTitleGravity(Gravity.CENTER_VERTICAL);
                toolbarLayout.setTitle(title);
                toolbarLayout.setCollapsedTitleTextColor(r.getColor(R.color.white, t));
                toolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.transparent, t));
                toolbarLayout.setContentScrim(new ColorDrawable(getResources().getColor(R.color.transparentBlack, t)));
            } else {
                toolbarLayout.setCollapsedTitleGravity(Gravity.CENTER_VERTICAL);
                toolbarLayout.setTitle("\t");
                toolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white, t));
            }
        });
    }
}