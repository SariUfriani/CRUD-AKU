package com.example.crud_mobile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ObatAdapter extends RecyclerView.Adapter<ObatAdapter.ViewHolderSaya> {

    private Context context;
    private ArrayList obat_id, obat_nama, obat_jenisobat, obat_harga;

    ObatAdapter(
            Context context,
            ArrayList obat_id,
            ArrayList obat_nama,
            ArrayList obat_jenisobat,
            ArrayList obat_harga
    ){
        this.context    = context;
        this.obat_id    = obat_id;
        this.obat_nama = obat_nama;
        this.obat_jenisobat = obat_jenisobat;
        this.obat_harga   = obat_harga;

    }

    @NonNull
    @Override
    public ViewHolderSaya onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflaterKita = LayoutInflater.from(context);
        View viewSaya = inflaterKita.inflate(R.layout.sariufriani8020180281, parent, false);
        return new ViewHolderSaya(viewSaya);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSaya holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_id_obat.setText(String.valueOf(obat_id.get(position)));
        holder.txt_nama_obat.setText(String.valueOf(obat_nama.get(position)));
        holder.txt_obat_jenisobat.setText(String.valueOf(obat_jenisobat.get(position)));
        holder.txt_obat_harga.setText(String.valueOf(obat_harga.get(position)));
        holder.layoutUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentKita = new Intent(context, UbahObatActivity.class);
                intentKita.putExtra("id", String.valueOf(obat_id.get(position)));
                intentKita.putExtra("nama", String.valueOf(obat_nama.get(position)));
                intentKita.putExtra("jenisobat", String.valueOf(obat_jenisobat.get(position)));
                intentKita.putExtra("harga", String.valueOf(obat_harga.get(position)));

                context.startActivity(intentKita);
            }
        });
    }

    public int getItemCount() {
        return obat_id.size();
    }

    public class ViewHolderSaya extends RecyclerView.ViewHolder {

        TextView txt_id_obat, txt_nama_obat, txt_obat_jenisobat, txt_obat_harga;
        LinearLayout layoutUtama;

        public ViewHolderSaya(@NonNull View itemView) {
            super(itemView);

            txt_id_obat         = itemView.findViewById(R.id.txt_obat_id);
            txt_nama_obat      = itemView.findViewById(R.id.txt_obat_nama);
            txt_obat_jenisobat  = itemView.findViewById(R.id.txt_obat_jenis);
            txt_obat_harga        = itemView.findViewById(R.id.txt_obat_harga);
            layoutUtama         = itemView.findViewById(R.id.layout_utama);
        }
    }
}
