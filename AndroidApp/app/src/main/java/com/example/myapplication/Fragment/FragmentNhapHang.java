package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.NhapHangAdapter;
import com.example.myapplication.ChiTietNhapHangActivity;
import com.example.myapplication.DTO.NhanVienDTO;
import com.example.myapplication.DTO.NhapHangDTO;
import com.example.myapplication.MainActivity;
import com.example.myapplication.NhapHangActivity;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class FragmentNhapHang extends Fragment {
    private RecyclerView recyclerView;
    private NhanVienDTO nhanVienDTO;
    private ArrayList<NhapHangDTO> nhapHangDTOS;
    private NhapHangDTO nhapHangDTO;
    private NhapHangAdapter nhapHangAdapter;

    public static FragmentNhapHang getInstance(NhanVienDTO nhanVienDTO) {
        FragmentNhapHang fragmentNhapHang = new FragmentNhapHang();
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_nhanvien", nhanVienDTO);
        fragmentNhapHang.setArguments(bundle);
        return fragmentNhapHang;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhaphang, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_nhaphang0);

        ((MainActivity) getActivity()).setTitle("Nhập hàng");
        setHasOptionsMenu(true);
        nhanVienDTO = (NhanVienDTO) getArguments().get("object_nhanvien");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllPhieuNhap();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_:
                Intent intent = new Intent(getActivity(), NhapHangActivity.class);
                intent.putExtra("nhanvien", nhanVienDTO);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void getAllPhieuNhap() {
        String url = "http://192.168.1.10/website1/nhaphang.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        nhapHangDTOS = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("nhaphang");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                nhapHangDTO = new NhapHangDTO();
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String entry = "";
                                entry = hit.getString("idnhaphang");
                                nhapHangDTO.setCOLUM_ID(entry);
                                entry = hit.getString("soluongbandau");
                                nhapHangDTO.setCOLUM_SOLUONGBD(entry);
                                entry = hit.getString("soluongnhap");
                                nhapHangDTO.setCOLUM_SOLUONGNHAP(entry);
                                entry = hit.getString("thoigian");
                                nhapHangDTO.setCOLUM_TIMESTAMP(entry);
                                entry = hit.getString("idhanghoa");
                                nhapHangDTO.setCOLUM_IDHANGHOA(entry);
                                entry = hit.getString("tenhanghoa");
                                nhapHangDTO.setCOLUM_TENHANGHOA(entry);
                                entry = hit.getString("hinhanh");
                                nhapHangDTO.setCOLUM_HINHANH(entry);
                                entry = hit.getString("tennhanvien");
                                nhapHangDTO.setCOLUM_NHANVIEN(entry);

                                nhapHangDTOS.add(nhapHangDTO);
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setHasFixedSize(true);
                            nhapHangAdapter = new NhapHangAdapter(getActivity(), nhapHangDTOS);
                            recyclerView.setAdapter(nhapHangAdapter);
                            nhapHangAdapter.setOnItemClickListener(new NhapHangAdapter.OnItemClickListner() {
                                @Override
                                public void onItemClick(String id, String vitri) {
                                    Intent intent = new Intent(getActivity(), ChiTietNhapHangActivity.class);
                                    intent.putExtra("nhaphang", (Serializable) nhapHangDTOS.get(Integer.parseInt(vitri)));
                                    startActivity(intent);
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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }
}
