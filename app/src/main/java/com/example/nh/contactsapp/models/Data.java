package com.example.nh.contactsapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 5/4/2018.
 */

public class Data implements Parcelable {
    private int id;
    private String name;
    private String phone;
    private String email;

    public Data(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Data(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    protected Data(Parcel in) {
        id = in.readInt();
        name = in.readString();
        phone = in.readString();
        email = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(phone);
        parcel.writeString(email);
    }
}
