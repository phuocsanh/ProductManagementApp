package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.DTO.NhanVienDTO;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FragmentDoiMatKhau extends Fragment {
    private NhanVienDTO nhanVienDTO;
    private String id, matkhau, xacnhanmatkhau;
    private EditText ed_tendangnhap, ed_matkhau, ed_xacnhanmatkhau;
    private Button btn_xacnhan, btn_huy;

    public static FragmentDoiMatKhau getInstance(NhanVienDTO nhanVienDTO){
        FragmentDoiMatKhau fragmentDoiMatKhau = new FragmentDoiMatKhau();
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_nhanvien", nhanVienDTO);
        fragmentDoiMatKhau.setArguments(bundle);
        return fragmentDoiMatKhau;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doimatkhau, container, false);
        ((MainActivity) getActivity()).setTitle("Đổi Mật Khẩu");
        ed_tendangnhap = view.findViewById(R.id.ed_tendangnhap_dmk);
        ed_matkhau = view.findViewById(R.id.ed_matkhau_dmk);
        ed_xacnhanmatkhau = view.findViewById(R.id.ed_xacnhanmatkhau_dmk);
        btn_xacnhan = view.findViewById(R.id.btn_xacnhan_dmk);
        btn_huy = view.findViewById(R.id.btn_huy_dmk);
        setHasOptionsMenu(true);

        nhanVienDTO = (NhanVienDTO)getArguments().get("object_nhanvien");
        ed_tendangnhap.setText(nhanVienDTO.getCOLUM_TENDANGNHAP());
        id = nhanVienDTO.getCOLUM_ID();

        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate_matkhau()>0){
                    updateMatKhau(id, matkhau);
                    ed_matkhau.setText("");
                    ed_xacnhanmatkhau.setText("");
                }

            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_matkhau.setText("");
                ed_xacnhanmatkhau.setText("");
            }
        });
        return view;
    }


    private void updateMatKhau(String id, String matkhau) {
        String url = "http://192.168.1.10/website1/updateMatKhau.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Toast.makeText(getActivity(),"Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(),"Đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
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
                param.put("manhanvien", id);
                param.put("matkhau", matkhau);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(request);
    }
    public int validate_matkhau() {
        int check = 1;
        matkhau = ed_matkhau.getText().toString().trim();
        xacnhanmatkhau = ed_xacnhanmatkhau.getText().toString().trim();
        if (matkhau.isEmpty() || xacnhanmatkhau.isEmpty()) {
            Toast.makeText(getActivity(),"Chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        if(!matkhau.equals(xacnhanmatkhau)){
            Toast.makeText(getActivity(),"Mật khẩu chưa đúng", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

}
