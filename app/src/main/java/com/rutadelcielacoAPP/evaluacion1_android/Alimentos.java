package com.rutadelcielacoAPP.evaluacion1_android;

import android.os.Parcel;
import android.os.Parcelable;

public class Alimentos implements Parcelable {

    private int id;
    private String nombre;

    public Alimentos(){

    }

    public Alimentos(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.nombre = source.readString();
    }

    protected Alimentos(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
    }

    public static final Parcelable.Creator<Alimentos> CREATOR = new Parcelable.Creator<Alimentos>() {
        @Override
        public Alimentos createFromParcel(Parcel source) {
            return new Alimentos(source);
        }

        @Override
        public Alimentos[] newArray(int size) {
            return new Alimentos[size];
        }
    };
}
