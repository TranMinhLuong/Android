package com.example.searchfilters;

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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.Holder> {
    private Context context;
    private ArrayList<ModelSearch> dssp;

    public AdapterSearch() {
    }

    public AdapterSearch(Context context, ArrayList<ModelSearch> dssp) {
        this.context = context;
        this.dssp = dssp;
    }

    @NonNull
    @Override
    public AdapterSearch.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_sp_seach, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSearch.Holder holder, int position) {
        final ModelSearch modelSearch = dssp.get(position);
        holder.imganhsp.setImageBitmap(modelSearch.getImghinhsp());
        holder.txtTenSP.setText(modelSearch.getTensp()+"("+modelSearch.getKhuyenmai()+"%)");
        int giamgia = (int) ((1 - (int) modelSearch.getKhuyenmai()*1.0/100) * Integer.parseInt(modelSearch.getGiasp()));
        holder.txtGiaSP.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(giamgia) +" VND");
    }

    @Override
    public int getItemCount() {
        return dssp.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imganhsp;
        TextView txtGiaSP;
        TextView txtTenSP;
        CardView itemsp;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imganhsp = itemView.findViewById(R.id.imgHinhSp);
            txtTenSP = itemView.findViewById(R.id.txtTenSP);
            txtGiaSP = itemView.findViewById(R.id.txtGiaSP);
            itemsp = itemView.findViewById(R.id.itemsp);
        }
    }
}
