package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.XuatHangAdapter;
import com.example.myapplication.DTO.HangHoaDTO;
import com.example.myapplication.DTO.NhanVienDTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import maes.tech.intentanim.CustomIntent;

import static com.example.myapplication.KhachHangActivity.GET_ID_KHACHHANG;
import static com.example.myapplication.KhachHangActivity.GET_TEN_KHACHHANG;

public class XuatHangActivity extends AppCompatActivity {

    private ArrayList<HangHoaDTO> hangHoaDTOS;
    private RecyclerView recyclerView;
    private XuatHangAdapter xuatHangAdapter;
    private Toolbar toolbar;
    private Button buttonThanhToan;

    private ConstraintLayout constraintThemKH;
    private TextView textViewtenKH, textViewTongTien;
    private String maHoaDon;
    public static final int REQUETCODE_KHACHHANG = 555;
    private int tongTien = 0;
    private NhanVienDTO nhanVienDTO;
    private String idKhachHang;
    private CoordinatorLayout coordinatorLayout;
    private TextView textviewtenKhachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuat_hang);
        recyclerView = findViewById(R.id.recyclerview_xuatHangHoa3);
        toolbar = findViewById(R.id.toolbar_xuatHangActivity);
        buttonThanhToan = findViewById(R.id.button_thanhToan3);
        coordinatorLayout = findViewById(R.id.coordinator_xuathang);
        constraintThemKH = findViewById(R.id.constraint_khachHang3);
        textViewtenKH = findViewById(R.id.textView_tenKhachHang3);
        textViewTongTien = findViewById(R.id.textView_tongTienHang3);
        textviewtenKhachHang = findViewById(R.id.textView_tenKhachHang3);


        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        hangHoaDTOS = (ArrayList<HangHoaDTO>) intent.getSerializableExtra("dsHangCanXuat");
        nhanVienDTO = (NhanVienDTO) intent.getSerializableExtra("object_nhanvien");

        Log.d("vvv", nhanVienDTO.getCOLUM_TENDANGNHAP());

        for (int i = 0; i < hangHoaDTOS.size(); i++) {
            if (Integer.parseInt(hangHoaDTOS.get(i).getCOLUMN_TONKHO()) > 0) {
                hangHoaDTOS.get(i).setCOLUMN_SOLUONGHD("1");
                int x = Integer.parseInt(hangHoaDTOS.get(i).getCOLUMN_GIABAN()) * Integer.parseInt(hangHoaDTOS.get(i).getCOLUMN_SOLUONGHD());
                tongTien += x;
            } else {
                hangHoaDTOS.get(i).setCOLUMN_SOLUONGHD("0");
            }
        }
        textViewTongTien.setText(tongTien + "");

        recyclerView.setLayoutManager(new LinearLayoutManager(XuatHangActivity.this));
        recyclerView.setHasFixedSize(true);
        xuatHangAdapter = new XuatHangAdapter(XuatHangActivity.this, hangHoaDTOS);
        recyclerView.setAdapter(xuatHangAdapter);

        xuatHangAdapter.setOnItemClickListener(new XuatHangAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(String id, String vitri, View view, String sl) {

                if (Integer.parseInt(sl) < Integer.parseInt(hangHoaDTOS.get(Integer.parseInt(vitri)).getCOLUMN_SOLUONGHD())) {
                    tongTien -= Integer.parseInt(hangHoaDTOS.get(Integer.parseInt(vitri)).getCOLUMN_GIABAN());
                } else {
                    tongTien += Integer.parseInt(hangHoaDTOS.get(Integer.parseInt(vitri)).getCOLUMN_GIABAN());
                }
                hangHoaDTOS.get(Integer.parseInt(vitri)).setCOLUMN_SOLUONGHD(sl);
                textViewTongTien.setText(tongTien + "");
            }
        });

        buttonThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHoaDon();
            }
        });

        constraintThemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XuatHangActivity.this, KhachHangActivity.class);
                startActivityForResult(intent, REQUETCODE_KHACHHANG);
                CustomIntent.customType(XuatHangActivity.this, "left-to-right");

            }
        });

    }

    //    -----------------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUETCODE_KHACHHANG) {
            if (resultCode == RESULT_OK) {
                idKhachHang = data.getStringExtra(GET_ID_KHACHHANG);
                textviewtenKhachHang.setText(data.getStringExtra(GET_TEN_KHACHHANG));
            }
        }
    }

    private void addHoaDon() {
        String url = "http://192.168.1.10/website1/addHoaDon.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    maHoaDon = jsonObject.getString("mahoadon");
                    for (int i = 0; i < hangHoaDTOS.size(); i++) {
                        if (Integer.parseInt(hangHoaDTOS.get(i).getCOLUMN_SOLUONGHD()) <= 0) {
                            Toast.makeText(XuatHangActivity.this, "Không thể thêm hàng hóa có số lượng là 0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        addHoaDonChiTiet(maHoaDon, hangHoaDTOS.get(i).getCOLUMN_ID(), hangHoaDTOS.get(i).getCOLUMN_SOLUONGHD());
                        int x = Integer.parseInt(hangHoaDTOS.get(i).getCOLUMN_TONKHO()) - Integer.parseInt(hangHoaDTOS.get(i).getCOLUMN_SOLUONGHD());
                        updateHangHoa(hangHoaDTOS.get(i), x + "");
                    }
                    finish();
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
                param.put("makhachhang", idKhachHang);
                param.put("manhanvien", nhanVienDTO.getCOLUM_ID());
                param.put("ghichu", "hit");
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(XuatHangActivity.this);
        rq.add(request);

    }

    private void addHoaDonChiTiet(String mahoadon, String mahanghoa, String soluong) {
        String url = "http://192.168.1.10/website1/addHoaDonChiTiet.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
//                        Log.d("vvv", "add thanh cong");
                        Toast.makeText(XuatHangActivity.this, "add thanh cong", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(XuatHangActivity.this, "add that bai", Toast.LENGTH_SHORT).show();
                    }
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
                param.put("mahoadon", mahoadon);
                param.put("masanpham", mahanghoa);
                param.put("soluong", soluong);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(XuatHangActivity.this);
        rq.add(request);
    }

    private void updateHangHoa(HangHoaDTO hangHoaDTO, String soluong) {
        String idHangHoa = hangHoaDTO.getCOLUMN_ID();
        String url = "http://192.168.1.10/website1/updateSoLuongHangHoa.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "update thanh cong");
                    } else if (x.equalsIgnoreCase("2")) {
                        Log.d("vvv", "update thieu du lieu");
                    } else {
                        Log.d("vvv", "update khong thanh cong");
                    }
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
                param.put("idhanghoa", idHangHoa);
                param.put("soluong", soluong);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(XuatHangActivity.this);
        rq.add(request);
    }

}