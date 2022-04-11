package com.example.produk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.produk.adapter.ProdukAdapter;
import com.example.produk.model.Produk;
import com.example.produk.service.ApiClient;
import com.example.produk.service.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvProduk;
    Button btnCari,btnAdd;
    EditText editCari;

    ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvProduk = findViewById(R.id.rvProduk);
        btnCari = findViewById(R.id.btnCari);
        btnAdd = findViewById(R.id.btnAdd);
        editCari = findViewById(R.id.editCari);


        Call<List<Produk>> call = apiInterface.getAllProduk();


        call.enqueue(new Callback<List<Produk>>() {
            @Override
            public void onResponse(Call<List<Produk>> call, Response<List<Produk>> response) {
                ProdukAdapter adapter = new ProdukAdapter(response.body(), MainActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                rvProduk.setLayoutManager(layoutManager);
                rvProduk.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Produk>> call, Throwable t) {
                System.out.println(t);
            }
        });

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cari = editCari.getText().toString();
                if (cari.isEmpty()) {
                    Call<List<Produk>> call1 = apiInterface.getAllProduk();
                    call1.enqueue(new Callback<List<Produk>>() {
                        @Override
                        public void onResponse(Call<List<Produk>> call, Response<List<Produk>> response) {
                            ProdukAdapter adapter = new ProdukAdapter(response.body(), MainActivity.this);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                            rvProduk.setLayoutManager(layoutManager);
                            rvProduk.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<List<Produk>> call, Throwable t) {
                            System.out.println(t);
                        }
                    });
                } else {
                    Call<List<Produk>> call2 = apiInterface.getProdukByCari(cari);
                    call2.enqueue(new Callback<List<Produk>>() {
                        @Override
                        public void onResponse(Call<List<Produk>> call, Response<List<Produk>> response) {
                            ProdukAdapter adapter = new ProdukAdapter(response.body(), MainActivity.this);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                            rvProduk.setLayoutManager(layoutManager);
                            rvProduk.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<List<Produk>> call, Throwable t) {
                            System.out.println(t);
                        }
                    });
                }
            }

        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TambahData.class);
                startActivityForResult(intent, 2);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Call<List<Produk>> call = apiInterface.getAllProduk();

        call.enqueue(new Callback<List<Produk>>() {
            @Override
            public void onResponse(Call<List<Produk>> call, Response<List<Produk>> response) {
                ProdukAdapter adapter = new ProdukAdapter(response.body(), MainActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
                rvProduk.setLayoutManager(layoutManager);
                rvProduk.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Produk>> call, Throwable t) {

            }

        });
    }
}