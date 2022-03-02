package com.example.myapplication.DTO;

import java.io.Serializable;

public class NhomHangDTO implements Serializable {
    private String COLUMN_ID = "idnhomhang";
    private String COLUMN_NAME = "tennhomhang";

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
}
