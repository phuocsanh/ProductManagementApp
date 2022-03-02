package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.DTO.NhomHangDTO;
import com.example.myapplication.InsertHangHoaActivity;
import com.example.myapplication.R;
import com.example.myapplication.UpdateHangHoaActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterSpinnerNhomHang extends ArrayAdapter<NhomHangDTO> {
    private Context context;
    private ArrayList<NhomHangDTO> list;


    public AdapterSpinnerNhomHang(@NonNull Context context, ArrayList<NhomHangDTO> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_spinner_nhomhang, parent, false
            );
        }
        ImageView imageView = convertView.findViewById(R.id.imb_sualoaihang);
        TextView tvTenNhomHang = convertView.findViewById(R.id.tv_tenloaihang);
        imageView.setEnabled(false);
        NhomHangDTO nhomHangDTOItem = getItem(position);
        if (nhomHangDTOItem != null) {
            tvTenNhomHang.setText(nhomHangDTOItem.getCOLUMN_NAME());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_spinner_nhomhang, parent, false
            );
        }
        ImageView imageView = convertView.findViewById(R.id.imb_sualoaihang);
        TextView tvTenNhomHang = convertView.findViewById(R.id.tv_tenloaihang);

        NhomHangDTO nhomHangDTOItem = getItem(position);
        if (nhomHangDTOItem != null) {
            tvTenNhomHang.setText(nhomHangDTOItem.getCOLUMN_NAME());
            View finalConvertView = convertView;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View view = inflater.inflate(R.layout.dialog_themnhomhang, null);
                    EditText nhomHang = view.findViewById(R.id.editTextNhomHang);
                    nhomHang.setText(tvTenNhomHang.getText().toString());
                    builder.setView(view)
                            .setTitle("Sửa loại hàng.")
                            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String username = nhomHang.getText().toString();
                                    updateLoaihang(position,username);
                                    tvTenNhomHang.setText(username);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
        return convertView;
    }

    public NhomHangDTO getItem(int position) {
        return list.get(position);
    }
    //    -----------------------

    private void updateLoaihang(int position, String tenloaihang) {
        String idloaihang = list.get(position).getCOLUMN_ID();
        String url = "http://192.168.1.10/website1/updateLoaiHang.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "update thanh cong");
                        getAllLoaiHang();
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
                param.put("idloaihang", idloaihang);
                param.put("tenloaihang", tenloaihang);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(context);
        rq.add(request);
    }

    private void getAllLoaiHang() {
        String url = "http://192.168.1.10/website1/loaihang.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<NhomHangDTO> nhomHangDTOS=  new ArrayList<>();

                        try {
                            JSONArray jsonArray = response.getJSONArray("loaihang");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                NhomHangDTO   nhomHangDTO = new NhomHangDTO();
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String idloaihang = hit.getString("idloaihang");
                                String tenloaihang = hit.getString("tenloaihang");
                                nhomHangDTO.setCOLUMN_ID(idloaihang);
                                nhomHangDTO.setCOLUMN_NAME(tenloaihang);

                                nhomHangDTOS.add(nhomHangDTO);
                            }
                            list.clear();
                            list.addAll(nhomHangDTOS);
                            notifyDataSetChanged();
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
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }
}
