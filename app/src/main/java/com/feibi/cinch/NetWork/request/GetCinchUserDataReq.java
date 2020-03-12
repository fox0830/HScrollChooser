package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class GetCinchUserDataReq extends BasicReq {
    public GetCinchUserDataReq(FormData formData) {
        this.partnerID = "173";
        this.invoke = "getlc";
        this.formData = new Gson().toJson(formData);
        this.token = MD5.EncoderByMd5(this.formData);
    }

    public static class FormData {
        String mb_no;
        String lc_id;
        public FormData(String mb_no, String lc_id) {
            this.mb_no = mb_no;
            this.lc_id = lc_id;
        }
    }
}
