package com.example.admin_umkm_sambongrejo.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_umkm_sambongrejo.R;
import com.example.admin_umkm_sambongrejo.activity.form.FormAddEditKonsumen;
import com.example.admin_umkm_sambongrejo.models.KonsumenModel;
import com.example.admin_umkm_sambongrejo.rest_api.controller.KonsumenController;

import java.util.ArrayList;

public class KonsumenAdapter extends RecyclerView.Adapter<KonsumenAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<KonsumenModel> listUser;
    private final RecyclerView recyclerView;
    public KonsumenAdapter(Context context, ArrayList<KonsumenModel>listUser, RecyclerView recyclerView){
        this.context = context;
        this.listUser = listUser;
        this.recyclerView = recyclerView;
    }
    @NonNull
    @Override
    public KonsumenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KonsumenAdapter.ViewHolder holder, int position) {
        holder.bind(listUser.get(position));
        holder.onClick(context,recyclerView,listUser.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listUser.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_name;
        TextView tv_username;
        TextView tv_gender;
        TextView tv_address;
        CardView card_user;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_username = itemView.findViewById(R.id.tv_username);
            card_user = itemView.findViewById(R.id.card_user);
            tv_gender = itemView.findViewById(R.id.tv_gender);
            tv_address = itemView.findViewById(R.id.tv_address);
        }
        public void bind(KonsumenModel model){
            tv_id.setText(model.getId());
            tv_username.setText(model.getEmail());
            tv_name.setText(model.getName());
            tv_gender.setText(model.getGender());
            tv_address.setText(model.getAddress());
        }
        public void onClick(Context context,RecyclerView recyclerView,KonsumenModel model){
            card_user.setOnClickListener(view -> {
                Intent edit = new Intent(context, FormAddEditKonsumen.class);
                edit.putExtra("isEdit",1);
                edit.putExtra("konsumen",model);
                context.startActivity(edit);
            });
            card_user.setOnLongClickListener(view -> {
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Select your answer");
                // Ask the final question
                builder.setMessage("Are you sure to delete data user?");
                builder.setPositiveButton("Yes", (dialog, which) -> {
                    KonsumenController userController = new KonsumenController();
                    userController.deleteKonsumen(model.getId(),context);
                    //
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setHasFixedSize(true);
                    userController.getAllKonsumen(context,recyclerView);
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
