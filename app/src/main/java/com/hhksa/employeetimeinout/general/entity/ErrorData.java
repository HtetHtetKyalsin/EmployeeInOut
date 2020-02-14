package com.hhksa.employeetimeinout.general.entity;

public class ErrorData {
    String code;
    String detail;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ErrorData(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }
}
