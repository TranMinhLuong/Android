package com.example.adapters;

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

import com.example.appsv.R;
import com.example.models.Students;

import java.util.List;

public class StudentsAdapter extends ArrayAdapter<Students> {
    @NonNull Activity context;
    int resource;
    @NonNull List<Students> objects;
    public StudentsAdapter(@NonNull Activity context, int resource, @NonNull List<Students> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource =resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row =inflater.inflate(this.resource, null);
        ImageView imageView = row.findViewById(R.id.imgavatar);
        TextView txtid = row.findViewById(R.id.txtid);
        TextView txtname = row.findViewById(R.id.txtname);
        TextView txtho = row.findViewById(R.id.txtho);
        TextView txtgender = row.findViewById(R.id.txtgender);
        TextView txtbir = row.findViewById(R.id.txtbir);
        TextView txtaddr = row.findViewById(R.id.txtaddr);
        TextView txtdes = row.findViewById(R.id.txtdes);

        Students students = this.objects.get(position);
        imageView.setImageResource(students.getAvatar());
        txtid.setText(students.getMa());
        txtname.setText(students.getName());
        txtho.setText(students.getHo());
        txtgender.setText(students.getGender());
        txtbir.setText(students.getBirthday());
        txtaddr.setText(students.getAddress());
        txtdes.setText(students.getDescription());

        return row;
    }
}
