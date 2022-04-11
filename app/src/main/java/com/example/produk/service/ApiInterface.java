package com.example.produk.service;

import com.example.produk.model.Produk;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/")
    Call<List<Produk>> getAllProduk();

    @GET("/cari")
    Call<List<Produk>> getProdukByCari(@Query("namaproduct")String namaproduct);

    @Multipart
    @POST("/")
    Call<String> saveProduk(@Part MultipartBody.Part file, @Part("data") RequestBody data );
}
