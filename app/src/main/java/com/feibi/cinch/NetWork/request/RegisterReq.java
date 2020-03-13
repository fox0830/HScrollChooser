package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class RegisterReq  {

        String lc_id;
        String lc_pwd;
        String lc_pwd_confirm;
        String mb_no;
        String lc_name;
        String lc_tel;
        String lc_age;
        String lc_sex;
        String lc_tall;
        String lc_weight;
        String lc_bmi;
        String lc_fat;
        String lc_target;
        String lc_remark;

        public RegisterReq(String lc_id, String lc_pwd, String lc_pwd_confirm, String mb_no, String lc_name, String lc_tel, String lc_age, String lc_sex, String lc_tall, String lc_weight, String lc_bmi, String lc_fat, String lc_target, String lc_remark) {
            this.lc_id = lc_id;
            this.lc_pwd = lc_pwd;
            this.lc_pwd_confirm = lc_pwd_confirm;
            this.mb_no = mb_no;
            this.lc_name = lc_name;
            this.lc_tel = lc_tel;
            this.lc_age = lc_age;
            this.lc_sex = lc_sex;
            this.lc_tall = lc_tall;
            this.lc_weight = lc_weight;
            this.lc_bmi = lc_bmi;
            this.lc_fat = lc_fat;
            this.lc_target = lc_target;
            this.lc_remark = lc_remark;
        }

}
