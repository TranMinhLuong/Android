package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.models.TyGia;
import com.example.tygiahoidoai.R;

import java.util.List;

public class TyGiaAdapter extends ArrayAdapter<TyGia> {
    Activity context;
    int resource;
    @NonNull List<TyGia> objects;
    public TyGiaAdapter(@NonNull Activity context, int resource, @NonNull List<TyGia> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(this.resource, null);

        TyGia tyGia = this.objects.get(position);

        ImageView imghinh = view.findViewById(R.id.imghinh);
        TextView txttype = view.findViewById(R.id.txttype);
        TextView txtmuatm = view.findViewById(R.id.txtdatamuatm);
        TextView txtbantm = view.findViewById(R.id.txtdatabantm);
        TextView txtbanck = view.findViewById(R.id.txtdatabanck);
        TextView txtmuack = view.findViewById(R.id.txtdatamuack);

        imghinh.setImageBitmap(tyGia.getBitmap());
        txttype.setText(tyGia.getType());
        txtmuatm.setText(tyGia.getMuatienmat());
        txtbantm.setText(tyGia.getBantienmat());
        txtbanck.setText(tyGia.getBanck());
        txtmuack.setText(tyGia.getMuack());

        return view;
    }
}
