package com.example.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Adapter.BinhLuanAdapter;
import com.example.Models.CommentSanPham;
import com.example.Models.SanPhamMG;
import com.example.Task.TaskCommentSP;
import com.example.eot.R;

import java.util.ArrayList;

public class ChiTietSP extends AppCompatActivity {

    private RecyclerView recyclerViewbl;
    private BinhLuanAdapter adaptercm;
    private ImageView imgchitietsp;
    private TextView tenchitietsp;
    private TextView noidungchitiet;
    private TextView txtgiachitietsp;
    private TextView txtgiamgiachitietsp;
    private TextView txtnhacungcap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiet_item);

        addControls();
        addEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        SanPhamMG sanPhamMG = (SanPhamMG) intent.getSerializableExtra("sanpham");
        sanPhamMG.setImghinhsp((Bitmap) intent.getParcelableExtra("img"));
        imgchitietsp.setImageBitmap(sanPhamMG.getImghinhsp());
        txtgiachitietsp.setText(sanPhamMG.getGiasp());
        int giamgia = (int) ((1 - (int) sanPhamMG.getKhuyenmai()*1.0/100) * Integer.parseInt(sanPhamMG.getGiasp()));
        txtgiamgiachitietsp.setText(String.valueOf(giamgia));
        txtgiachitietsp.setText(sanPhamMG.getGiasp());
        noidungchitiet.setText(sanPhamMG.getNoidung());
        txtnhacungcap.setText(sanPhamMG.getNhacungcap());
        tenchitietsp.setText(sanPhamMG.getTensp());
        BinhLuan binhLuan = new BinhLuan();
        binhLuan.execute(String.valueOf(sanPhamMG.getId()));
    }

    private void addEvent() {

    }

    private void addControls() {
        recyclerViewbl = findViewById(R.id.recyBinhluan);

        imgchitietsp = findViewById(R.id.imghinhchitietsp);
        tenchitietsp = findViewById(R.id.txttenchitietsp);
        noidungchitiet = findViewById(R.id.txtnoidungchitiet);
        txtgiachitietsp = findViewById(R.id.txtgiachitietsp);
        txtgiamgiachitietsp = findViewById(R.id.txtgiamgiachitiet);
        txtnhacungcap = findViewById(R.id.txtnhacungcap);
        txtgiachitietsp.setPaintFlags(txtgiamgiachitietsp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    class BinhLuan extends TaskCommentSP{
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(ChiTietSP.this);
            dialog.setTitle("Loading");
            dialog.setMessage("Loading");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<CommentSanPham> commentSanPhams) {
            super.onPostExecute(commentSanPhams);
            if (commentSanPhams != null){
                adaptercm = new BinhLuanAdapter(ChiTietSP.this, commentSanPhams);
                recyclerViewbl.setAdapter(adaptercm);
                LinearLayoutManager manager = new LinearLayoutManager(ChiTietSP.this);
                RecyclerView.LayoutManager layoutManager = manager;
                recyclerViewbl.setLayoutManager(layoutManager);
                dialog.dismiss();
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
