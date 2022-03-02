package com.example.myapplication.DTO;

import java.io.Serializable;

public class HoaDonDTO implements Serializable {

    private String COLUMN_ID = "idHoaDon";
    private String COLUMN_KH = "khachHang";
    private String COLUMN_NV = "nhanVien";
    private String COLUMN_GHICHU = "ghiChu";
    private String COLUMN_TONGTIEN = "tongTien";
    private String COLUMN_THOIGIANTAO = "thoiGianTao";
    private String COLUMN_THOIGIANSUA = "thoiGianSua";

    public String getCOLUMN_TONGTIEN() {
        return COLUMN_TONGTIEN;
    }

    public void setCOLUMN_TONGTIEN(String COLUMN_TONGTIEN) {
        this.COLUMN_TONGTIEN = COLUMN_TONGTIEN;
    }

    public String getCOLUMN_ID() {
        return COLUMN_ID;
    }

    public void setCOLUMN_ID(String COLUMN_ID) {
        this.COLUMN_ID = COLUMN_ID;
    }

    public String getCOLUMN_KH() {
        return COLUMN_KH;
    }

    public void setCOLUMN_KH(String COLUMN_KH) {
        this.COLUMN_KH = COLUMN_KH;
    }

    public String getCOLUMN_NV() {
        return COLUMN_NV;
    }

    public void setCOLUMN_NV(String COLUMN_NV) {
        this.COLUMN_NV = COLUMN_NV;
    }

    public String getCOLUMN_GHICHU() {
        return COLUMN_GHICHU;
    }

    public void setCOLUMN_GHICHU(String COLUMN_GHICHU) {
        this.COLUMN_GHICHU = COLUMN_GHICHU;
    }

    public String getCOLUMN_THOIGIANTAO() {
        return COLUMN_THOIGIANTAO;
    }

    public void setCOLUMN_THOIGIANTAO(String COLUMN_THOIGIANTAO) {
        this.COLUMN_THOIGIANTAO = COLUMN_THOIGIANTAO;
    }

    public String getCOLUMN_THOIGIANSUA() {
        return COLUMN_THOIGIANSUA;
    }

    public void setCOLUMN_THOIGIANSUA(String COLUMN_THOIGIANSUA) {
        this.COLUMN_THOIGIANSUA = COLUMN_THOIGIANSUA;
    }
}
