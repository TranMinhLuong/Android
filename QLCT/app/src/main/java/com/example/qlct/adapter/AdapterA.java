package com.example.qlct.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlct.ManHinh2Activity;
import com.example.qlct.R;
import com.example.qlct.models.ModelsA;

import java.util.ArrayList;

public class AdapterA extends RecyclerView.Adapter<AdapterA.Holder> {
    Context context;
    ArrayList<ModelsA> ds;

    public AdapterA() {
    }

    public AdapterA(Context context, ArrayList<ModelsA> ds) {
        this.context = context;
        this.ds = ds;
    }

    @NonNull
    @Override
    public AdapterA.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterA.Holder holder, int position) {
        ModelsA modelsA = ds.get(position);
        holder.txt1.setText(modelsA.getTen());
        holder.txt2.setText(modelsA.getTuoi());
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txt1,txt2;
        public Holder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.txt1);
            txt2 = itemView.findViewById(R.id.txt2);
        }
    }
}
