package com.example.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.models.DoVat;
import com.example.sqliteimage.MainActivity;
import com.example.sqliteimage.R;

import java.util.List;

public class DoVatAdapters extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<DoVat> listdovat;

    public DoVatAdapters(MainActivity context, int layout, List<DoVat> listdovat) {
        this.context = context;
        this.layout = layout;
        this.listdovat = listdovat;
    }

    @Override
    public int getCount() {
        return listdovat.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTen, txtMota;
        ImageView imagehinh;
        ImageButton imgbsua, imgbxoa;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTen     = view.findViewById(R.id.txttendv);
            holder.txtMota    = view.findViewById(R.id.txtmotadv);
            holder.imagehinh  = view.findViewById(R.id.imagehinh);
            holder.imgbsua    = view.findViewById(R.id.imgbsua);
            holder.imgbxoa    = view.findViewById(R.id.imgbxoa);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        final DoVat doVat = listdovat.get(i);

        holder.txtMota.setText(doVat.getMota());
        holder.txtTen.setText(doVat.getTen());

        //chuyển mảng byte[] sang bitmap
        byte[] hinhanh = doVat.getHinhanh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
        holder.imagehinh.setImageBitmap(bitmap);

        //bắt sự kiên xóa và sửa
        holder.imgbsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogSuaDoVat(doVat.getTen(), doVat.getMota(), doVat.getId());
            }
        });

        return view;
    }
}
