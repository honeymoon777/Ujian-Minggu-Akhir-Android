package com.example.produk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.produk.model.Produk;

public class Detail_Produk extends AppCompatActivity {

    ImageView imageProduk;
    TextView txtNamaProdukDetail, txtHargaProdukDetail, txtDeskripsiDetail, txtKategoriDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        imageProduk = findViewById(R.id.imageProduk);
        txtNamaProdukDetail = findViewById(R.id.txtNamaProdukDetail);
        txtHargaProdukDetail = findViewById(R.id.txtHargaProdukDetail);
        txtDeskripsiDetail = findViewById(R.id.txtDeskripsiProdukDetail);
        txtKategoriDetail = findViewById(R.id.txtKategoriProdukDetail);

        Produk produk = getIntent().getExtras().getParcelable("produk");

        Glide.with(this).load("http://b403-139-192-158-19.ngrok.io/foto-product/"+produk.getGambarProduct()).into(imageProduk);
        txtNamaProdukDetail.setText(produk.getNamaProduct());
        txtHargaProdukDetail.setText(String.valueOf(produk.getHargaProduct()));
        txtDeskripsiDetail.setText(produk.getDeskripsiProduct());
        txtKategoriDetail.setText(produk.getCategoryProduct());
    }
}