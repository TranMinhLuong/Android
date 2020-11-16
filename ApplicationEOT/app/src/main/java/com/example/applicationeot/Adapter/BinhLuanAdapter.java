package com.example.applicationeot.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationeot.Models.BinhLuanMG;
import com.example.applicationeot.R;

import java.util.ArrayList;

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.Holder> {
    private Context context;
    private ArrayList<BinhLuanMG> dsbl;

    public BinhLuanAdapter() {
    }

    public BinhLuanAdapter(Context context, ArrayList<BinhLuanMG> dsbl) {
        this.context = context;
        this.dsbl = dsbl;
    }

    @NonNull
    @Override
    public BinhLuanAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_binhluan, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuanAdapter.Holder holder, int position) {
        BinhLuanMG binhLuanMG = dsbl.get(position);
        holder.txtnguoibl.setText(binhLuanMG.getTenkh());
        holder.txtngaybl.setText(binhLuanMG.getCreated());
        holder.txtcomment.setText(binhLuanMG.getComment());
        holder.ratingBar.setRating(Float.parseFloat(binhLuanMG.getRating()));
    }

    @Override
    public int getItemCount() {
        return dsbl.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txtnguoibl,txtngaybl,txtcomment;
        RatingBar ratingBar;
        public Holder(@NonNull View itemView) {
            super(itemView);
            txtnguoibl = itemView.findViewById(R.id.txtnguoibl);
            txtngaybl = itemView.findViewById(R.id.txtngaybl);
            txtcomment = itemView.findViewById(R.id.txtcomment);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
