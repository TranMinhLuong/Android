package com.example.giuaki.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giuaki.Models.MonAn;
import com.example.giuaki.R;

import java.util.ArrayList;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.Holder> {
    private Context context;
    private ArrayList<MonAn> dsmonan;

    public MonAnAdapter() {
    }

    public MonAnAdapter(Context context, ArrayList<MonAn> dsmonan) {
        this.context = context;
        this.dsmonan = dsmonan;
    }

    @NonNull
    @Override
    public MonAnAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.itemsp, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnAdapter.Holder holder, int position) {
        MonAn monAn = dsmonan.get(position);
        holder.imghinh.setImageResource(monAn.getImghinh());
        holder.txttenmon.setText(monAn.getTenmon());
    }

    @Override
    public int getItemCount() {
        return dsmonan.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imghinh;
        TextView txttenmon;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imghinh = itemView.findViewById(R.id.imgHinhSp);
            txttenmon = itemView.findViewById(R.id.txtTenSP);
        }
    }
}
