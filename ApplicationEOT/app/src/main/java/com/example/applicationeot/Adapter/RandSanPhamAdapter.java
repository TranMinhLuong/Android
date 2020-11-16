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
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RandSanPhamAdapter extends RecyclerView.Adapter<RandSanPhamAdapter.Holder> {
    private Context context;
    private ArrayList<SanPhamMG> dsrandsp;

    public RandSanPhamAdapter() {
    }

    public RandSanPhamAdapter(Context context, ArrayList<SanPhamMG> dsrandsp) {
        this.context = context;
        this.dsrandsp = dsrandsp;
    }

    @NonNull
    @Override
    public RandSanPhamAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View  view = inflater.inflate(R.layout.item_randsp, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RandSanPhamAdapter.Holder holder, int position) {
        final SanPhamMG mgsp = dsrandsp.get(position);
        holder.imghinhRandsp.setImageBitmap(mgsp.getImghinhsp());
        holder.txttenspRand.setText(mgsp.getTensp()+"("+mgsp.getKhuyenmai()+"%)");
        int giamgia = (int) ((1 - (int) mgsp.getKhuyenmai()*1.0/100) * Integer.parseInt(mgsp.getGiasp()));
        holder.txtgiaspRand.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(giamgia) +" VND");
        holder.cardViewRand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPhamMG sp = new SanPhamMG();
                sp.setIddm(mgsp.getIddm());
                sp.setIdsp(mgsp.getIdsp());
                sp.setNhacungcap(mgsp.getNhacungcap());
                sp.setNoidung(mgsp.getNoidung());
                sp.setKhuyenmai(mgsp.getKhuyenmai());
                sp.setGiasp(mgsp.getGiasp());
                sp.setTensp(mgsp.getTensp());
                Intent intent = new Intent(context, ChiTietSPActivity.class);
                intent.putExtra("sanpham", sp);
                intent.putExtra("img", mgsp.getImghinhsp());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsrandsp.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imghinhRandsp;
        TextView txttenspRand;
        TextView txtgiaspRand;
        CardView cardViewRand;
        public Holder(@NonNull View itemView) {
            super(itemView);
            cardViewRand = itemView.findViewById(R.id.cardviewRand);
            imghinhRandsp = itemView.findViewById(R.id.imgspRand);
            txttenspRand = itemView.findViewById(R.id.txttenspRand);
            txtgiaspRand = itemView.findViewById(R.id.txtgiaspRand);
        }
    }
}
