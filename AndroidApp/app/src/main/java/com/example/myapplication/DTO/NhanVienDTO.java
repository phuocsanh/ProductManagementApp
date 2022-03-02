package com.example.myapplication.DTO;

import java.io.Serializable;

public class NhanVienDTO implements Serializable {
    private String COLUM_ID = "idnhanvien";
    private String COLUM_TENDANGNHAP = "tendangnhap";
    private String COLUM_MATKHAU = "matkhau";
    private String COLUM_CHUCVU = "chucvu";

    public String getCOLUM_ID() {
        return COLUM_ID;
    }

    public void setCOLUM_ID(String COLUMN_ID) {
        this.COLUM_ID = COLUMN_ID;
    }

    public String getCOLUM_TENDANGNHAP() {
        return COLUM_TENDANGNHAP;
    }

    public void setCOLUM_TENDANGNHAP(String COLUMN_TENDANGNHAP) {
        this.COLUM_TENDANGNHAP = COLUMN_TENDANGNHAP;
    }

    public String getCOLUM_MATKHAU() {
        return COLUM_MATKHAU;
    }

    public void setCOLUM_MATKHAU(String COLUM_MATKHAU) {
        this.COLUM_MATKHAU = COLUM_MATKHAU;
    }

    public String getCOLUM_CHUCVU() {
        return COLUM_CHUCVU;
    }

    public void setCOLUM_CHUCVU(String COLUM_CHUCVU) {
        this.COLUM_CHUCVU = COLUM_CHUCVU;
    }
}