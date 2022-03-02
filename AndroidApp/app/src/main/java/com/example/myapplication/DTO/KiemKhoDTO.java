package com.example.myapplication.DTO;

import java.io.Serializable;

public class KiemKhoDTO implements Serializable {
    private String COLUM_ID = "idKiemKho";
    private String COLUM_HANGHOA = "hangHoa";
    private String COLUM_NHANVIEN = "nhanVien";
    private String COLUM_SOLUONGBANDAU = "soLuongBanDau";
    private String COLUM_TONCUOI = "tonCuoi";
    private String COLUM_TIMESTAMP = "timestamp";
    private String COLUM_NOITAOPHIEU = "noitaophieu";

    public String getCOLUM_NOITAOPHIEU() {
        return COLUM_NOITAOPHIEU;
    }

    public void setCOLUM_NOITAOPHIEU(String COLUM_NOITAOPHIEU) {
        this.COLUM_NOITAOPHIEU = COLUM_NOITAOPHIEU;
    }

    public String getCOLUM_ID() {
        return COLUM_ID;
    }

    public void setCOLUM_ID(String COLUM_ID) {
        this.COLUM_ID = COLUM_ID;
    }

    public String getCOLUM_HANGHOA() {
        return COLUM_HANGHOA;
    }

    public void setCOLUM_HANGHOA(String COLUM_HANGHOA) {
        this.COLUM_HANGHOA = COLUM_HANGHOA;
    }

    public String getCOLUM_NHANVIEN() {
        return COLUM_NHANVIEN;
    }

    public void setCOLUM_NHANVIEN(String COLUM_NHANVIEN) {
        this.COLUM_NHANVIEN = COLUM_NHANVIEN;
    }

    public String getCOLUM_SOLUONGBANDAU() {
        return COLUM_SOLUONGBANDAU;
    }

    public void setCOLUM_SOLUONGBANDAU(String COLUM_SOLUONGBANDAU) {
        this.COLUM_SOLUONGBANDAU = COLUM_SOLUONGBANDAU;
    }

    public String getCOLUM_TONCUOI() {
        return COLUM_TONCUOI;
    }

    public void setCOLUM_TONCUOI(String COLUM_TONCUOI) {
        this.COLUM_TONCUOI = COLUM_TONCUOI;
    }

    public String getCOLUM_TIMESTAMP() {
        return COLUM_TIMESTAMP;
    }

    public void setCOLUM_TIMESTAMP(String COLUM_TIMESTAMP) {
        this.COLUM_TIMESTAMP = COLUM_TIMESTAMP;
    }
}
