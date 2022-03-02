package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapplication.Adapter.AdapterSpinnerNhomHang;
import com.example.myapplication.DTO.HangHoaDTO;
import com.example.myapplication.DTO.NhanVienDTO;
import com.example.myapplication.DTO.NhomHangDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdateHangHoaActivity extends AppCompatActivity {

    private ImageView imageViewHinhHangHoa;
    private EditText tenHangHoa, giaBan, giaGoc, tonKho, ghiChu, viTri;
    private TextView dinhMucTonMin, dinhMucTonMax;
    private Spinner spNhomHang;
    private ImageButton btnThemNhomHang;
    private LinearLayout linearLayout;

    private HangHoaDTO hangHoaDTO;
    private ArrayList<NhomHangDTO> nhomHangDTOS;
    private AdapterSpinnerNhomHang adapterSpinnerNhomHang;
    private NhomHangDTO nhomHangDTO;
    private NhanVienDTO nhanVienDTO;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_hoa_update);

        btnThemNhomHang = findViewById(R.id.imageButton_themLoaiHang);
        imageViewHinhHangHoa = findViewById(R.id.imageView_hinhHangHoa2);
        tenHangHoa = findViewById(R.id.textView_tenHangHoa2);
        dinhMucTonMin = findViewById(R.id.textView_dinhMucTonMin2);
        dinhMucTonMax = findViewById(R.id.textView_dinhMucTonMax2);
        giaBan = findViewById(R.id.textView_giaBan2);
        giaGoc = findViewById(R.id.textView_giaGoc2);
        tonKho = findViewById(R.id.textView_tonKho2);
        viTri = findViewById(R.id.textView_viTriHang2);
        ghiChu = findViewById(R.id.textView_ghiChu2);
        spNhomHang = findViewById(R.id.spinner_nhomHang2);
        linearLayout = findViewById(R.id.linear_dinhMuc);
        toolbar = findViewById(R.id.toolbar_chinhSuaHangHoa);



        toolbar.setNavigationIcon(R.drawable.ic_back);
        setTitle("Chỉnh sửa hàng hóa");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imageViewHinhHangHoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pick = new Intent(Intent.ACTION_GET_CONTENT);
                pick.setType("image/*");
                Intent pho = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent chosser = Intent.createChooser(pick, "chon");
                chosser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pho});
                startActivityForResult(chosser, 999);
            }
        });

        //lay id
        Intent intent = getIntent();
        hangHoaDTO = (HangHoaDTO) intent.getSerializableExtra("hangHoaTaiViTriClick");
        nhanVienDTO= (NhanVienDTO) intent.getSerializableExtra("nhanvien");
        getAllLoaiHang();

        Glide.with(UpdateHangHoaActivity.this).load("http://192.168.1.10/website1/hinhsanpham/"+
                hangHoaDTO.getCOLUMN_HINHDAIDIEN()).into(imageViewHinhHangHoa);
        tenHangHoa.setText(hangHoaDTO.getCOLUMN_NAME());
        dinhMucTonMin.setText(hangHoaDTO.getCOLUMN_DINHMUCTON_MIN());
        dinhMucTonMax.setText(hangHoaDTO.getCOLUMN_DINHMUCTON_MAX());
        giaBan.setText(hangHoaDTO.getCOLUMN_GIABAN());
        giaGoc.setText(hangHoaDTO.getCOLUMN_GIAVON());
        tonKho.setText(hangHoaDTO.getCOLUMN_TONKHO());
        viTri.setText(hangHoaDTO.getCOLUMN_VITRI());
        ghiChu.setText(hangHoaDTO.getCOLUMN_GHICHU());

        btnThemNhomHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAddNhomHang();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDinhMuc();
            }
        });

    }
    //-------------------------------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 999 && resultCode == RESULT_OK) {
            if (data.getExtras() != null) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageViewHinhHangHoa.setImageBitmap(imageBitmap);
            } else {
                Uri uri = data.getData();
                imageViewHinhHangHoa.setImageURI(uri);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_:
                updateHangHoa();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateHangHoa() {
        String idHangHoa = hangHoaDTO.getCOLUMN_ID();
        NhomHangDTO selection = (NhomHangDTO) spNhomHang.getSelectedItem();
        String idloaihang = selection.getCOLUMN_ID();
        String tenhanghoa = tenHangHoa.getText().toString();
        String vitri = viTri.getText().toString();
        String soluong = tonKho.getText().toString();
        String giaban = giaBan.getText().toString();
        String giavon = giaGoc.getText().toString();
        String dinhmuctonmin = dinhMucTonMin.getText().toString();
        String dinhmuctonmax = dinhMucTonMax.getText().toString();
        String ghichu = ghiChu.getText().toString();

        String url = "http://192.168.1.10/website1/updateHangHoa.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");

                    if (x.equalsIgnoreCase("1")) {
                        if (!hangHoaDTO.getCOLUMN_TONKHO().equalsIgnoreCase(soluong)){
                            addKiemKho(nhanVienDTO.getCOLUM_ID(),hangHoaDTO.getCOLUMN_ID(),hangHoaDTO.getCOLUMN_TONKHO(),soluong,"tự động khi cập nhập hàng hóa");
                        }

                        Log.d("vvv", "update thanh cong");
                        finish();
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
                param.put("idhanghoa", idHangHoa);
                param.put("idloaihang", idloaihang);
                param.put("tenhanghoa", tenhanghoa);
                param.put("vitri", vitri);
                param.put("hinhanh", getBase64String());   //!!!!!!!!!!!
                param.put("soluong", soluong);
                param.put("giaban", giaban);
                param.put("giavon", giavon);
                param.put("dinhmuctonmin", dinhmuctonmin);
                param.put("dinhmuctonmax", dinhmuctonmax);
                param.put("ghichu", ghichu);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(UpdateHangHoaActivity.this);
        rq.add(request);
    }

    private void DialogAddNhomHang() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateHangHoaActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themnhomhang, null);
        EditText nhomHang = view.findViewById(R.id.editTextNhomHang);
        builder.setView(view)
                .setTitle("Thêm loại hàng.")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = nhomHang.getText().toString();
                        addLoaiHang(username);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void addLoaiHang(String name) {
        String url = "http://192.168.1.10/website1/addloaihang.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "add thanh cong");
                        getAllLoaiHang();
                    } else {
                        Log.d("vvv", "add that bai");
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
                param.put("tenhanghoa", name);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(UpdateHangHoaActivity.this);
        rq.add(request);
    }

    private void getAllLoaiHang() {
        String url = "http://192.168.1.10/website1/loaihang.php";
//        String url = "https://databasefpt.000webhostapp.com/loaihang.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        nhomHangDTOS = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("loaihang");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                nhomHangDTO = new NhomHangDTO();
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String idloaihang = hit.getString("idloaihang");
                                String tenloaihang = hit.getString("tenloaihang");
                                nhomHangDTO.setCOLUMN_ID(idloaihang);
                                nhomHangDTO.setCOLUMN_NAME(tenloaihang);
                                nhomHangDTOS.add(nhomHangDTO);
                            }
                            adapterSpinnerNhomHang = new AdapterSpinnerNhomHang(UpdateHangHoaActivity.this, nhomHangDTOS);
                            spNhomHang.setAdapter(adapterSpinnerNhomHang);

                            for (int i = 0; i < nhomHangDTOS.size(); i++) {
                                if (hangHoaDTO.getCOLUMN_LOAIHANG().equalsIgnoreCase(nhomHangDTOS.get(i).getCOLUMN_ID())) {
                                    spNhomHang.setSelection(i);
                                    break;
                                }
                            }

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
        RequestQueue requestQueue = Volley.newRequestQueue(UpdateHangHoaActivity.this);
        requestQueue.add(request);
    }
    private void DialogDinhMuc() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateHangHoaActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_dinhmuc, null);
        EditText min = view.findViewById(R.id.dinhmuctonmin_dialog);
        EditText max = view.findViewById(R.id.dinhmuctonmax_dialog);
        min.setText(dinhMucTonMin.getText().toString());
        max.setText(dinhMucTonMax.getText().toString());
        builder.setView(view)
                .setTitle("Định mức tồn.")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String tu = min.getText().toString();
                        String den = max.getText().toString();
                        dinhMucTonMin.setText(tu);
                        dinhMucTonMax.setText(den);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    //-------------------------------------------------------------------------------
    private String getBase64String() {
        Drawable drawable = ((ImageView) imageViewHinhHangHoa).getDrawable();
        try {
            Bitmap bitmap;
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
            byte[] image = stream.toByteArray();

            return Base64.encodeToString(image, 0);
        } catch (OutOfMemoryError e) {
            // Handle the error'
            return "";
        }
    }
    private void addKiemKho(String maNV, String mahh, String soluongbd, String toncuoi, String noitaophieu) {
        String url = "http://192.168.1.10/website1/addKiemKho.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  maKiemKho = jsonObject.getString("makiemkho");

                    addKiemKhoChiTiet(maKiemKho, mahh,soluongbd,toncuoi);

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
                param.put("manhanvien", maNV);
                param.put("noitaophieu", noitaophieu);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(UpdateHangHoaActivity.this);
        rq.add(request);
    }
    private void addKiemKhoChiTiet(String makiemkho, String idhh, String slbd,String toncuoi) {
        String url = "http://192.168.1.10/website1/addKiemKhoChiTiet.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "add thanh cong");
                    } else {
                        Log.d("vvv", "add that bai");
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
                param.put("makiemkho", makiemkho);
                param.put("mahanghoa", idhh);
                param.put("soluongBD", slbd);
                param.put("toncuoi",toncuoi);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(UpdateHangHoaActivity.this);
        rq.add(request);
    }
}