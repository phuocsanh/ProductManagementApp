package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.KhachHangAdapter;
import com.example.myapplication.DTO.KhachHangDTO;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import maes.tech.intentanim.CustomIntent;

public class KhachHangActivity extends AppCompatActivity {

    public static final String GET_ID_KHACHHANG = "idkhachhang";
    public static final String GET_TEN_KHACHHANG = "tenkhachhang";
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private KhachHangDTO khachHangDTO;
    private ArrayList<KhachHangDTO> khachHangDTOS;
    private KhachHangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khachhang);
        recyclerView = findViewById(R.id.rc_khachhang_acti);
        toolbar = findViewById(R.id.toolbar_khachHangActivity);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        setTitle("Thêm Khách hàng");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                CustomIntent.customType(KhachHangActivity.this, "right-to-left");
            }
        });


        getAllKhachHang();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                nhanVienAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_:
                dialogThemKhachHang();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getAllKhachHang() {
        String url = "http://192.168.1.10/website1/khachhang.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        khachHangDTOS = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("khachhang");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                khachHangDTO = new KhachHangDTO();
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String entry = "";
                                entry = hit.getString("makhachhang");
                                khachHangDTO.setCOLUM_ID(entry);
                                entry = hit.getString("tenkhachhang");
                                khachHangDTO.setCOLUM_TENKHACHHANG(entry);
                                entry = hit.getString("sdtkhachhang");
                                khachHangDTO.setCOLUM_SDT(entry);
                                khachHangDTOS.add(khachHangDTO);
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(KhachHangActivity.this));
                            recyclerView.setHasFixedSize(true);
                            adapter = new KhachHangAdapter(KhachHangActivity.this, khachHangDTOS);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new KhachHangAdapter.OnItemClickListner() {
                                @Override
                                public void onItemClick(String id, String vitri) {
                                    Intent returnIntent = new Intent();
                                    returnIntent.putExtra(GET_ID_KHACHHANG, id);
                                    returnIntent.putExtra(GET_TEN_KHACHHANG, khachHangDTOS.get(Integer.parseInt(vitri)).getCOLUM_TENKHACHHANG());
                                    setResult(RESULT_OK, returnIntent);
                                    finish();
                                    CustomIntent.customType(KhachHangActivity.this, "right-to-left");
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("vvv", error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(KhachHangActivity.this);
        requestQueue.add(request);
    }
    private void dialogThemKhachHang() {
        AlertDialog.Builder builder = new AlertDialog.Builder(KhachHangActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_khachhang, null);
        TextInputLayout textInputLayoutTen = view.findViewById(R.id.ed_tenkhachhang_dialog);
        TextInputLayout textInputLayoutMK = view.findViewById(R.id.ed_sdt_dialog);
        builder.setView(view)
                .setTitle("Thêm khách hàng")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = textInputLayoutTen.getEditText().getText().toString().trim();
                        String SDT = textInputLayoutMK.getEditText().getText().toString().trim();
                        addKhachHang(username, SDT);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void addKhachHang(String ten, String sdt) {
        String url = "http://192.168.1.10/website1/addKhachHang.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "add thanh cong");
                        getAllKhachHang();
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
                param.put("tenkhachhang", ten);
                param.put("sdtkhachhang", sdt);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(KhachHangActivity.this);
        rq.add(request);
    }
}