package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.DTO.NhapHangDTO;

public class ChiTietNhapHangActivity extends AppCompatActivity {

    private TextView tvTenNV, tvTimestiamp, tvTenHangHoa, tvMaHangHoa, tvTonCu, tvSLNhap;
    private NhapHangDTO nhapHangDTO;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nhap_hang);

        tvTenNV = findViewById(R.id.textView_tenNhanvien21);
        tvTimestiamp = findViewById(R.id.textView_timestamp21);
        tvTenHangHoa = findViewById(R.id.textView_tenHangHoa21);
        tvMaHangHoa = findViewById(R.id.textView_maHangHoa21);
        tvTonCu = findViewById(R.id.textView_tonCu21);
        tvSLNhap = findViewById(R.id.textView_soLuongNhap21);
        toolbar = findViewById(R.id.toolbar_nhaphang21);

        Intent intent = getIntent();
        nhapHangDTO = (NhapHangDTO) intent.getSerializableExtra("nhaphang");

        tvTenNV.setText(nhapHangDTO.getCOLUM_NHANVIEN());
        tvTimestiamp.setText(nhapHangDTO.getCOLUM_TIMESTAMP());
        tvTenHangHoa.setText(nhapHangDTO.getCOLUM_TENHANGHOA());
        tvMaHangHoa.setText("SP000"+ nhapHangDTO.getCOLUM_IDHANGHOA());
        tvTonCu.setText(nhapHangDTO.getCOLUM_SOLUONGBD());
        tvSLNhap.setText(nhapHangDTO.getCOLUM_SOLUONGNHAP());

        toolbar.setNavigationIcon(R.drawable.ic_back);
        setTitle("PN000"+nhapHangDTO.getCOLUM_ID());
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}