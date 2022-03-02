package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.DTO.NhanVienDTO;
import com.example.myapplication.Fragment.FragmentDoiMatKhau;
import com.example.myapplication.Fragment.FragmentHangHoa;
import com.example.myapplication.Fragment.FragmentKhachHang;
import com.example.myapplication.Fragment.FragmentKiemKho;
import com.example.myapplication.Fragment.FragmentLSGD;
import com.example.myapplication.Fragment.FragmentNhanVien;
import com.example.myapplication.Fragment.FragmentNhapHang;
import com.example.myapplication.Fragment.FragmentThongKe;
import com.example.myapplication.Fragment.FragmentXuatHang;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    FragmentManager fragmentManager;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private View mHeaderView;
    private TextView tv_chucvu, tv_User;
    private NhanVienDTO nhanVienDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nvView);
        toolbar = findViewById(R.id.toolbar);
        mHeaderView = navigationView.getHeaderView(0);
        tv_chucvu = mHeaderView.findViewById(R.id.textView_chucvu_header);
        tv_User = mHeaderView.findViewById(R.id.textView_tenNguoiDung);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        fragmentManager = getSupportFragmentManager();
        xulyheader();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent, FragmentXuatHang.getInstance(nhanVienDTO)).commit();
            navigationView.setCheckedItem(R.id.nav_1_xuatHang);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                ClickMenu(item);
                return true;
            }
        });

    }

    //    -----------------------------------------------------------
    private void ClickMenu(MenuItem menuItem) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        switch (menuItem.getItemId()) {
            case R.id.nav_1_xuatHang:
                fragmentTransaction.replace(R.id.flContent, FragmentXuatHang.getInstance(nhanVienDTO)).commit();
                break;
            case R.id.nav_3_lichSuDonHang:
                fragmentTransaction.replace(R.id.flContent, new FragmentLSGD()).commit();
                break;
            case R.id.nav_5_tongQuan:
                fragmentTransaction.replace(R.id.flContent, new FragmentThongKe()).commit();
                break;
            case R.id.nav_6_hangHoa:
                fragmentTransaction.replace(R.id.flContent, FragmentHangHoa.getInstance(nhanVienDTO)).commit();
                break;
            case R.id.nav_7_nhapHang:
                fragmentTransaction.replace(R.id.flContent, FragmentNhapHang.getInstance(nhanVienDTO)).commit();
                break;
            case R.id.nav_8_khachHang:
                fragmentTransaction.replace(R.id.flContent, new FragmentKhachHang()).commit();
                break;

            case R.id.nav_9_nhanVien:
                fragmentTransaction.replace(R.id.flContent, FragmentNhanVien.getInstance(nhanVienDTO)).commit();
                break;
            case R.id.nav_14_kiemKho:
                fragmentTransaction.replace(R.id.flContent, FragmentKiemKho.getInstance(nhanVienDTO)).commit();
                break;
            case R.id.nav_11_goiDT:
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19006522"));// Initiates the Intent
                startActivity(intentCall);
                break;
            case  R.id.nav_12_doiMatKhau:
                fragmentTransaction.replace(R.id.flContent, FragmentDoiMatKhau.getInstance(nhanVienDTO)).commit();
                break;
            case R.id.nav_13_dangXuat:
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
    }

    private void xulyheader() {
        Intent intent = getIntent();
        if (intent != null) {
            nhanVienDTO = (NhanVienDTO) intent.getSerializableExtra("nhanvien"); //Obtaining data
        }
        tv_User.setText(nhanVienDTO.getCOLUM_TENDANGNHAP());
        tv_chucvu.setText(nhanVienDTO.getCOLUM_CHUCVU());

    }


}