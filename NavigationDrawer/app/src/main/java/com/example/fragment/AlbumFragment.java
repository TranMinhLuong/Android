package com.example.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.navigationdrawer.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AlbumFragment extends Fragment {
    private ImageView imghinh;
    private CheckBox cbauto;
    private ImageButton btnpre, btnnext;
    private int currentposition = -1;
    private ArrayList<String> dshinh;
    private Timer timer = null;
    private TimerTask timerTask = null;

    public AlbumFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dshinh = new ArrayList<>();
        dshinh.add("https://i.pinimg.com/originals/6e/f9/18/6ef918d3efd7ef140c2287f033d98f8b.jpg");
        dshinh.add("https://i.pinimg.com/236x/e3/3b/d7/e33bd7ff8f8bbe5f4e6cc3e8b11f7984.jpg");
        dshinh.add("https://i.pinimg.com/236x/8c/3c/fe/8c3cfe49c31cfa8665b45c1c6f15f653.jpg");
        dshinh.add("https://i.pinimg.com/236x/85/a6/2b/85a62beead2e96ff2d8889f75e15ddba.jpg");
        dshinh.add("https://i.pinimg.com/236x/69/e6/fb/69e6fbb86672c95bde52eaeb51d062bc.jpg");
        dshinh.add("https://i.pinimg.com/236x/1c/e1/47/1ce1473cad4753dc4796b6ffdf0f620c.jpg");
        dshinh.add("https://i.pinimg.com/236x/2b/e5/80/2be580b91d11c077da7846e83d5c717d.jpg");
        dshinh.add("https://i.pinimg.com/236x/59/16/40/591640e531aa7f685dd4aa7b46c78878.jpg");
        currentposition = 0;

        ImageTask task = new ImageTask();
        task.execute(dshinh.get(currentposition));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.album_fragment, container, false);

        imghinh = view.findViewById(R.id.imghinh);
        cbauto = view.findViewById(R.id.ckbauto);
        btnpre = view.findViewById(R.id.imgpre);
        btnnext = view.findViewById(R.id.imgnext);

        addEvents();

        return view;
    }

    private void addEvents() {
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulynext();
            }
        });
        btnpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyprevius();
            }
        });
        cbauto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    btnpre.setEnabled(false);
                    btnnext.setEnabled(false);


                }else {
                    btnpre.setEnabled(true);
                    btnnext.setEnabled(true);

                    if (timer!=null)
                        timer.cancel();
                }
            }
        });
        cbauto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    btnpre.setEnabled(false);
                    btnnext.setEnabled(false);

                    xulyauto();
                }else {
                    btnpre.setEnabled(true);
                    btnnext.setEnabled(true);

                    if (timer!=null)
                        timer.cancel();
                }
            }
        });
    }

    private void xulyauto() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                currentposition++;
                if (currentposition == dshinh.size())
                    currentposition = 0;
                ImageTask task = new ImageTask();
                task.execute(dshinh.get(currentposition));
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 0, 5000);
    }


    private void xulyprevius() {
        currentposition--;
        if (currentposition==-1)
            currentposition = dshinh.size()-1;
        ImageTask task = new ImageTask();
        task.execute(dshinh.get(currentposition));
    }

    private void xulynext() {
        currentposition++;
        if (currentposition==dshinh.size()-1)
            currentposition = 0;
        ImageTask task = new ImageTask();
        task.execute(dshinh.get(currentposition));
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
            Bitmap bitmap = null;
            try {
                String link = params[0];
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(link).getContent());
                return bitmap;
            }catch (Exception e){
                Log.e("LOI",e.toString());
            }
            return null;
        }
    }
}
