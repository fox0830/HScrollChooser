package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.GsonUtil;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class GetBmiReq extends BasicReq {

    public GetBmiReq(FormData formData) {
        this.partnerID = "173";
        this.invoke = "bmi";
        this.formData = new Gson().toJson(formData);
        this.token = MD5.EncoderByMd5(this.formData);
    }

    public static class FormData {
        String tall;
        String weight;

        public FormData(String tall, String weight) {
            this.tall = tall;
            this.weight = weight;
        }
    }
}
