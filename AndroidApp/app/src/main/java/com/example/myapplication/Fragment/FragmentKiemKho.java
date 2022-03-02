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
import com.example.myapplication.Adapter.KiemKhoAdapter;
import com.example.myapplication.ChiTietKiemKhoActivity;
import com.example.myapplication.DTO.KiemKhoDTO;
import com.example.myapplication.DTO.NhanVienDTO;
import com.example.myapplication.KiemKhoActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class FragmentKiemKho extends Fragment {
    private RecyclerView recyclerView;
    private KiemKhoDTO kiemKhoDTO;
    private ArrayList<KiemKhoDTO> kiemKhoDTOS;
    private KiemKhoAdapter kiemKhoAdapter;
    private NhanVienDTO nhanVienDTO;

    public static FragmentKiemKho getInstance(NhanVienDTO nhanVienDTO) {
        FragmentKiemKho fragmentKiemKho = new FragmentKiemKho();
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_nhanvien", nhanVienDTO);
        fragmentKiemKho.setArguments(bundle);
        return fragmentKiemKho;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kiemkho, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_kiemkho0);

        ((MainActivity) getActivity()).setTitle("Kiểm kho");
        setHasOptionsMenu(true);

        nhanVienDTO = (NhanVienDTO) getArguments().get("object_nhanvien");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllKiemKho();
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
                Intent intent = new Intent(getActivity(), KiemKhoActivity.class);
                intent.putExtra("nhanvien", nhanVienDTO);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getAllKiemKho() {
        String url = "http://192.168.1.10/website1/kiemkho.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        kiemKhoDTOS = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("kiemkho");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                kiemKhoDTO = new KiemKhoDTO();
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String entry = "";
                                entry = hit.getString("makiemkho");
                                kiemKhoDTO.setCOLUM_ID(entry);
                                entry = hit.getString("manhanvien");
                                kiemKhoDTO.setCOLUM_NHANVIEN(entry);
                                entry = hit.getString("thoigian");
                                kiemKhoDTO.setCOLUM_TIMESTAMP(entry);
                                entry = hit.getString("noitaophieu");
                                kiemKhoDTO.setCOLUM_NOITAOPHIEU(entry);

                                kiemKhoDTOS.add(kiemKhoDTO);
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setHasFixedSize(true);
                            kiemKhoAdapter = new KiemKhoAdapter(getActivity(), kiemKhoDTOS);
                            recyclerView.setAdapter(kiemKhoAdapter);
                            kiemKhoAdapter.setOnItemClickListener(new KiemKhoAdapter.OnItemClickListner() {
                                @Override
                                public void onItemClick(String id, String vitri) {
                                    Intent intent = new Intent(getActivity(), ChiTietKiemKhoActivity.class);
                                    intent.putExtra("kiemkho", (Serializable) kiemKhoDTOS.get(Integer.parseInt(vitri)));
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
