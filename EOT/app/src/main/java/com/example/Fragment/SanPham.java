package com.example.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.SanPhamAdapter;
import com.example.Models.SanPhamMG;
import com.example.Task.TaskSanPhamMG;
import com.example.eot.MainActivity;
import com.example.eot.R;

import java.util.ArrayList;

public class SanPham extends Fragment {

    private Context context;
    private RecyclerView recyVer;
    private ArrayList<SanPhamMG> listspVer;
    private SanPhamAdapter adapterspVer;

    public SanPham() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SanPhamShow sanPhamShow = new SanPhamShow();
        sanPhamShow.execute();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sanpham, container, false);
        recyVer = view.findViewById(R.id.recyVer);
        listspVer = new ArrayList<>();
        addEvents();
        return view;
    }


    private void addEvents() {

    }

    class SanPhamShow extends TaskSanPhamMG {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(ArrayList<SanPhamMG> sanPhamMGS) {
            if (sanPhamMGS != null){
                adapterspVer = new SanPhamAdapter(context, sanPhamMGS);
                recyVer.setAdapter(adapterspVer);
                LinearLayoutManager manager = new LinearLayoutManager(context);
                RecyclerView.LayoutManager layoutManager = manager;
                recyVer.setLayoutManager(layoutManager);
            }
            super.onPostExecute(sanPhamMGS);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
