package com.example.admin_umkm_sambongrejo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_umkm_sambongrejo.R;
import com.example.admin_umkm_sambongrejo.models.TransaksiModel;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<TransaksiModel> listReport;

    public TransactionAdapter(Context context, ArrayList<TransaksiModel> listReport){
        this.context = context;
        this.listReport = listReport;
    }
    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        holder.bind(listReport.get(position));
    }

    @Override
    public int getItemCount() {
        return listReport.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id_transaksi;
        TextView tv_id_konsumen;
        TextView tv_name_konsumen;
        TextView tv_name_product;
        TextView tv_tgl_transaksi;
        TextView tv_quanity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id_transaksi = itemView.findViewById(R.id.tv_id_transaksi);
            tv_id_konsumen = itemView.findViewById(R.id.tv_id_konsumen);
            tv_name_konsumen = itemView.findViewById(R.id.tv_name_konsumen);
            tv_name_product = itemView.findViewById(R.id.tv_name_product);
            tv_tgl_transaksi = itemView.findViewById(R.id.tv_tgl_beli);
            tv_quanity = itemView.findViewById(R.id.tv_qty);
        }

        public void bind(TransaksiModel model){
            tv_id_transaksi.setText(model.getId_transaksi());
            tv_id_konsumen.setText(model.getId_konsumen());
            tv_name_konsumen.setText(model.getName_konsumen());
            tv_name_product.setText(model.getName_product());
            tv_tgl_transaksi.setText(model.getTanggal_beli());
            tv_quanity.setText(model.getQuantity());
        }
    }
}
