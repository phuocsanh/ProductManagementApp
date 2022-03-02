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
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.AdapterLSGD;
import com.example.myapplication.ChiTietKiemKhoActivity;
import com.example.myapplication.ChiTietLSGDActivity;
import com.example.myapplication.DTO.HoaDonDTO;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class FragmentLSGD extends Fragment {
    private RecyclerView recyclerView;
    private HoaDonDTO hoaDonDTO;
    private ArrayList<HoaDonDTO> hoaDonDTOS;
    private AdapterLSGD adapterLSGD;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lsgd, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_lsdg);

        ((MainActivity) getActivity()).setTitle("Lịch sử giao dịch");
        setHasOptionsMenu(true);
        getAllHoaDon();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getAllHoaDon() {
        String url = "http://192.168.1.10/website1/hoadon.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hoaDonDTOS = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("hoadon");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                hoaDonDTO = new HoaDonDTO();
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String entry = "";
                                entry = hit.getString("mahoadon");
                                hoaDonDTO.setCOLUMN_ID(entry);
                                entry = hit.getString("tenkhachhang");
                                hoaDonDTO.setCOLUMN_KH(entry);
                                entry = hit.getString("tendangnhap");
                                hoaDonDTO.setCOLUMN_NV(entry);
                                entry = hit.getString("ghichu");
                                hoaDonDTO.setCOLUMN_GHICHU(entry);
                                entry = hit.getString("thoigiantao");
                                hoaDonDTO.setCOLUMN_THOIGIANTAO(entry);
                                entry = hit.getString("tongtien");
                                hoaDonDTO.setCOLUMN_TONGTIEN(entry);

                                hoaDonDTOS.add(hoaDonDTO);
                            }
//                            Log.d("vvv", hoaDonDTOS.get(1).getCOLUMN_ID());
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setHasFixedSize(true);
                            adapterLSGD = new AdapterLSGD(getActivity(), hoaDonDTOS);
                            recyclerView.setAdapter(adapterLSGD);
                            adapterLSGD.setOnItemClickListener(new AdapterLSGD.OnItemClickListner() {
                                @Override
                                public void onItemClick(String id, String vitri, String idhoadon) {
                                    Intent intent = new Intent(getActivity(), ChiTietLSGDActivity.class);
                                    intent.putExtra("hoadon", (Serializable) hoaDonDTOS.get(Integer.parseInt(vitri)));
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
