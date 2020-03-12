package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

import java.io.File;

public class UploadRecordReq extends BasicReq {

    public UploadRecordReq(FormData formData) {
        this.partnerID = "173";
        this.invoke = "lcupimg";
        this.formData = new Gson().toJson(formData);
        this.token = MD5.EncoderByMd5(this.formData);
    }
    public static class FormData {
        String lc_id;
        String task_no;
        String u_pic ="filename";

        public FormData(String lc_id, String task_no, File[] u_pic) {
            this.lc_id = lc_id;
            this.task_no = task_no;
        }
    }
}
