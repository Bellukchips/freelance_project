package com.example.admin_umkm_sambongrejo.rest_api.response;

import com.example.admin_umkm_sambongrejo.models.KonsumenModel;
import com.example.admin_umkm_sambongrejo.models.ProductModel;
import com.example.admin_umkm_sambongrejo.models.TransaksiModel;
import com.example.admin_umkm_sambongrejo.models.UserModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseApi {
    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("products")
    private ArrayList<ProductModel> products;

    @SerializedName("users")
    private ArrayList<UserModel>users;

    @SerializedName("konsumen")
    private ArrayList<KonsumenModel>konsumen;

    @SerializedName("transaction")
    private ArrayList<TransaksiModel>transaksi;

    public ArrayList<TransaksiModel> getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(ArrayList<TransaksiModel> transaksi) {
        this.transaksi = transaksi;
    }

    public ArrayList<KonsumenModel> getKonsumen() {
        return konsumen;
    }

    public void setKonsumen(ArrayList<KonsumenModel> konsumen) {
        this.konsumen = konsumen;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public ArrayList<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductModel> products) {
        this.products = products;
    }

    public ArrayList<UserModel> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserModel> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "ResponseApi{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", products=" + products +
                ", users=" + users +
                ", konsumen=" + konsumen +
                '}';
    }
}
