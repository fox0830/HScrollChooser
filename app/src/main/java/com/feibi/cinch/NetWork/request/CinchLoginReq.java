package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class CinchLoginReq  {
        String lc_id;
        String lc_pwd;

        public CinchLoginReq(String lc_id, String lc_pwd) {
            this.lc_id = lc_id;
            this.lc_pwd = lc_pwd;

    }
}
