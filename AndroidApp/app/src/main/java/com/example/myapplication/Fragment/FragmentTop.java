package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.TopAdapter;
import com.example.myapplication.DTO.HangHoaDTO;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentTop extends Fragment {

    private TopAdapter topAdapter;
    private HangHoaDTO hangHoaDTOTop;
    private ArrayList<HangHoaDTO> topDTOList;
    private RecyclerView recyclerViewTop;
    private Spinner spinnerNam;
    private ArrayList<String> listNam;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top, container, false);

        recyclerViewTop = view.findViewById(R.id.recyclerview_top);

        spinnerNam = view.findViewById(R.id.spiner_nam_top);
        getDsNam();

        return view;
    }

    private void getTop(String namDT) {
        String url = "http://192.168.1.10/website1/getTop.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                topDTOList = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equals("1")) {
                        topDTOList.clear();
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("top");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                hangHoaDTOTop = new HangHoaDTO();
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String entry = "";
                                entry = (hit.getString("idhanghoa"));
                                hangHoaDTOTop.setCOLUMN_ID(entry);
                                entry = (hit.getString("tenhanghoa"));
                                hangHoaDTOTop.setCOLUMN_NAME(entry);
                                entry = (hit.getString("soluong"));
                                hangHoaDTOTop.setCOLUMN_SOLUONGHD(entry);

                                topDTOList.add(hangHoaDTOTop);
                            }
                        } catch (Exception e) {

                        }
                        recyclerViewTop.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerViewTop.setHasFixedSize(true);
                        topAdapter = new TopAdapter(getActivity(), topDTOList);
                        recyclerViewTop.setAdapter(topAdapter);

                    } else {
                        Toast.makeText(getActivity(), "Không có dữ liệu top", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("nam", namDT);

                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(request);
    }

    private void getDsNam() {
        String url = "http://192.168.1.10/website1/getDsNam.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listNam = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("listnam");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                listNam.add(hit.getString("nam"));
                            }
                            setSpinnerAdapter(listNam);
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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    private void setSpinnerAdapter(ArrayList<String> list) {
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);
        spinnerNam.setAdapter(spinnerArrayAdapter);
        spinnerNam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getTop(spinnerNam.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}
