package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.HangHoaAdapter;
import com.example.myapplication.DTO.HangHoaDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChonHangkiemActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<HangHoaDTO> hangHoaDTOS;
    private HangHoaDTO hangHoaDTO;
    private HangHoaAdapter hangHoaAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_hang_kiem);

        searchView = findViewById(R.id.searchview13);

        toolbar = findViewById(R.id.toolbar_chonhangkiem13);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerview_hangHoa13);

        getAllHangHoa();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                hangHoaAdapter.getFilter().filter(newText);

                recyclerView.setBackgroundResource(R.drawable.recylerview_radius);
                recyclerView.setAdapter(hangHoaAdapter);

                hangHoaAdapter.setOnItemClickListener(new HangHoaAdapter.OnItemClickListner() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onItemClick(String id, String vitri, View view) {
                        for(int i = 0; i<hangHoaDTOS.size(); i++){
                            if(hangHoaDTOS.get(i).getCOLUMN_ID().equalsIgnoreCase(id)){
                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("chonhangkiem",hangHoaDTOS.get(i));
                                setResult(RESULT_OK, returnIntent);
                                finish();
                            }
                        }

                    }
                });
                return true;
            }
        });


    }

    private void getAllHangHoa() {
        String url = "http://192.168.1.10/website1/hanghoa.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hangHoaDTOS = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("hanghoa");
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
                                hangHoaDTO.setCOLUMN_THUCTE("0");

                                hangHoaDTOS.add(hangHoaDTO);
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(ChonHangkiemActivity.this));
                            recyclerView.setHasFixedSize(true);
                            hangHoaAdapter = new HangHoaAdapter(ChonHangkiemActivity.this, hangHoaDTOS);


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
        RequestQueue requestQueue = Volley.newRequestQueue(ChonHangkiemActivity.this);
        requestQueue.add(request);
    }
}