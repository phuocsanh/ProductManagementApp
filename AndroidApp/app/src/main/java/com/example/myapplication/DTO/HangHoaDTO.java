package com.example.myapplication.DTO;

import java.io.Serializable;

public class HangHoaDTO implements Serializable {
    private String COLUMN_ID = "idHangHoa";
    private String COLUMN_NAME = "TenHangHoa";
    private String COLUMN_LOAIHANG = "idLoaiHang";
    private String COLUMN_GIAVON = "GiaVon";
    private String COLUMN_GIABAN = "GiaBan";
    private String COLUMN_TONKHO = "TonKho";
    private String COLUMN_VITRI = "ViTri";
    private String COLUMN_DINHMUCTON_MIN = "DinhMucTonMin";
    private String COLUMN_DINHMUCTON_MAX = "DinhMucTonMax";
    private String COLUMN_HINHDAIDIEN = "HinhDaiDien";
    private String COLUMN_GHICHU = "GhiChu";
    private String COLUMN_TIMESTAMP = "timestamp";
    private String COLUMN_SOLUONGHD = "soLuongHoaDon";
    private String COLUMN_THUCTE = "soLuongThucTe";
    public String getCOLUMN_THUCTE() {
        return COLUMN_THUCTE;
    }

    public void setCOLUMN_THUCTE(String COLUMN_THUCTE) {
        this.COLUMN_THUCTE = COLUMN_THUCTE;
    }



    public String getCOLUMN_SOLUONGHD() {
        return COLUMN_SOLUONGHD;
    }

    public void setCOLUMN_SOLUONGHD(String COLUMN_SOLUONGHD) {
        this.COLUMN_SOLUONGHD = COLUMN_SOLUONGHD;
    }



    public String getCOLUMN_GHICHU() {
        return COLUMN_GHICHU;
    }

    public void setCOLUMN_GHICHU(String COLUMN_GHICHU) {
        this.COLUMN_GHICHU = COLUMN_GHICHU;
    }



    public String getCOLUMN_ID() {
        return COLUMN_ID;
    }

    public void setCOLUMN_ID(String COLUMN_ID) {
        this.COLUMN_ID = COLUMN_ID;
    }

    public String getCOLUMN_NAME() {
        return COLUMN_NAME;
    }

    public void setCOLUMN_NAME(String COLUMN_NAME) {
        this.COLUMN_NAME = COLUMN_NAME;
    }

    public String getCOLUMN_LOAIHANG() {
        return COLUMN_LOAIHANG;
    }

    public void setCOLUMN_LOAIHANG(String COLUMN_LOAIHANG) {
        this.COLUMN_LOAIHANG = COLUMN_LOAIHANG;
    }

    public String getCOLUMN_GIAVON() {
        return COLUMN_GIAVON;
    }

    public void setCOLUMN_GIAVON(String COLUMN_GIAVON) {
        this.COLUMN_GIAVON = COLUMN_GIAVON;
    }

    public String getCOLUMN_GIABAN() {
        return COLUMN_GIABAN;
    }

    public void setCOLUMN_GIABAN(String COLUMN_GIABAN) {
        this.COLUMN_GIABAN = COLUMN_GIABAN;
    }

    public String getCOLUMN_TONKHO() {
        return COLUMN_TONKHO;
    }

    public void setCOLUMN_TONKHO(String COLUMN_TONKHO) {
        this.COLUMN_TONKHO = COLUMN_TONKHO;
    }

    public String getCOLUMN_VITRI() {
        return COLUMN_VITRI;
    }

    public void setCOLUMN_VITRI(String COLUMN_VITRI) {
        this.COLUMN_VITRI = COLUMN_VITRI;
    }

    public String getCOLUMN_DINHMUCTON_MIN() {
        return COLUMN_DINHMUCTON_MIN;
    }

    public void setCOLUMN_DINHMUCTON_MIN(String COLUMN_DINHMUCTON_MIN) {
        this.COLUMN_DINHMUCTON_MIN = COLUMN_DINHMUCTON_MIN;
    }

    public String getCOLUMN_DINHMUCTON_MAX() {
        return COLUMN_DINHMUCTON_MAX;
    }

    public void setCOLUMN_DINHMUCTON_MAX(String COLUMN_DINHMUCTON_MAX) {
        this.COLUMN_DINHMUCTON_MAX = COLUMN_DINHMUCTON_MAX;
    }

    public String getCOLUMN_HINHDAIDIEN() {
        return COLUMN_HINHDAIDIEN;
    }

    public void setCOLUMN_HINHDAIDIEN(String COLUMN_HINHDAIDIEN) {
        this.COLUMN_HINHDAIDIEN = COLUMN_HINHDAIDIEN;
    }

    public String getCOLUMN_TIMESTAMP() {
        return COLUMN_TIMESTAMP;
    }

    public void setCOLUMN_TIMESTAMP(String COLUMN_TIMESTAMP) {
        this.COLUMN_TIMESTAMP = COLUMN_TIMESTAMP;
    }
}
