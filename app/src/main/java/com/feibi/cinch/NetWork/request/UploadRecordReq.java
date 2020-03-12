package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

import java.io.File;

public class UploadRecordReq extends BasicReq {
    File[] files;
    public UploadRecordReq(FormData formData, File[] files) {
        this.partnerID = "173";
        this.invoke = "lcupimg";
        this.formData = new Gson().toJson(formData);
        this.token = MD5.EncoderByMd5(this.formData);
        this.files = files;
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public static class FormData {
        String lc_id;
        String task_no;
        String u_pic ="filename";
        public FormData(String lc_id, String task_no) {
            this.lc_id = lc_id;
            this.task_no = task_no;
        }
    }
}
