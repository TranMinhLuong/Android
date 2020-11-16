package com.example.applicationeot.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationeot.Models.GioHang;
import com.example.applicationeot.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ThanhToanAdapter extends RecyclerView.Adapter<ThanhToanAdapter.Holder> {
    private Context context;
    private ArrayList<GioHang> dsgh;

    public ThanhToanAdapter() {
    }

    public ThanhToanAdapter(Context context, ArrayList<GioHang> dsgh) {
        this.context = context;
        this.dsgh = dsgh;
    }

    @NonNull
    @Override
    public ThanhToanAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_thanhtoan, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhToanAdapter.Holder holder, int position) {
        GioHang gioHang = dsgh.get(position);
        holder.txtsoluongtt.setText(gioHang.getSoluongsp()+"x ");
        holder.txttensptt.setText(gioHang.getTenspgh()+"("+gioHang.getKmgh()+"%)");
        holder.txtgiasptt.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(Long.parseLong(gioHang.getGiaspgh())) + " VNĐ");
    }

    @Override
    public int getItemCount() {
        return dsgh.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView txtsoluongtt, txttensptt, txtgiasptt;
        public Holder(@NonNull View itemView) {
            super(itemView);
            txtsoluongtt = itemView.findViewById(R.id.txtsoluongtt);
            txttensptt = itemView.findViewById(R.id.txttensptt);
            txtgiasptt = itemView.findViewById(R.id.txtgiasptt);
        }
    }
}
