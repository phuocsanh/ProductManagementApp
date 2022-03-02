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
import com.example.myapplication.Adapter.AddKiemKhoAdapter;
import com.example.myapplication.Adapter.HangHoaAdapter;
import com.example.myapplication.DTO.HangHoaDTO;
import com.example.myapplication.DTO.KiemKhoDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChiTietKiemKhoActivity extends AppCompatActivity {
    private TextView tenNV, thoiGianTao, noiTaoPhieu;
    private KiemKhoDTO kiemKhoDTO;
    private Toolbar toolbar;
    private AddKiemKhoAdapter addKiemKhoAdapter;
    private HangHoaDTO hangHoaDTO;
    private ArrayList<HangHoaDTO> hangHoaDTOS;
    private RecyclerView recyclerView;
    private TextView tvSLHangKiem, tvTongTonKho,tvTongLech, tvTongThucTe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_kiem_kho);

        tenNV = findViewById(R.id.textView_tenNhanVien16);
        thoiGianTao = findViewById(R.id.thoiGianTao16);
        noiTaoPhieu = findViewById(R.id.noiTaoPhieu16);
        toolbar = findViewById(R.id.toolbar_kk16);
        recyclerView = findViewById(R.id.recyclerview_kiemhang16);
        tvSLHangKiem = findViewById(R.id.textview_hangkiem16);
        tvTongTonKho = findViewById(R.id.textview_tonkho16);
        tvTongThucTe = findViewById(R.id.textview_thucte16);
        tvTongLech = findViewById(R.id.textview_lech16);

        Intent intent = getIntent();
        kiemKhoDTO = (KiemKhoDTO) intent.getSerializableExtra("kiemkho");

        thoiGianTao.setText(kiemKhoDTO.getCOLUM_TIMESTAMP());
        noiTaoPhieu.setText(kiemKhoDTO.getCOLUM_NOITAOPHIEU());

        getNVByID();

        toolbar.setNavigationIcon(R.drawable.ic_back);
        setTitle("KK000"+kiemKhoDTO.getCOLUM_ID());
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getAllHangHoa(kiemKhoDTO.getCOLUM_ID());

    }

    private void setTongHangKiem(){
        tvSLHangKiem.setText(hangHoaDTOS.size() + "");
        int tongton = 0, tongthucte = 0,tonglech = 0;
        for (int i = 0; i<hangHoaDTOS.size();i++){
            tongton+=Integer.parseInt(hangHoaDTOS.get(i).getCOLUMN_TONKHO());
            tongthucte+=Integer.parseInt(hangHoaDTOS.get(i).getCOLUMN_THUCTE());
        }
        tonglech = tongthucte - tongton;
        tvTongTonKho.setText(""+tongton);
        tvTongThucTe.setText(""+tongthucte);
        tvTongLech.setText(""+tonglech);
    }

    private void getAllHangHoa(String idkk) {
        String url = "http://192.168.1.10/website1/getHHByKK.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("hanghoa");

                    hangHoaDTOS = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        hangHoaDTO = new HangHoaDTO();
                        JSONObject hit = jsonArray.getJSONObject(i);
                        String entry = "";
                        entry = hit.getString("idhanghoa");
                        hangHoaDTO.setCOLUMN_ID(entry);
                        entry = hit.getString("idloaihang");
                        hangHoaDTO.setCOLUMN_LOAIHANG(entry);
                        entry = hit.getString("tenhanghoa");
                        hangHoaDTO.setCOLUMN_NAME(entry);
                        entry = hit.getString("vitri");
                        hangHoaDTO.setCOLUMN_VITRI(entry);
                        entry = hit.getString("hinhanh");
                        hangHoaDTO.setCOLUMN_HINHDAIDIEN(entry);
                        entry = hit.getString("soluong");
                        hangHoaDTO.setCOLUMN_TONKHO(entry);
                        entry = hit.getString("giaban");
                        hangHoaDTO.setCOLUMN_GIABAN(entry);
                        entry = hit.getString("giavon");
                        hangHoaDTO.setCOLUMN_GIAVON(entry);
                        entry = hit.getString("dinhmuctonmin");
                        hangHoaDTO.setCOLUMN_DINHMUCTON_MIN(entry);
                        entry = hit.getString("dinhmuctonmax");
                        hangHoaDTO.setCOLUMN_DINHMUCTON_MAX(entry);
                        entry = hit.getString("ghichu");
                        hangHoaDTO.setCOLUMN_GHICHU(entry);
                        entry = hit.getString("soluongbandau");
                        hangHoaDTO.setCOLUMN_TONKHO(entry);
                        entry = hit.getString("toncuoi");
                        hangHoaDTO.setCOLUMN_THUCTE(entry);

                        hangHoaDTOS.add(hangHoaDTO);
//                                Log.d("vvv", dsHangHoa.get(i).getCOLUMN_NAME());
                    }
                    setTongHangKiem();
                    Log.d("vvv", " hang hoa");
                    recyclerView.setLayoutManager(new LinearLayoutManager(ChiTietKiemKhoActivity.this));
                    recyclerView.setHasFixedSize(true);
                    addKiemKhoAdapter = new AddKiemKhoAdapter(ChiTietKiemKhoActivity.this, hangHoaDTOS);
                    recyclerView.setAdapter(addKiemKhoAdapter);

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
                param.put("idkiemkho", idkk);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(ChiTietKiemKhoActivity.this);
        rq.add(request);
    }

    private void getNVByID() {
        String url = "http://192.168.1.10/website1/getNhanVienByID.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("nhanvien");
                    JSONObject hit = jsonArray.getJSONObject(0);
                    String entry = "";
                    entry = hit.getString("tendangnhap");
                    tenNV.setText(entry);
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
                param.put("idnhanvien", kiemKhoDTO.getCOLUM_NHANVIEN());
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(ChiTietKiemKhoActivity.this);
        rq.add(request);
    }
}