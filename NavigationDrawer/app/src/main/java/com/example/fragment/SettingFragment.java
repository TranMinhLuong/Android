package com.example.fragment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.navigationdrawer.MainActivity;
import com.example.navigationdrawer.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class SettingFragment extends Fragment {
    private Button btnload;
    private ImageView imghinh;

    String link = "https://i.pinimg.com/originals/d6/b6/74/d6b674b8eb278e3f3dabb3d2e53bd577.jpg";
    String link1 = "https://i.pinimg.com/474x/d9/e3/42/d9e3428a83eb951525da8228d3368a41.jpg";
    String link2 = "https://i.pinimg.com/originals/f9/11/95/f91195a1aecab06176b686553dea80a9.jpg";
    String link3 = "https://i.pinimg.com/originals/6e/f9/18/6ef918d3efd7ef140c2287f033d98f8b.jpg";

    ArrayList<String> dshinh;
    Random random = new Random();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dshinh = new ArrayList<>();
        dshinh.add(link);
        dshinh.add(link1);
        dshinh.add(link2);
        dshinh.add(link3);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment, container, false);

        btnload = view.findViewById(R.id.btnload);
        imghinh = view.findViewById(R.id.imghinh);

        addEvents();

        return view;
    }

    private void addEvents() {
        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taihinh();
            }
        });
    }

    private void taihinh() {
        int n = random.nextInt(4);
        ImageTask task = new ImageTask();
        task.execute(dshinh.get(n));
    }

    class ImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imghinh.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                String link = params[0];
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(link).getContent());
                return bitmap;

            }catch (Exception e){
                Log.e("Lỗi",e.toString());
            }
            return null;
        }
    }
}
