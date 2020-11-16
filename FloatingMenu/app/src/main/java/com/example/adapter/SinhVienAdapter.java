package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.floatingmenu.R;
import com.example.models.SinhVien;

import java.util.ArrayList;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.Holder> {

    private Context context;
    private ArrayList<SinhVien> listsv;

    public SinhVienAdapter (Context context, ArrayList<SinhVien> listsv){
        this.context = context;
        this.listsv = listsv;
    }

    @NonNull
    @Override
    public SinhVienAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienAdapter.Holder holder, final int position) {
        final SinhVien sinhVien =listsv.get(position);
        holder.imgava.setImageResource(sinhVien.getImgava());
        holder.txtTT.setText(sinhVien.getTen());
        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(context, v);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.floating_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.xem:
                                Toast.makeText(context,
                                        "Mã sinh viên: "+sinhVien.getMsv()+
                                                "\nTên sinh viên là: "+sinhVien.getTen()+
                                                "\nLớp: "+sinhVien.getLop(),Toast.LENGTH_LONG).show();
                                break;
                            case R.id.sua:
                                if (sinhVien.getMsv() == 1){
                                    listsv.remove(position);
                                    notifyDataSetChanged();
                                    listsv.add(new SinhVien(R.drawable.boruto, "Boruto", "Lôi thuật", 1));
                                    Toast.makeText(context, "Đã đổi tên lớp thành: "+"Lôi thuật", Toast.LENGTH_LONG).show();
                                }else if(sinhVien.getMsv() == 2){
                                    listsv.add(new SinhVien(R.drawable.naruto, "Naruto", "Phong thuật", 2));
                                    Toast.makeText(context, "Đã đổi tên lớp thành: "+"Phong thuật", Toast.LENGTH_LONG).show();
                                }else if(sinhVien.getMsv() == 3){
                                    listsv.remove(position);
                                    listsv.add(new SinhVien(R.drawable.itachi, "Itachi", "Ảo thuật", 3));
                                    Toast.makeText(context, "Đã đổi tên lớp thành: "+"Ảo thuật", Toast.LENGTH_LONG).show();
                                }else if(sinhVien.getMsv() == 4){
                                    listsv.add(new SinhVien(R.drawable.sasuke, "Sasuke", "Hỏa thuật", 4));
                                    Toast.makeText(context, "Đã đổi tên lớp thành"+"Hỏa thuật", Toast.LENGTH_LONG).show();
                                }
                                break;
                            case R.id.xoa:
                                listsv.remove(position);
                                notifyDataSetChanged();
                                break;
                            case R.id.them:
                                listsv.remove(position);
                                notifyDataSetChanged();
                                listsv.add(new SinhVien(R.drawable.flash, "The Flash", "Lôi thuật", 5));
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listsv.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        ImageView imgava;
        TextView txtTT;
        CardView item;
        public Holder(@NonNull View itemView) {
            super(itemView);

            imgava = itemView.findViewById(R.id.imgava);
            txtTT = itemView.findViewById(R.id.txtTT);
            item = itemView.findViewById(R.id.item);


        }
    }
}
