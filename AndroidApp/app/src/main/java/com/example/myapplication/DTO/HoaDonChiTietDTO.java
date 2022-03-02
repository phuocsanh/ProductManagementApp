package com.example.myapplication.DTO;

public class HoaDonChiTietDTO {
    private String COLUMN_ID = "idHoaDonChiTiet";
    private String COLUMN_IDHD = "idHoaDon";
    private String COLUMN_IDHH = "idHangHoa";
    private String COLUMN_SOLUONG = "soLuong";

    public String getCOLUMN_ID() {
        return COLUMN_ID;
    }

    public void setCOLUMN_ID(String COLUMN_ID) {
        this.COLUMN_ID = COLUMN_ID;
    }

    public String getCOLUMN_IDHD() {
        return COLUMN_IDHD;
    }

    public void setCOLUMN_IDHD(String COLUMN_IDHD) {
        this.COLUMN_IDHD = COLUMN_IDHD;
    }

    public String getCOLUMN_IDHH() {
        return COLUMN_IDHH;
    }

    public void setCOLUMN_IDHH(String COLUMN_IDHH) {
        this.COLUMN_IDHH = COLUMN_IDHH;
    }

    public String getCOLUMN_SOLUONG() {
        return COLUMN_SOLUONG;
    }

    public void setCOLUMN_SOLUONG(String COLUMN_SOLUONG) {
        this.COLUMN_SOLUONG = COLUMN_SOLUONG;
    }
}
