package com.feibi.trade.NetWork.request;


import com.feibi.trade.NetWork.basic.BasicReq;

public class RegisterReq extends BasicReq {
    private String username;
    private String phone;
    private String country;
    private String district;
    private String email;
    private String password;

    public RegisterReq(String username, String phone, String country, String district, String email, String password) {
        this.username = username;
        this.phone = phone;
        this.country = country;
        this.district = district;
        this.email = email;
        this.password = password;
    }
}
