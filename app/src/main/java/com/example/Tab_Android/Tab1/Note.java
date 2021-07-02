package com.example.Tab_Android.Tab1;

public class Note {
    int _id;
    String address;

    public Note(int _id, String address){
        this._id = _id;
        this.address = address;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
