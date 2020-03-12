package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class ChangePwdReq extends BasicReq {
    public ChangePwdReq(FormData formData) {
        this.partnerID = "173";
        this.invoke = "modifypwd";
        this.formData = new Gson().toJson(formData);
        this.token = MD5.EncoderByMd5(this.formData);
    }

    public static class FormData {
        String lc_id;
        String lc_pwd;
        String lc_pwd_new;
        String lc_pwd_confirm;

        public FormData(String lc_id, String lc_pwd, String lc_pwd_new, String lc_pwd_confirm) {
            this.lc_id = lc_id;
            this.lc_pwd = lc_pwd;
            this.lc_pwd_new = lc_pwd_new;
            this.lc_pwd_confirm = lc_pwd_confirm;
        }
    }
}
