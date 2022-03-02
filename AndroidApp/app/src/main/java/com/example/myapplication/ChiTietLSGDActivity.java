package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.ChiTietLSGDAdapter;
import com.example.myapplication.DTO.HangHoaDTO;
import com.example.myapplication.DTO.HoaDonDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChiTietLSGDActivity extends AppCompatActivity {
    private HoaDonDTO hoaDonDTO;
    private TextView tvTenKH, tvTimestamp, tvTongTien, tvNhanVien;
    private RecyclerView recyclerView;
    private HangHoaDTO hangHoaDTO;
    private ArrayList<HangHoaDTO> hangHoaDTOS;
    private ChiTietLSGDAdapter mAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_lsgdactivity);

        tvTenKH = findViewById(R.id.textView_tenKhachHang22);
        tvTimestamp = findViewById(R.id.textView_timestamp22);
        tvTongTien = findViewById(R.id.textView_tongTien22);
        tvNhanVien = findViewById(R.id.textView_nhanVien22);
        recyclerView = findViewById(R.id.rc_hanghoa22);
        toolbar = findViewById(R.id.toolbar_chitietlsgd22);

        recyclerView.setLayoutManager(new LinearLayoutManager(ChiTietLSGDActivity.this));
        recyclerView.setHasFixedSize(true);

        Intent intent = getIntent();
        hoaDonDTO = (HoaDonDTO) intent.getSerializableExtra("hoadon");

        toolbar.setNavigationIcon(R.drawable.ic_back);
        setTitle("HH000"+hoaDonDTO.getCOLUMN_ID());
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvTenKH.setText(hoaDonDTO.getCOLUMN_KH());
        tvTimestamp.setText(hoaDonDTO.getCOLUMN_THOIGIANTAO());
        tvTongTien.setText(hoaDonDTO.getCOLUMN_TONGTIEN());
        tvNhanVien.setText(hoaDonDTO.getCOLUMN_NV());
        getHangHoaTrongHoaDon(hoaDonDTO.getCOLUMN_ID());
    }

    private void getHangHoaTrongHoaDon(String maHD) {
        String url = "http://192.168.1.10/website1/getHangHoaTrongHoaDon.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    hangHoaDTOS = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("hanghoatronghoadon");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        hangHoaDTO = new HangHoaDTO();
                        JSONObject hit = jsonArray.getJSONObject(i);
                        String entry = "";
                        entry = hit.getString("idhanghoa");
                        hangHoaDTO.setCOLUMN_ID(entry);
                        entry = hit.getString("tenhanghoa");
                        hangHoaDTO.setCOLUMN_NAME(entry);
                        entry = hit.getString("giaban");
                        hangHoaDTO.setCOLUMN_GIABAN(entry);
                        entry = hit.getString("soluong");
                        hangHoaDTO.setCOLUMN_SOLUONGHD(entry);

                        hangHoaDTOS.add(hangHoaDTO);
                    }
                    mAdapter = new ChiTietLSGDAdapter(ChiTietLSGDActivity.this,hangHoaDTOS);
                    recyclerView.setAdapter(mAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("vvv", error.toString() + " err add");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("idhoadon", maHD);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(ChiTietLSGDActivity.this);
        rq.add(request);
    }
}