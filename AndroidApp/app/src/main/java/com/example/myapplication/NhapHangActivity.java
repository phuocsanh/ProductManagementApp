package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.myapplication.Adapter.AddNhapHangAdapter;
import com.example.myapplication.DTO.HangHoaDTO;
import com.example.myapplication.DTO.NhanVienDTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NhapHangActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private ArrayList<HangHoaDTO> hangHoaDTOS;
    private EditText editTextSoLuongNhap;
    private NhanVienDTO nhanVienDTO;
    private AddNhapHangAdapter addNhapHangAdapter;
    private ImageView imageViewRemove, imageViewAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_hang);

        recyclerView = findViewById(R.id.recyclerview_nhaphang18);
        editTextSoLuongNhap = findViewById(R.id.editext_soluong18);
        linearLayout = findViewById(R.id.linear_chonhangnhap18);
        toolbar = findViewById(R.id.toolbar_nhapkho18);
        imageViewRemove = findViewById(R.id.imageView_remove18);
        imageViewAdd = findViewById(R.id.imageView_add18);


        recyclerView.setLayoutManager(new LinearLayoutManager(NhapHangActivity.this));
        recyclerView.setHasFixedSize(true);

        Intent intent = getIntent();
        nhanVienDTO = (NhanVienDTO) intent.getSerializableExtra("nhanvien");

        toolbar.setNavigationIcon(R.drawable.ic_close);
        setSupportActionBar(toolbar);
        setTitle("Nhập hàng");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NhapHangActivity.this, ChonHangkiemActivity.class);
                startActivityForResult(intent, 132);
            }
        });
        hangHoaDTOS = new ArrayList<>();

        imageViewRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int empty = Integer.parseInt(editTextSoLuongNhap.getText().toString().trim());
                if (empty>0){
                    empty--;
                    editTextSoLuongNhap.setText(empty+"");
                }
            }
        });
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int empty = Integer.parseInt(editTextSoLuongNhap.getText().toString().trim());
                empty++;
                editTextSoLuongNhap.setText(empty+"");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 132) {
            if (resultCode == RESULT_OK) {
                hangHoaDTOS.add((HangHoaDTO) data.getSerializableExtra("chonhangkiem"));
                setAdapterRecyclerView();

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save_:
                String x = editTextSoLuongNhap.getText().toString().trim();
                addNhapHang(hangHoaDTOS.get(0).getCOLUMN_ID(),nhanVienDTO.getCOLUM_ID(),hangHoaDTOS.get(0).getCOLUMN_TONKHO(),x);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setAdapterRecyclerView() {
        if (hangHoaDTOS.size() > 0) {
            recyclerView.setBackgroundResource(R.drawable.radius);
        }
        addNhapHangAdapter = new AddNhapHangAdapter(NhapHangActivity.this, hangHoaDTOS);
        recyclerView.setAdapter(addNhapHangAdapter);
    }

    private void addNhapHang(String maHH, String maNV, String soLuongBD, String soLuongNhap) {
        String url = "http://192.168.1.10/website1/addNhapHang.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "update thanh cong");
                        int y = Integer.parseInt(soLuongBD) + Integer.parseInt(soLuongNhap);
                        updateSLHangHoa(maHH, String.valueOf(y));
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
                param.put("mahanghoa", maHH);
                param.put("manhanvien", maNV);
                param.put("soluongbandau", soLuongBD);
                param.put("soluongnhap", soLuongNhap);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(NhapHangActivity.this);
        rq.add(request);
    }

    private void updateSLHangHoa(String maHH, String soLuongNhap) {
        String url = "http://192.168.1.10/website1/updateSoLuongHangHoa.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "update thanh cong");
                        finish();
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
                param.put("idhanghoa", maHH);
                param.put("soluong", soLuongNhap);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(NhapHangActivity.this);
        rq.add(request);
    }
}