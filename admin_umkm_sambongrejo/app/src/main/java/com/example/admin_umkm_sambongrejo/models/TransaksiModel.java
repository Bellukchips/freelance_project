package com.example.admin_umkm_sambongrejo.models;

import android.os.Parcel;
import android.os.Parcelable;

public class TransaksiModel implements Parcelable {
    private final String id_transaksi;
    private final String id_konsumen;
    private final String name_konsumen;
    private final String name_product;
    private final String price_product;
    private final String tanggal_beli;
    private final String bukti_pembayaran;
    private final String quantity;
    private final String ongkir;
    private final String total;
    private final String tujuan;

    public String getName_product() {
        return name_product;
    }

    public String getId_transaksi() {
        return id_transaksi;
    }

    public String getId_konsumen() {
        return id_konsumen;
    }

    public String getName_konsumen() {
        return name_konsumen;
    }

    public String getTanggal_beli() {
        return tanggal_beli;
    }

    public String getBukti_pembayaran() {
        return bukti_pembayaran;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getOngkir() {
        return ongkir;
    }

    public String getTotal() {
        return total;
    }

    public String getTujuan() {
        return tujuan;
    }

    public static Creator<TransaksiModel> getCREATOR() {
        return CREATOR;
    }

    protected TransaksiModel(Parcel in) {
        id_transaksi = in.readString();
        id_konsumen = in.readString();
        name_konsumen = in.readString();
        tanggal_beli = in.readString();
        bukti_pembayaran = in.readString();
        quantity = in.readString();
        ongkir = in.readString();
        total = in.readString();
        tujuan = in.readString();
        name_product = in.readString();
        price_product = in.readString();
    }

    public String getPrice_product() {
        return price_product;
    }

    public static final Creator<TransaksiModel> CREATOR = new Creator<TransaksiModel>() {
        @Override
        public TransaksiModel createFromParcel(Parcel in) {
            return new TransaksiModel(in);
        }

        @Override
        public TransaksiModel[] newArray(int size) {
            return new TransaksiModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id_transaksi);
        parcel.writeString(id_konsumen);
        parcel.writeString(name_konsumen);
        parcel.writeString(tanggal_beli);
        parcel.writeString(bukti_pembayaran);
        parcel.writeString(quantity);
        parcel.writeString(ongkir);
        parcel.writeString(total);
        parcel.writeString(tujuan);
    }
}
