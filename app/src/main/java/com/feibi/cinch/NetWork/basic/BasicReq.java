package com.feibi.cinch.NetWork.basic;

import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class BasicReq {
    public String partnerID;
    public String invoke;
    public String token;
    public String formData;

    public BasicReq(String invoke, Object formData) {
        this.partnerID ="173";
        this.invoke = invoke;
        this.formData = new Gson().toJson(formData);
        this.token = MD5.EncoderByMd5(this.formData);
    }

    public String getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(String partnerID) {
        this.partnerID = partnerID;
    }

    public String getInvoke() {
        return invoke;
    }

    public void setInvoke(String invoke) {
        this.invoke = invoke;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }
}
