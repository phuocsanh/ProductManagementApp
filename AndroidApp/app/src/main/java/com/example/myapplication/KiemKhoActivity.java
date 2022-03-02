package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.bumptech.glide.Glide;
import com.example.myapplication.Adapter.AddKiemKhoAdapter;
import com.example.myapplication.DTO.HangHoaDTO;
import com.example.myapplication.DTO.NhanVienDTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KiemKhoActivity extends AppCompatActivity {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private ArrayList<HangHoaDTO> hangHoaDTOS;
    private AddKiemKhoAdapter addKiemKhoAdapter;
    private TextView tvSLHangKiem, tvTongTonKho,tvTongLech, tvTongThucTe;
    int empty = 0;
    private String maKiemKho;
    private NhanVienDTO nhanVienDTO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiem_kho);

        recyclerView = findViewById(R.id.recyclerview_kiemhang11);
        linearLayout = findViewById(R.id.linear_chonhangkiem11);
        toolbar = findViewById(R.id.toolbar_phieukiemhang);
        tvSLHangKiem = findViewById(R.id.textview_hangkiem11);
        tvTongTonKho = findViewById(R.id.textview_tonkho11);
        tvTongThucTe = findViewById(R.id.textview_thucte11);
        tvTongLech = findViewById(R.id.textview_lech11);

        recyclerView.setLayoutManager(new LinearLayoutManager(KiemKhoActivity.this));
        recyclerView.setHasFixedSize(true);

        Intent intent = getIntent();
        nhanVienDTO= (NhanVienDTO) intent.getSerializableExtra("nhanvien");

        toolbar.setNavigationIcon(R.drawable.ic_close);
        setSupportActionBar(toolbar);
        setTitle("Tạo phiếu kiểm");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KiemKhoActivity.this, ChonHangkiemActivity.class);
                startActivityForResult(intent, 123);
            }
        });
        hangHoaDTOS = new ArrayList<>();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                hangHoaDTOS.add((HangHoaDTO) data.getSerializableExtra("chonhangkiem"));
                setAdapterRecyclerView();
                setTongHangKiem();
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
                addKiemKho(nhanVienDTO.getCOLUM_ID(),"khi tạo phiếu kiểm kho");

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void dialogKiemKho(HangHoaDTO hangHoaDTO) {
        AlertDialog.Builder builder = new AlertDialog.Builder(KiemKhoActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_kiemkho, null);
        ImageView imHinh = view.findViewById(R.id.imageView_hinhHangHoa13);
        TextView tvTen = view.findViewById(R.id.textView_tenHangHoa13);
        TextView tvMa = view.findViewById(R.id.textView_maHangHoa13);
        TextView tvTonkho = view.findViewById(R.id.textView_tomKho13);
        TextView tvDaKiem = view.findViewById(R.id.textView_dakiem13);
        TextView tvChenhLech = view.findViewById(R.id.textView_chenhLech13);
        EditText etSoLuong = view.findViewById(R.id.editText_soLuong13);
        ImageView imGiamSL = view.findViewById(R.id.imageView_giamSoLuong13);
        ImageView imTangSL = view.findViewById(R.id.imageView_tangSoLuong13);
        Button btnGhiDe = view.findViewById(R.id.button_ghiDe13);
        Button btnCongThem = view.findViewById(R.id.button_congThem13);
        empty = Integer.parseInt(hangHoaDTO.getCOLUMN_THUCTE());

        Glide.with(KiemKhoActivity.this).load("http://192.168.1.10/website1/hinhsanpham/" +
                hangHoaDTO.getCOLUMN_HINHDAIDIEN()).into(imHinh);
        tvTen.setText(hangHoaDTO.getCOLUMN_NAME());
        tvMa.setText("SP000" + hangHoaDTO.getCOLUMN_ID());
        etSoLuong.setText(hangHoaDTO.getCOLUMN_THUCTE());

        builder.setView(view)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialog alert = builder.create();

        imGiamSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (empty>0){
                    empty--;
                    etSoLuong.setText(empty+"");
                }
            }
        });
        imTangSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                empty++;
                etSoLuong.setText(empty+"");
            }
        });

        btnGhiDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hangHoaDTO.setCOLUMN_THUCTE(etSoLuong.getText().toString());
                setAdapterRecyclerView();
                tvTongThucTe.setText(hangHoaDTO.getCOLUMN_THUCTE());
                setTongHangKiem();
                alert.cancel();
            }
        });
        btnCongThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = Integer.parseInt(hangHoaDTO.getCOLUMN_THUCTE());
                x+=Integer.parseInt(etSoLuong.getText().toString());
                hangHoaDTO.setCOLUMN_THUCTE(x+"");
                setAdapterRecyclerView();
                tvTongThucTe.setText(hangHoaDTO.getCOLUMN_THUCTE());
                setTongHangKiem();
                alert.cancel();
            }
        });

        tvTonkho.setText(hangHoaDTO.getCOLUMN_TONKHO());
        tvDaKiem.setText(hangHoaDTO.getCOLUMN_THUCTE());
        tvChenhLech.setText(Integer.parseInt(hangHoaDTO.getCOLUMN_THUCTE()) -Integer.parseInt(hangHoaDTO.getCOLUMN_TONKHO())+ "");

        alert.show();
    }

    private void setAdapterRecyclerView(){
        addKiemKhoAdapter = new AddKiemKhoAdapter(KiemKhoActivity.this, hangHoaDTOS);
        recyclerView.setAdapter(addKiemKhoAdapter);
        addKiemKhoAdapter.setOnItemClickListener(new AddKiemKhoAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(String id, String vitri) {
                dialogKiemKho(hangHoaDTOS.get(Integer.parseInt(vitri)));
            }
        });
    }

    private void addKiemKho(String maNV,String noitaophieu) {
        String url = "http://192.168.1.10/website1/addKiemKho.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    maKiemKho = jsonObject.getString("makiemkho");
                    for (int i = 0; i < hangHoaDTOS.size(); i++) {
                        addKiemKhoChiTiet(maKiemKho, hangHoaDTOS.get(i));
                        updateHangHoa(hangHoaDTOS.get(i));
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
                param.put("manhanvien", maNV);
                param.put("noitaophieu", noitaophieu);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(KiemKhoActivity.this);
        rq.add(request);
    }

    private void updateHangHoa(HangHoaDTO hangHoaDTO) {
        String url = "http://192.168.1.10/website1/updateHangHoa.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "update thanh cong");
//                        finish();
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
                param.put("idhanghoa", hangHoaDTO.getCOLUMN_ID());
                param.put("idloaihang", hangHoaDTO.getCOLUMN_LOAIHANG());
                param.put("tenhanghoa", hangHoaDTO.getCOLUMN_NAME());
                param.put("vitri", hangHoaDTO.getCOLUMN_VITRI());
                param.put("hinhanh", "");   //!!!!!!!!!!!
                param.put("soluong", hangHoaDTO.getCOLUMN_THUCTE());
                param.put("giaban", hangHoaDTO.getCOLUMN_GIABAN());
                param.put("giavon", hangHoaDTO.getCOLUMN_GIAVON());
                param.put("dinhmuctonmin", hangHoaDTO.getCOLUMN_DINHMUCTON_MIN());
                param.put("dinhmuctonmax", hangHoaDTO.getCOLUMN_DINHMUCTON_MAX());
                param.put("ghichu", hangHoaDTO.getCOLUMN_GHICHU());
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(KiemKhoActivity.this);
        rq.add(request);
    }

    private void addKiemKhoChiTiet(String makiemkho, HangHoaDTO hangHoaDTO) {
        String url = "http://192.168.1.10/website1/addKiemKhoChiTiet.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "add thanh cong");
                    } else {
                        Log.d("vvv", "add that bai");
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
                param.put("makiemkho", makiemkho);
                param.put("mahanghoa", hangHoaDTO.getCOLUMN_ID());
                param.put("soluongBD", hangHoaDTO.getCOLUMN_TONKHO());
                param.put("toncuoi", hangHoaDTO.getCOLUMN_THUCTE());
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(KiemKhoActivity.this);
        rq.add(request);
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
}