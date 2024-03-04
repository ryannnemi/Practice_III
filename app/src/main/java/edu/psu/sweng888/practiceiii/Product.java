package edu.psu.sweng888.practiceiii;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.io.Serializable;

public class Product implements Parcelable, Serializable {
    private int id;
    private String name;
    private String description;
    private String seller;
    private double price;

    protected Product(Parcel in){
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        seller = in.readString();
        price = in.readDouble();
        }

    public Product(int ID, String Name, String Description, String Seller, double Price) {
        id = ID;
        name = Name;
        description = Description;
        seller = Seller;
        price = Price;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) { return new Product(in); }

        @Override
        public Product[] newArray(int size) { return new Product[size]; }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(seller);
        dest.writeDouble(price);
    }

    // Getters
    public int getID() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getSeller() { return seller; }
    public double getPrice() { return price; }

}
