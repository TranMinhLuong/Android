package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.animationlistview.R;

import java.util.List;

public class Adapter extends ArrayAdapter<String> {

    @NonNull
    Activity context;
    int resource;
    @NonNull List<String> objects;

    public Adapter(@NonNull Activity context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);
        TextView txtten = row.findViewById(R.id.txtname);
        txtten.setText(this.objects.get(position));

        Animation animation= AnimationUtils.loadAnimation(this.context, R.anim.hieuunglistview);
        row.startAnimation(animation);
        return row;
    }
}
