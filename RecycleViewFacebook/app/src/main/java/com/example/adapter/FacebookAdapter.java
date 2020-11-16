package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.models.FacebookManager;
import com.example.recycleviewfacebook.MainActivity;
import com.example.recycleviewfacebook.R;

import java.util.ArrayList;
import java.util.List;

public class FacebookAdapter extends RecyclerView.Adapter<FacebookAdapter.Holder> {

    private Context context;
    private ArrayList<FacebookManager> listfb;

    public FacebookAdapter (Context context, ArrayList<FacebookManager> listfb){
        this.context = context;
        this.listfb = listfb;
    }


    @NonNull
    @Override
    public FacebookAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FacebookAdapter.Holder holder, int position) {
        FacebookManager facebookManager = listfb.get(position);
        holder.imgava.setImageResource(facebookManager.getAvatar());
        holder.imghinh.setImageResource(facebookManager.getHinhanh());
        holder.txtstatus.setText(facebookManager.getStatus());
        holder.txtname.setText(facebookManager.getTen());
        holder.txttime.setText(facebookManager.getMinutes());
        holder.btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, holder.btnlike.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listfb.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView imgava;
        ImageView imghinh;
        TextView txtname;
        TextView txttime;
        TextView txtstatus;
        Button btnlike;

        public Holder(@NonNull View itemView) {
            super(itemView);

            imgava = itemView.findViewById(R.id.imgava);
            imghinh = itemView.findViewById(R.id.imghinh);
            txtname = itemView.findViewById(R.id.txtname);
            txttime = itemView.findViewById(R.id.txttime);
            txtstatus = itemView.findViewById(R.id.txtstatus);
            btnlike = itemView.findViewById(R.id.btnlike);
        }
    }
}
