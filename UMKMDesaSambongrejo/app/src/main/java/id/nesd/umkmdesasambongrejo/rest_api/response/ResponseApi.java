package id.nesd.umkmdesasambongrejo.rest_api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import id.nesd.umkmdesasambongrejo.model.KonsumenModel;
import id.nesd.umkmdesasambongrejo.model.ProductModel;
import id.nesd.umkmdesasambongrejo.model.TransaksiModel;

public class ResponseApi {
    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("address")
    private String address;

    @SerializedName("gender")
    private String gender;

    @SerializedName("id_user")
    private String id_user;

    @SerializedName("products")
    private ArrayList<ProductModel> products;

    @SerializedName("konsumen")
    private ArrayList<KonsumenModel>konsumen;

    @SerializedName("transaction")
    private ArrayList<TransaksiModel>transaksi;

    public ArrayList<TransaksiModel> getTransaksi() {
        return transaksi;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "ResponseApi{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", products=" + products +
                ", konsumen=" + konsumen +
                '}';
    }
}
