package com.example.admin_umkm_sambongrejo.activity.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin_umkm_sambongrejo.R;
import com.example.admin_umkm_sambongrejo.activity.form.FormAddEditKonsumen;
import com.example.admin_umkm_sambongrejo.rest_api.controller.KonsumenController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserFragment extends Fragment {

    public UserFragment(){

    }
    private  RecyclerView rc_user;
    private KonsumenController konsumenController;
    private SwipeRefreshLayout refreshLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user, container, false);
        rc_user = view.findViewById(R.id.rc_user);
        refreshLayout = view.findViewById(R.id.refresh_user);
        FloatingActionButton fab = view.findViewById(R.id.fabAddUser);
        fab.setOnClickListener(view1 -> {
            Intent formAddData = new Intent(getContext(), FormAddEditKonsumen.class);
            formAddData.putExtra("isEdit",0);
            startActivity(formAddData);
        });
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecycleView(rc_user);
        refreshLayout.setColorSchemeResources(R.color.purple_500);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
    }

    private void refreshContent(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setupRecycleView(rc_user);
                refreshLayout.setRefreshing(false);
            }
        }, 3000);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void setupRecycleView(@NonNull RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        konsumenController = new KonsumenController();
        konsumenController.getAllKonsumen(getContext(),rc_user);
    }
}