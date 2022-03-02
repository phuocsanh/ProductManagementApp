package com.example.myapplication.Adapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
import com.example.myapplication.DTO.HangHoaDTO;
import com.example.myapplication.DTO.HoaDonChiTietDTO;
import com.example.myapplication.DTO.HoaDonDTO;
import com.example.myapplication.DTO.KhachHangDTO;
import com.example.myapplication.DTO.NhomHangDTO;
import com.example.myapplication.R;
import com.example.myapplication.UpdateHangHoaActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterLSGD extends RecyclerView.Adapter<AdapterLSGD.LichSuViewHolder> {

    private Context mContext;
    private ArrayList<HoaDonDTO> hoaDonDTOS;
    OnItemClickListner listner;


    public AdapterLSGD(Context mContext, ArrayList<HoaDonDTO> hoaDonDTOS) {
        this.mContext = mContext;
        this.hoaDonDTOS = hoaDonDTOS;
    }

    public class LichSuViewHolder extends RecyclerView.ViewHolder {
        private TextView idHoaDon, tenKhachHang, thoiGian, tongTien;

        private LichSuViewHolder(View itemView) {
            super(itemView);
            idHoaDon = itemView.findViewById(R.id.textView_maHD5);
            tenKhachHang = itemView.findViewById(R.id.textView_tenKH5);
            thoiGian = itemView.findViewById(R.id.textView_thoiGianTao5);
            tongTien = itemView.findViewById(R.id.textView_tongTienHD5);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listner != null && position != RecyclerView.NO_POSITION) {
                        listner.onItemClick(itemView.getTag().toString(), String.valueOf(position), idHoaDon.getText().toString());
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public LichSuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_recyclerview_lsgd, parent, false);
        return new LichSuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuViewHolder holder, int position) {
        String id = hoaDonDTOS.get(position).getCOLUMN_ID();
        String thoigiantao = hoaDonDTOS.get(position).getCOLUMN_THOIGIANTAO();

        holder.idHoaDon.setText("HD00" + id);
        holder.thoiGian.setText(thoigiantao);
        holder.tenKhachHang.setText(hoaDonDTOS.get(position).getCOLUMN_KH());
        holder.tongTien.setText(hoaDonDTOS.get(position).getCOLUMN_TONGTIEN());
//        getHhachHangByID(hoaDonDTOS.get(position).getCOLUMN_IDKH(), holder);
//        getGiaHoaDonBYIDHD(id, holder);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return hoaDonDTOS.size();
    }

    public interface OnItemClickListner {
        void onItemClick(String id, String vitri, String idhoadon);
    }
    public void setOnItemClickListener(OnItemClickListner listener){
        this.listner = listener;
    }

    /*private void getHhachHangByID(String idkh, LichSuViewHolder holder) {
        String url = "http://192.168.1.10/website1/getKhachHangByID.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("khachhang");
                    khachHangDTO = new KhachHangDTO();
                    JSONObject hit = jsonArray.getJSONObject(0);
                    String entry = "";
                    entry = hit.getString("makhachhang");
                    khachHangDTO.setCOLUM_TENKHACHHANG(entry);
                    entry = hit.getString("tenkhachhang");
                    khachHangDTO.setCOLUM_TENKHACHHANG(entry);
                    holder.tenKhachHang.setText(khachHangDTO.getCOLUM_TENKHACHHANG());
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
                param.put("makhachhang", idkh);

                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(mContext);
        rq.add(request);
    }

    private void getGiaHoaDonBYIDHD(String idhd, LichSuViewHolder holder) {
        String url = "http://192.168.1.10/website1/getGiaHoaDon.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("giahoadon");
                    JSONObject hit = jsonArray.getJSONObject(0);
                    String entry = "";
                    entry = hit.getString("tonggia");
                    holder.tongTien.setText(entry);
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
                param.put("idhoadon", idhd);

                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(mContext);
        rq.add(request);
    }*/

}
