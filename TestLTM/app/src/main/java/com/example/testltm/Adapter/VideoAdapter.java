package com.example.testltm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testltm.R;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.Holder> {
    private Context context;
    private ArrayList<String> listClient;

    public VideoAdapter() {
    }

    public VideoAdapter(Context context, ArrayList<String> listClient) {
        this.context = context;
        this.listClient = listClient;
    }

    @NonNull
    @Override
    public VideoAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.client_call, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.Holder holder, int position) {
        //Video
    }

    @Override
    public int getItemCount() {
        return listClient.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        VideoView videoView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            videoView =itemView.findViewById(R.id.videoView);
        }
    }
}
