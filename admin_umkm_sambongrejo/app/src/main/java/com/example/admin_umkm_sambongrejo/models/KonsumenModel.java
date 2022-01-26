package com.example.admin_umkm_sambongrejo.models;

import android.os.Parcel;
import android.os.Parcelable;

public class KonsumenModel implements Parcelable {
    private final String id;
    private final String name;
    private final String email;
    private final String password;
    private final String gender;
    private final String address;
    private final String created_at;
    private final String updated_at;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public static Creator<KonsumenModel> getCREATOR() {
        return CREATOR;
    }

    protected KonsumenModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        email = in.readString();
        password = in.readString();
        gender = in.readString();
        address = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<KonsumenModel> CREATOR = new Creator<KonsumenModel>() {
        @Override
        public KonsumenModel createFromParcel(Parcel in) {
            return new KonsumenModel(in);
        }

        @Override
        public KonsumenModel[] newArray(int size) {
            return new KonsumenModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(gender);
        parcel.writeString(address);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
    }
}
