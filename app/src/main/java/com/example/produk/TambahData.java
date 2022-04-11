package com.example.produk;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.produk.model.Produk;
import com.example.produk.service.ApiClient;
import com.example.produk.service.ApiInterface;
import com.google.gson.Gson;

import java.io.File;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahData extends AppCompatActivity {

    EditText editNama, editHarga, editDeskripsi, editKategori;
    Button btnSave, btnSubmit;
    ImageView imageTambah;
    String filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_data);

        editNama = findViewById(R.id.editNama);
        editHarga = findViewById(R.id.editHarga);
        editDeskripsi = findViewById(R.id.editDeskripsi);
        editKategori = findViewById(R.id.editKategori);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSave = findViewById(R.id.btnSave);
        imageTambah = findViewById(R.id.imageTambah);

        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahData.this, ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);
                startActivityForResult(intent, 1);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produk produk = new Produk();
                produk.setNamaProduct(editNama.getText().toString());
                produk.setHargaProduct(Integer.parseInt(editHarga.getText().toString()));
                produk.setDeskripsiProduct(editDeskripsi.getText().toString());
                produk.setCategoryProduct(editKategori.getText().toString());

                Gson gson = new Gson();
                String json = gson.toJson(produk);

                //Untuk mendapatkan filenya
                File file = new File(filepath);

                //ini untuk data json yang dikirim
                RequestBody data = RequestBody.create(MediaType.parse("text/plain"), json);

                //convert data file menjadi request body
                RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(),
                        requestBody);

                Call<String> call = apiInterface.saveProduk(fileToUpload, data);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(TambahData.this, response.body(), Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(TambahData.this, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        filepath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
        imageTambah.setImageBitmap(BitmapFactory.decodeFile(filepath));
    }
}
