package com.example.applicationeot.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationeot.Activity.GioHangActivity;
import com.example.applicationeot.Activity.MainActivity;
import com.example.applicationeot.Models.GioHang;
import com.example.applicationeot.R;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static java.lang.String.valueOf;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.Holder> {
    private Context context;
    private ArrayList<GioHang> dsgh;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public GioHangAdapter() {
    }

    public GioHangAdapter(Context context, ArrayList<GioHang> dsgh) {
        this.context = context;
        this.dsgh = dsgh;
    }

    public void saveCart() {
        Gson gson = new Gson();
        SharedPreferences preferences = context.getSharedPreferences("GioHang", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String json = gson.toJson(MainActivity.dsgiohang);
        editor.putString("GioHang",json);
        editor.commit();
    }

    @NonNull
    @Override
    public GioHangAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_giohang, parent, false);
        Holder holder = new Holder(view, onItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GioHangAdapter.Holder holder, final int position) {
        GioHang gioHang = dsgh.get(position);
        holder.txttengh.setText(gioHang.getTenspgh()+"("+gioHang.getKmgh()+"%)");
        holder.txtgiagh.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(Long.parseLong(gioHang.getGiaspgh())) + " VNĐ");
        holder.imggh.setImageBitmap(gioHang.getImghinhspgh());
        holder.txtsl.setText(gioHang.getSoluongsp() + "");
        int soluong = Integer.parseInt(holder.txtsl.getText().toString());
        if (soluong >= 10){
            holder.btnmax.setVisibility(View.INVISIBLE);
            holder.btnmin.setVisibility(View.VISIBLE);
        }else if(soluong <= 1){
            holder.btnmin.setVisibility(View.INVISIBLE);
        }else if(soluong >= 1){
            holder.btnmax.setVisibility(View.VISIBLE);
            holder.btnmin.setVisibility(View.VISIBLE);
        }
        holder.btnmax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slnew = Integer.parseInt(holder.txtsl.getText().toString()) + 1;
                int slcurrent = MainActivity.dsgiohang.get(position).getSoluongsp();
                long giaht = Long.parseLong(MainActivity.dsgiohang.get(position).getGiaspgh());
                MainActivity.dsgiohang.get(position).setSoluongsp(slnew);
                long gianew = (giaht * slnew) / slcurrent;
                MainActivity.dsgiohang.get(position).setGiaspgh(String.valueOf(gianew));
                holder.txtgiagh.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(gianew) + " VNĐ");
                GioHangActivity.EvenUntils();
                if (slnew > 9){
                    holder.btnmax.setVisibility(View.INVISIBLE);
                    holder.btnmin.setVisibility(View.VISIBLE);
                    holder.txtsl.setText(String.valueOf(slnew));
                }else {
                    holder.btnmax.setVisibility(View.VISIBLE);
                    holder.btnmin.setVisibility(View.VISIBLE);
                    holder.txtsl.setText(String.valueOf(slnew));
                }
//                saveCart();
            }
        });
        holder.btnmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slnew = Integer.parseInt(holder.txtsl.getText().toString()) - 1;
                int slcurrent = MainActivity.dsgiohang.get(position).getSoluongsp();
                long giaht = Long.parseLong(MainActivity.dsgiohang.get(position).getGiaspgh());
                MainActivity.dsgiohang.get(position).setSoluongsp(slnew);
                long gianew = (giaht * slnew) / slcurrent;
                MainActivity.dsgiohang.get(position).setGiaspgh(String.valueOf(gianew));
                holder.txtgiagh.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(gianew) + " VNĐ");
                GioHangActivity.EvenUntils();
                if (slnew < 2){
                    holder.btnmax.setVisibility(View.VISIBLE);
                    holder.btnmin.setVisibility(View.INVISIBLE);
                    holder.txtsl.setText(String.valueOf(slnew));
                }else {
                    holder.btnmax.setVisibility(View.VISIBLE);
                    holder.btnmin.setVisibility(View.VISIBLE);
                    holder.txtsl.setText(String.valueOf(slnew));
                }
//                saveCart();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsgh.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView txttengh, txtgiagh, txtsl, txtdeleteitem;
        public ImageView imggh;
        public Button btnmin, btnmax;
        public Holder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            txttengh = itemView.findViewById(R.id.txttengiohang);
            txtgiagh = itemView.findViewById(R.id.txtgiagiohang);
            imggh = itemView.findViewById(R.id.imggiohang);
            btnmin = itemView.findViewById(R.id.btnmin);
            btnmax = itemView.findViewById(R.id.btnmax);
            txtsl = itemView.findViewById(R.id.txtsl);
            txtdeleteitem = itemView.findViewById(R.id.txtdeleteitemgh);
            txtdeleteitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
