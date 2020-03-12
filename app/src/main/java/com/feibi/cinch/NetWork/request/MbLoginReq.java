package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class MbLoginReq extends BasicReq {
    public MbLoginReq(FormData formData) {
        this.partnerID = "173";
        this.invoke = "mblogin";
        this.formData = new Gson().toJson(formData);
        this.token = MD5.EncoderByMd5(this.formData);
    }

    public static class FormData {
        String mb_no;
        String mb_pwd;

        public FormData(String mb_no, String mb_pwd) {
            this.mb_no = mb_no;
            this.mb_pwd = mb_pwd;
        }
    }
}
