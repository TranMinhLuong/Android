package com.example.applicationeot.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationeot.Activity.SanPhamActivity;
import com.example.applicationeot.Models.DanhMucMG;
import com.example.applicationeot.Models.SanPhamMG;
import com.example.applicationeot.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucAdapter.Holder> {
    private Context context;
    private ArrayList<DanhMucMG> dsdanhmuc;

    public DanhMucAdapter() {
    }

    public DanhMucAdapter(Context context, ArrayList<DanhMucMG> dsdanhmuc) {
        this.context = context;
        this.dsdanhmuc = dsdanhmuc;
    }

    @NonNull
    @Override
    public DanhMucAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_danhmuc, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DanhMucAdapter.Holder holder, int position) {
        final DanhMucMG danhMucMG = dsdanhmuc.get(position);
        holder.txtdanhmuc.setText(danhMucMG.getTendm());
        Picasso.with(context).load(danhMucMG.getAnhdm()).placeholder(R.drawable.noimage).error(R.drawable.error).into(holder.imgdanhmuc);
        holder.carddm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iddm = String.valueOf(danhMucMG.getIddm());
                Intent intent = new Intent(context, SanPhamActivity.class);
                intent.putExtra("danhmucsp",iddm);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsdanhmuc.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imgdanhmuc;
        TextView txtdanhmuc;
        CardView carddm;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imgdanhmuc = itemView.findViewById(R.id.imgdanhmuc);
            txtdanhmuc = itemView.findViewById(R.id.txttendanhmuc);
            carddm = itemView.findViewById(R.id.carddm);
        }
    }
}
