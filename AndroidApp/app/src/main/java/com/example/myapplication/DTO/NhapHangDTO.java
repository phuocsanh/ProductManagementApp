package com.example.myapplication.DTO;

import java.io.Serializable;

public class NhapHangDTO implements Serializable {
    private String COLUM_ID = "idNhapHang";
    private String COLUM_IDHANGHOA = "idHangHoa";
    private String COLUM_TENHANGHOA = "tenHangHoa";
    private String COLUM_NHANVIEN = "nhanVien";
    private String COLUM_SOLUONGBD = "soLuongBD";
    private String COLUM_SOLUONGNHAP = "soLuongNhap";
    private String COLUM_HINHANH = "hinhAnh";
    private String COLUM_TIMESTAMP = "timestamp";

    public String getCOLUM_HINHANH() {
        return COLUM_HINHANH;
    }

    public void setCOLUM_HINHANH(String COLUM_HINHANH) {
        this.COLUM_HINHANH = COLUM_HINHANH;
    }


    public String getCOLUM_TIMESTAMP() {
        return COLUM_TIMESTAMP;
    }

    public void setCOLUM_TIMESTAMP(String COLUM_TIMESTAMP) {
        this.COLUM_TIMESTAMP = COLUM_TIMESTAMP;
    }

    public String getCOLUM_ID() {
        return COLUM_ID;
    }

    public void setCOLUM_ID(String COLUM_ID) {
        this.COLUM_ID = COLUM_ID;
    }

    public String getCOLUM_IDHANGHOA() {
        return COLUM_IDHANGHOA;
    }

    public void setCOLUM_IDHANGHOA(String COLUM_IDHANGHOA) {
        this.COLUM_IDHANGHOA = COLUM_IDHANGHOA;
    }

    public String getCOLUM_TENHANGHOA() {
        return COLUM_TENHANGHOA;
    }

    public void setCOLUM_TENHANGHOA(String COLUM_TENHANGHOA) {
        this.COLUM_TENHANGHOA = COLUM_TENHANGHOA;
    }

    public String getCOLUM_NHANVIEN() {
        return COLUM_NHANVIEN;
    }

    public void setCOLUM_NHANVIEN(String COLUM_NHANVIEN) {
        this.COLUM_NHANVIEN = COLUM_NHANVIEN;
    }

    public String getCOLUM_SOLUONGBD() {
        return COLUM_SOLUONGBD;
    }

    public void setCOLUM_SOLUONGBD(String COLUM_SOLUONGBD) {
        this.COLUM_SOLUONGBD = COLUM_SOLUONGBD;
    }

    public String getCOLUM_SOLUONGNHAP() {
        return COLUM_SOLUONGNHAP;
    }

    public void setCOLUM_SOLUONGNHAP(String COLUM_SOLUONGNHAP) {
        this.COLUM_SOLUONGNHAP = COLUM_SOLUONGNHAP;
    }
}
