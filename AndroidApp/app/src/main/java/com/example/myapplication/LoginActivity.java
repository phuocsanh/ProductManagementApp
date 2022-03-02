package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.DTO.NhanVienDTO;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout editTextUsername, editTextPassword;
    private Button buttonLogin;
    private CheckBox chkRemember;
    private String strUser, strPass;
    private NhanVienDTO nhanVienDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.et_username);
        editTextPassword = findViewById(R.id.et_password);
        buttonLogin = findViewById(R.id.btn_login);
        chkRemember = findViewById(R.id.checkbox_savepass);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        editTextUsername.getEditText().setText(sharedPreferences.getString("USERNAME", ""));
        editTextPassword.getEditText().setText(sharedPreferences.getString("PASSWORD", ""));
        chkRemember.setChecked(sharedPreferences.getBoolean("REMEMBER", false));

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    public void rememberUser(String user, String pass, Boolean chk) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!chk) {
            editor.clear();
        } else {
            editor.putString("USERNAME", user);
            editor.putString("PASSWORD", pass);
            editor.putBoolean("REMEMBER", chk);
        }
        editor.commit();
    }

    private void checkLogin() {
        String url = "http://192.168.1.10/website1/checkLogin.php";
        strUser = editTextUsername.getEditText().getText().toString().trim();
        strPass = editTextPassword.getEditText().getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equals("1")) {
                        nhanVienDTO = new NhanVienDTO();
                        JSONArray jsonArray = jsonObject.getJSONArray("nhanvien");
                        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                        nhanVienDTO.setCOLUM_ID(jsonObject1.getString("manhanvien"));
                        nhanVienDTO.setCOLUM_TENDANGNHAP(jsonObject1.getString("tendangnhap"));
                        nhanVienDTO.setCOLUM_MATKHAU(jsonObject1.getString("matkhau"));
                        nhanVienDTO.setCOLUM_CHUCVU(jsonObject1.getString("chucvu"));

                        Log.d("vvv", nhanVienDTO.getCOLUM_TENDANGNHAP());
                        rememberUser(strUser, strPass, chkRemember.isChecked());

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("nhanvien", nhanVienDTO);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplication(), "Tên tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
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
                param.put("tendangnhap", strUser);
                param.put("matkhau", strPass);
                return param;

            }
        };
        RequestQueue rq = Volley.newRequestQueue(LoginActivity.this);
        rq.add(request);
    }

}
