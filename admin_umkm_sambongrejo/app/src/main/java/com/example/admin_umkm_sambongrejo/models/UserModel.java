package com.example.admin_umkm_sambongrejo.models;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {
    private final String id;
    private final String name;
    private final String email;
    private final String password;
    private final String roles;
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

    public String getRoles() {
        return roles;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public static Creator<UserModel> getCREATOR() {
        return CREATOR;
    }

    protected UserModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        email = in.readString();
        password = in.readString();
        roles = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
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
        parcel.writeString(roles);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
    }
}
