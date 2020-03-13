package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.GsonUtil;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class GetBmiReq  {

        String tall;
        String weight;

        public GetBmiReq(String tall, String weight) {
            this.tall = tall;
            this.weight = weight;
        }

}
