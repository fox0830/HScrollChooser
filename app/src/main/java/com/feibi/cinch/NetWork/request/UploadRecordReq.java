package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

import java.io.File;

public class UploadRecordReq  {
    String lc_id;
    String task_no;
    String u_pic ="filename";

    public UploadRecordReq(String lc_id, String task_no, String u_pic) {
        this.lc_id = lc_id;
        this.task_no = task_no;
        this.u_pic = u_pic;
    }
}
