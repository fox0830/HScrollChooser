package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class ChangeDataReq extends BasicReq {
    public ChangeDataReq(FormData formData) {
        this.partnerID = "173";
        this.invoke = "modifylc";
        this.formData = new Gson().toJson(formData);
        this.token = MD5.EncoderByMd5(this.formData);
    }

    public static class FormData {
        String mb_no;
        String lc_id;
        String lc_name;
        String lc_tel;
        String lc_age;
        String lc_sex;
        String lc_pwd;
        String lc_tall;
        String lc_weight;
        String lc_bmi;
        String lc_fat;
        String lc_target;
        String lc_remark;

        public FormData(String mb_no, String lc_id, String lc_name, String lc_tel, String lc_age, String lc_sex, String lc_pwd, String lc_tall, String lc_weight, String lc_bmi, String lc_fat, String lc_target, String lc_remark) {
            this.mb_no = mb_no;
            this.lc_id = lc_id;
            this.lc_name = lc_name;
            this.lc_tel = lc_tel;
            this.lc_age = lc_age;
            this.lc_sex = lc_sex;
            this.lc_pwd = lc_pwd;
            this.lc_tall = lc_tall;
            this.lc_weight = lc_weight;
            this.lc_bmi = lc_bmi;
            this.lc_fat = lc_fat;
            this.lc_target = lc_target;
            this.lc_remark = lc_remark;
        }
    }
}


