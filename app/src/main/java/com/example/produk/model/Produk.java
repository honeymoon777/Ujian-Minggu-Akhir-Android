package com.example.produk.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Produk implements Parcelable {
    private long id;
    private String namaProduct;
    private int hargaProduct;
    private String deskripsiProduct;
    private String categoryProduct;
    private String gambarProduct;

    public Produk(long id, String namaProduct, int hargaProduct, String deskripsiProduct, String categoryProduct, String gambarProduct) {
        this.id = id;
        this.namaProduct = namaProduct;
        this.hargaProduct = hargaProduct;
        this.deskripsiProduct = deskripsiProduct;
        this.categoryProduct = categoryProduct;
        this.gambarProduct = gambarProduct;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamaProduct() {
        return namaProduct;
    }

    public void setNamaProduct(String namaProduct) {
        this.namaProduct = namaProduct;
    }

    public int getHargaProduct() {
        return hargaProduct;
    }

    public void setHargaProduct(int hargaProduct) {
        this.hargaProduct = hargaProduct;
    }

    public String getDeskripsiProduct() {
        return deskripsiProduct;
    }

    public void setDeskripsiProduct(String deskripsiProduct) {
        this.deskripsiProduct = deskripsiProduct;
    }

    public String getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(String categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public String getGambarProduct() {
        return gambarProduct;
    }

    public void setGambarProduct(String gambarProduct) {
        this.gambarProduct = gambarProduct;
    }

    public static Creator<Produk> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.namaProduct);
        dest.writeInt(this.hargaProduct);
        dest.writeString(this.deskripsiProduct);
        dest.writeString(this.categoryProduct);
        dest.writeString(this.gambarProduct);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readLong();
        this.namaProduct = source.readString();
        this.hargaProduct = source.readInt();
        this.deskripsiProduct = source.readString();
        this.categoryProduct = source.readString();
        this.gambarProduct = source.readString();
    }

    protected Produk(Parcel in) {
        this.id = in.readLong();
        this.namaProduct = in.readString();
        this.hargaProduct = in.readInt();
        this.deskripsiProduct = in.readString();
        this.categoryProduct = in.readString();
        this.gambarProduct = in.readString();
    }

    public static final Parcelable.Creator<Produk> CREATOR = new Parcelable.Creator<Produk>() {
        @Override
        public Produk createFromParcel(Parcel source) {
            return new Produk(source);
        }

        @Override
        public Produk[] newArray(int size) {
            return new Produk[size];
        }
    };
}
