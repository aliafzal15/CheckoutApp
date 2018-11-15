package com.app.checkout51.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;


public class OfferModel implements Parcelable, Comparable<OfferModel> {

    private int id;
    private String name;
    private String imageURL;
    private double cashBack;

    public OfferModel(int id, String name, String imageURL, double cashBack) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.cashBack = cashBack;
    }

    public OfferModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.imageURL = in.readString();
        this.cashBack = in.readDouble();
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public double getCashBack() {
        return cashBack;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.imageURL);
        dest.writeDouble(this.cashBack);
    }

    public static final Creator<OfferModel> CREATOR = new Creator<OfferModel>() {
        @Override
        public OfferModel createFromParcel(Parcel source) {
            return new OfferModel(source);
        }

        @Override
        public OfferModel[] newArray(int size) {
            return new OfferModel[size];
        }
    };

    @Override
    public int compareTo(@NonNull OfferModel o) {
        return this.name.compareTo(o.getName());
    }
}
