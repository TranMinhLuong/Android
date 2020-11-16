package com.example.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.basicproject1.MainActivity;
import com.example.basicproject1.R;
import com.example.models.Music;

import java.util.List;

public class MusicAdapter extends ArrayAdapter<Music> {
    Activity context;
    int resource;
    @NonNull List<Music> objects;
    public MusicAdapter(@NonNull Activity context, int resource, @NonNull List<Music> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);
        TextView txtid = row.findViewById(R.id.txtid);
        TextView txtsong = row.findViewById(R.id.txtsong);
        TextView txtsing = row.findViewById(R.id.txtsing);
        ImageButton btnlike = row.findViewById(R.id.btnlike);
        ImageButton btndislike = row.findViewById(R.id.btndislike);

        final Music music = this.objects.get(position);
        //music.setIdsong(music.getIdsong());
        txtid.setText(music.getIdsong());
        txtsong.setText(music.getNamesong());
        txtsing.setText(music.getSingger());

        if (music.isLike()){
            btnlike.setVisibility(View.INVISIBLE);
            btndislike.setVisibility(View.VISIBLE);
        }else {
            btnlike.setVisibility(View.VISIBLE);
            btndislike.setVisibility(View.INVISIBLE);
        }

        btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyThich(music);
            }
        });

        btndislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyKhongThich(music);
            }
        });

        return row;
    }

    private void xulyKhongThich(Music music) {

    }

    private void xulyThich(Music music) {
        ContentValues row = new ContentValues();
        row.put("YEUTHICH", 1);
        MainActivity.database.update("ListMusic", row, "MABH=?", new String[]{music.getIdsong()});
    }

}
