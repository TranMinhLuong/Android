package com.example.Adapter;

import android.annotation.SuppressLint;
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

import com.example.Models.SanPhamMG;
import com.example.Fragment.ChiTietSP;
import com.example.eot.R;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.Holder> {

    private Context context;
    private ArrayList<SanPhamMG> listspmg;

    public SanPhamAdapter(Context context, ArrayList<SanPhamMG> listspmg) {
        this.context = context;
        this.listspmg = listspmg;
    }

    @NonNull
    @Override
    public SanPhamAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_item, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.Holder holder, final int position) {
        final SanPhamMG sanPhamMG = listspmg.get(position);
        holder.imghinhsp.setImageBitmap(sanPhamMG.getImghinhsp());
        holder.txttensp.setText(sanPhamMG.getTensp());
        holder.txtgiasp.setText(sanPhamMG.getGiasp());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SanPhamMG sp = new SanPhamMG();
               sp.setId(sanPhamMG.getId());
               sp.setNhacungcap(sanPhamMG.getNhacungcap());
               sp.setNoidung(sanPhamMG.getNoidung());
               sp.setKhuyenmai(sanPhamMG.getKhuyenmai());
               sp.setGiasp(sanPhamMG.getGiasp());
               sp.setTensp(sanPhamMG.getTensp());
               Intent intent = new Intent(context,ChiTietSP.class);
               intent.putExtra("sanpham", sp);
               intent.putExtra("img", sanPhamMG.getImghinhsp());
               context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listspmg.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imghinhsp;
        TextView txttensp;
        TextView txtgiasp;
        CardView item;
        public Holder(@NonNull View itemView) {
            super(itemView);

            imghinhsp = itemView.findViewById(R.id.imghinhsp);
            txttensp = itemView.findViewById(R.id.txtTenSP);
            txtgiasp = itemView.findViewById(R.id.txtGiaSP);
            item = itemView.findViewById(R.id.item);
        }
    }
}
