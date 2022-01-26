package com.example.admin_umkm_sambongrejo.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.admin_umkm_sambongrejo.R;
import com.example.admin_umkm_sambongrejo.activity.form.FormInputEditActivity;
import com.example.admin_umkm_sambongrejo.models.ProductModel;
import com.example.admin_umkm_sambongrejo.models.UserModel;
import com.example.admin_umkm_sambongrejo.rest_api.EndPoint;
import com.example.admin_umkm_sambongrejo.rest_api.controller.ProductController;
import com.example.admin_umkm_sambongrejo.utils.FormatCurrency;

import java.util.ArrayList;

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
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
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
            card_img.setOnClickListener(view -> {
                Intent formEditData = new Intent(context, FormInputEditActivity.class);
                formEditData.putExtra("isEdit",1);
                formEditData.putExtra("product",model);
                context.startActivity(formEditData);
            });
            card_img.setOnLongClickListener(view -> {
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Select your answer");
                // Ask the final question
                builder.setMessage("Are you sure to delete data product?");
                builder.setPositiveButton("Yes", (dialog, which) -> {
                    ProductController productController = new ProductController();
                    productController.deleteProduct(model.getId(),context);
                    //
                    GridLayoutManager layoutManager = new GridLayoutManager(context,2);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    productController.getAllProduct(context,recyclerView);
                    Toast.makeText(context,
                            "Success delete data",Toast.LENGTH_SHORT).show();
                });
                // Set the alert dialog no button click listener
                builder.setNegativeButton("No", (dialog, which) -> {
                    Toast.makeText(context,
                            "Cancel delete data",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();

                return true;
            });
        }

    }
}
