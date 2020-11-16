package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Models.CommentSanPham;
import com.example.Models.SanPhamMG;
import com.example.eot.R;

import java.util.ArrayList;

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.Holder> {

    private Context context;
    private ArrayList<CommentSanPham> listcomment;

    public BinhLuanAdapter(Context context, ArrayList<CommentSanPham> listcomment) {
        this.context = context;
        this.listcomment = listcomment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_binhluan,parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuanAdapter.Holder holder, int position) {
        CommentSanPham commentSanPham = listcomment.get(position);
        holder.txtnguoibl.setText(commentSanPham.getTenkh());
        holder.txtngaybl.setText(commentSanPham.getCreated());
        holder.txtcomment.setText(commentSanPham.getComment());
        holder.ratingBar.setRating(Float.parseFloat(commentSanPham.getRating()));
    }

    @Override
    public int getItemCount() {
        return listcomment.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txtnguoibl;
        TextView txtngaybl;
        RatingBar ratingBar;
        TextView txtcomment;
        public Holder(@NonNull View itemView) {
            super(itemView);

            txtcomment = itemView.findViewById(R.id.txtcomment);
            txtnguoibl = itemView.findViewById(R.id.txtnguoibl);
            txtngaybl = itemView.findViewById(R.id.txtngaybl);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
