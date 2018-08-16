package com.ucmed.sxpt.entity.dto;

public class UserDto {
    /**
     * 卡号
     */
    private String kh;
    /**
     * 卡类型
     */
    private String klx;

    public String getKh() {
        return kh;
    }

    public void setKh(String kh) {
        this.kh = kh;
    }

    public String getKlx() {
        return klx;
    }

    public void setKlx(String klx) {
        this.klx = klx;
    }
}