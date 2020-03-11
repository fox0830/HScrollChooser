package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

import java.io.File;

public class UploadPicReq extends BasicReq {

    public UploadPicReq(FormData formData) {
        this.partnerID = "173";
        this.invoke = "upimg";
        this.formData = new Gson().toJson(formData);
        this.token = MD5.EncoderByMd5(this.formData);
    }

    public static class FormData {
        String lc_id;
        File lc_pic;

        public FormData(String lc_id, File lc_pic) {
            this.lc_id = lc_id;
            this.lc_pic = lc_pic;
        }
    }
}
