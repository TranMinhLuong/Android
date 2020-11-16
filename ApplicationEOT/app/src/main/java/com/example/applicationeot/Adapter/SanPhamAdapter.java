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

import com.example.applicationeot.Activity.ChiTietSPActivity;
import com.example.applicationeot.Models.SanPhamMG;
import com.example.applicationeot.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.Holder> {
    private Context context;
    private ArrayList<SanPhamMG> dssp;

    public SanPhamAdapter() {
    }

    public SanPhamAdapter(Context context, ArrayList<SanPhamMG> dssp) {
        this.context = context;
        this.dssp = dssp;
    }

    @NonNull
    @Override
    public SanPhamAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_dongsp, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.Holder holder, int position) {
        final SanPhamMG sanPhamMG = dssp.get(position);
        holder.imganhsp.setImageBitmap(sanPhamMG.getImghinhsp());
        holder.txtTenSP.setText(sanPhamMG.getTensp()+"("+sanPhamMG.getKhuyenmai()+"%)");
        int giamgia = (int) ((1 - (int) sanPhamMG.getKhuyenmai()*1.0/100) * Integer.parseInt(sanPhamMG.getGiasp()));
        holder.txtGiaSP.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(giamgia) +" VND");
        holder.itemsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPhamMG sp = new SanPhamMG();
                sp.setIddm(sanPhamMG.getIddm());
                sp.setIdsp(sanPhamMG.getIdsp());
                sp.setNhacungcap(sanPhamMG.getNhacungcap());
                sp.setNoidung(sanPhamMG.getNoidung());
                sp.setKhuyenmai(sanPhamMG.getKhuyenmai());
                sp.setGiasp(sanPhamMG.getGiasp());
                sp.setTensp(sanPhamMG.getTensp());
                Intent intent = new Intent(context, ChiTietSPActivity.class);
                intent.putExtra("sanpham", sp);
                intent.putExtra("img", sanPhamMG.getImghinhsp());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
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
