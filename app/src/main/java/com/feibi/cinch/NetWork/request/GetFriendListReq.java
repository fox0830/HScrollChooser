package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;


public class GetFriendListReq extends BasicReq {
    public GetFriendListReq(FormData formData) {
        this.partnerID = "173";
        this.invoke = "listlc";
        this.formData = new Gson().toJson(formData);
        this.token = MD5.EncoderByMd5(this.formData);
    }


    public static class FormData {
        String on_status;  //o-old，n-new
        String mb_no;

        public FormData(String on_status, String mb_no) {
            this.on_status = on_status;
            this.mb_no = mb_no;
        }
    }
}
