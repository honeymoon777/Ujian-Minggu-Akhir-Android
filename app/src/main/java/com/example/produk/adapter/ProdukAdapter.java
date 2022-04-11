package com.example.produk.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.produk.Detail_Produk;
import com.example.produk.R;
import com.example.produk.model.Produk;

import java.util.List;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ProdukViewHolder> {
    private List<Produk> produk;
    private Context context;

    public ProdukAdapter (List<Produk> produk, Context context){
        this.context = context;
        this.produk = produk;
    }

    @NonNull
    @Override
    public ProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_layout,parent,false);
        return new ProdukAdapter.ProdukViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukAdapter.ProdukViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load("http://6cb8-139-192-158-19.ngrok.io/foto-product/"+produk.get(position)
                .getGambarProduct()).into(holder.imageProdukItem);
        holder.txtNamaProduk.setText(produk.get(position).getNamaProduct());
        holder.txtHargaProduk.setText(String.valueOf(produk.get(position).getHargaProduct()));



        holder.imageProdukItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Detail_Produk.class);
                intent.putExtra("produk", produk.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return produk.size();
    }

    public class ProdukViewHolder extends RecyclerView.ViewHolder {

        ImageView imageProdukItem;
        TextView txtNamaProduk, txtHargaProduk;

        public ProdukViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProdukItem =itemView.findViewById(R.id.imageProdukItem);
            txtNamaProduk= itemView.findViewById(R.id.txtNamaProduk);
            txtHargaProduk = itemView.findViewById(R.id.txtHargaProduk);

        }
    }
}
