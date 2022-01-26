package id.nesd.umkmdesasambongrejo.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

import id.nesd.umkmdesasambongrejo.R;
import id.nesd.umkmdesasambongrejo.activity.DetailActivity;
import id.nesd.umkmdesasambongrejo.activity.HomeActivity;
import id.nesd.umkmdesasambongrejo.model.ProductModel;
import id.nesd.umkmdesasambongrejo.rest_api.EndPoint;
import id.nesd.umkmdesasambongrejo.tool.FormatCurrency;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<ProductModel> listProduct;
    private final RecyclerView recyclerView;
    public ProductAdapter(Context context,ArrayList<ProductModel> listProduct,RecyclerView recyclerView){
        this.context = context;
        this.listProduct = listProduct;
        this.recyclerView = recyclerView;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // bind data
        holder.bind(context,listProduct.get(position));
        // on click
        holder.onClickProduct(context,listProduct.get(position),recyclerView);
    }

    @Override
    public int getItemCount() {
        return this.listProduct.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_product;
        TextView tv_title;
        TextView tv_price;
        CardView card_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product = itemView.findViewById(R.id.img_product);
            tv_title = itemView.findViewById(R.id.title_product);
            tv_price = itemView.findViewById(R.id.price_product);
            card_img = itemView.findViewById(R.id.card_product);
        }
        public void bind(Context context,ProductModel model){
            FormatCurrency currency = new FormatCurrency(Integer.parseInt(model.getPrice()));
            Glide.with(context).load(EndPoint.IMG_URL+model.getPhoto()).into(img_product);
            tv_price.setText(currency.getFormat());
            tv_title.setText(model.getName());
        }
        public void onClickProduct(Context context,ProductModel model,RecyclerView recyclerView){
            img_product.setOnClickListener(view -> {
                Intent i1 = new Intent(context, DetailActivity.class);
                i1.putExtra("product", model);
                context.startActivity(i1);
            });
            tv_title.setOnClickListener(view -> {
                HomeActivity.total += Integer.parseInt(model.getPrice());
                HomeActivity.qty +=1;
                HomeActivity.id_product = model.getId();
                HomeActivity.name_product = model.getName();
                HomeActivity.price_product = model.getPrice();
                HomeActivity.update();
            });

        }

    }
}
