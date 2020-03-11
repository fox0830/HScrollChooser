package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class CinchLoginReq extends BasicReq {
    public CinchLoginReq(FormData formData) {
        this.partnerID = "173";
        this.invoke = "lclogin";
        this.formData = new Gson().toJson(formData);
        this.token = MD5.EncoderByMd5(this.formData);
    }

    public static class FormData {
        String lc_id;
        String lc_pwd;

        public FormData(String lc_id, String lc_pwd) {
            this.lc_id = lc_id;
            this.lc_pwd = lc_pwd;
        }
    }
}
