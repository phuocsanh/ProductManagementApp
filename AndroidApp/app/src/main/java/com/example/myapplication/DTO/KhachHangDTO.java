package com.example.myapplication.DTO;

public class KhachHangDTO {
    private String COLUM_ID = "idkhachhang";
    private String COLUM_TENKHACHHANG = "tenkhachhang";
    private String COLUM_SDT = "sdt";

    public String getCOLUM_ID() {
        return COLUM_ID;
    }

    public void setCOLUM_ID(String COLUM_ID) {
        this.COLUM_ID = COLUM_ID;
    }

    public String getCOLUM_TENKHACHHANG() {
        return COLUM_TENKHACHHANG;
    }

    public void setCOLUM_TENKHACHHANG(String COLUM_TENKHACHHANG) {
        this.COLUM_TENKHACHHANG = COLUM_TENKHACHHANG;
    }

    public String getCOLUM_SDT() {
        return COLUM_SDT;
    }

    public void setCOLUM_SDT(String COLUM_SDT) {
        this.COLUM_SDT = COLUM_SDT;
    }
}
