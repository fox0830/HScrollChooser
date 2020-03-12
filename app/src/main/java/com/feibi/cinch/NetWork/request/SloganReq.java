package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class SloganReq  extends BasicReq {
    public SloganReq(FormData formData) {
        this.partnerID = "173";
        this.invoke = "slogan";
        this.formData = new Gson().toJson(formData);
        this.token = MD5.EncoderByMd5(this.formData);
    }

    public static class FormData {
        String show;

        public FormData(String show) {
            this.show = show;
        }
    }
}
