package id.nesd.umkmdesasambongrejo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductModel implements Parcelable {
    private final String id;
    private final String name;
    private final String description;
    private final String price;
    private final String photo;
    private final String created_at;
    private final String updated_at;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getPhoto() {
        return photo;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public static Creator<ProductModel> getCREATOR() {
        return CREATOR;
    }

    protected ProductModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        price = in.readString();
        photo = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
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
        parcel.writeString(description);
        parcel.writeString(price);
        parcel.writeString(photo);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
    }
}
