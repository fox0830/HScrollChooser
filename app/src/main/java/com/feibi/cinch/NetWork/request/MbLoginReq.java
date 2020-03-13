package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class MbLoginReq  {
        String mb_no;
        String mb_pwd;

        public MbLoginReq(String mb_no, String mb_pwd) {
            this.mb_no = mb_no;
            this.mb_pwd = mb_pwd;
    }
}
