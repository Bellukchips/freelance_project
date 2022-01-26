package com.example.admin_umkm_sambongrejo.activity.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin_umkm_sambongrejo.R;
import com.example.admin_umkm_sambongrejo.activity.form.FormInputEditActivity;
import com.example.admin_umkm_sambongrejo.rest_api.controller.ProductController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ProductFragment extends Fragment {

    public  ProductFragment(){

    }
    private ProductController productController;
    private RecyclerView rc_product;
    private SwipeRefreshLayout refreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_product, container, false);
        rc_product = view.findViewById(R.id.rc_product);
        refreshLayout = view.findViewById(R.id.refresh_product);
        FloatingActionButton fab = view.findViewById(R.id.fabAddProduct);
        fab.setOnClickListener(view1 -> {
            Intent formAddData = new Intent(getContext(), FormInputEditActivity.class);
            formAddData.putExtra("isEdit",0);
            startActivity(formAddData);
        });
        return view;
    }

    @Override
    public void onResume() {
        setupRecycleView(rc_product);
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecycleView(rc_product);
        refreshLayout.setColorSchemeResources(R.color.purple_500);
        refreshLayout.setOnRefreshListener(this::refreshContent);
    }

    private void refreshContent(){
        new Handler().postDelayed(() -> {
            setupRecycleView(rc_product);
            refreshLayout.setRefreshing(false);
        }, 3000);
    }
    private void setupRecycleView(@NonNull RecyclerView recyclerView){
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        productController = new ProductController();
        productController.getAllProduct(getContext(),recyclerView);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}