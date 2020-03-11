package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class GetVerCodeReq extends BasicReq {


    public GetVerCodeReq(FormData formData) {
        this.partnerID ="173";
        this.invoke = "checkTel";
        this.formData = new Gson().toJson(formData);
        this.token = MD5.EncoderByMd5(this.formData);
    }


    public static class FormData{
        static  String   lc_id;

        public  FormData(String lc_id) {
            this.lc_id = lc_id;
        }
    }
}
