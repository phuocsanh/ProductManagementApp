package com.example.myapplication.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.DTO.HoaDonDTO;
import com.example.myapplication.DTO.KhachHangDTO;
import com.example.myapplication.DTO.KiemKhoDTO;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KiemKhoAdapter extends RecyclerView.Adapter<KiemKhoAdapter.KiemKhoViewHolder> {
    Context mContext;
    ArrayList<KiemKhoDTO> kiemKhoDTOS;
    private OnItemClickListner listner;

    public KiemKhoAdapter(Context mContext,  ArrayList<KiemKhoDTO> kiemKhoDTOS) {
        this.mContext = mContext;
        this.kiemKhoDTOS = kiemKhoDTOS;
    }

    public class KiemKhoViewHolder extends RecyclerView.ViewHolder {
        private TextView maKK, tenNV, time;
        public KiemKhoViewHolder(@NonNull View itemView) {
            super(itemView);
            maKK = itemView.findViewById(R.id.textView_maKK9);
            tenNV = itemView.findViewById(R.id.textView_tenNV9);
            time = itemView.findViewById(R.id.textView_timestamp9);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listner != null && position != RecyclerView.NO_POSITION) {
                        listner.onItemClick(itemView.getTag().toString(), String.valueOf(position));
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public KiemKhoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_recyclerview_kiemkho, parent, false);
        return new KiemKhoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KiemKhoViewHolder holder, int position) {
        holder.maKK.setText("KK00" + kiemKhoDTOS.get(position).getCOLUM_ID());
        holder.time.setText(kiemKhoDTOS.get(position).getCOLUM_TIMESTAMP());
        getNVByID(kiemKhoDTOS.get(position).getCOLUM_NHANVIEN(),holder);

        holder.itemView.setTag(kiemKhoDTOS.get(position).getCOLUM_ID());

    }

    @Override
    public int getItemCount() {
        return kiemKhoDTOS.size();
    }

    private void getNVByID(String idnv, KiemKhoViewHolder holder) {
        String url = "http://192.168.1.10/website1/getNhanVienByID.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("nhanvien");
                    JSONObject hit = jsonArray.getJSONObject(0);
                    String entry = "";
                    entry = hit.getString("tendangnhap");
                    holder.tenNV.setText(entry);
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
                param.put("idnhanvien", idnv);

                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(mContext);
        rq.add(request);
    }

    public interface OnItemClickListner {
        void onItemClick(String id, String vitri);
    }
    public void setOnItemClickListener(OnItemClickListner listener){
        this.listner = listener;
    }
}
