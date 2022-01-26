package id.nesd.umkmdesasambongrejo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import id.nesd.umkmdesasambongrejo.activity.DetailActivity;
import id.nesd.umkmdesasambongrejo.activity.HomeActivity;
import id.nesd.umkmdesasambongrejo.R;
import id.nesd.umkmdesasambongrejo.model.MenuModel;

public class MenuAdapter extends BaseAdapter {
    Context context;
    List<MenuModel> menu;
    LayoutInflater inflater;


    public MenuAdapter(List<MenuModel> menu, Context context) {
        this.context = context;
        this.menu = menu;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_menu, null);
        }
        ImageView ivPhoto = view.findViewById(R.id.ivPhoto);
        TextView tvNama = view.findViewById(R.id.tvNama);
        TextView tvHarga = view.findViewById(R.id.tvHarga);

        MenuModel d = menu.get(i);
        Glide.with(context).load(d.getFoto())
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(ivPhoto);
        tvNama.setText(d.getNama());
        tvHarga.setText("Rp. " + d.getHarga());
        ivPhoto.setOnClickListener(view1 -> {
            HomeActivity.total += d.getHarga();
            HomeActivity.update();
        });
        tvNama.setOnClickListener(v -> {
            Intent i1 = new Intent(context, DetailActivity.class);
            i1.putExtra("namida", d.getId());
            context.startActivity(i1);
        });
        return view;
    }


}
