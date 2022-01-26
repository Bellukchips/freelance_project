package id.nesd.umkmdesasambongrejo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.nesd.umkmdesasambongrejo.R;
import id.nesd.umkmdesasambongrejo.tool.Helper;

public class TentangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);
        new Helper().setTitleAppBar(this, getResources().getString(R.string.tentang));
    }
}