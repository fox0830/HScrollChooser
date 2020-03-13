package com.feibi.cinch.NetWork.request;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.utils.MD5;
import com.google.gson.Gson;

public class WriteAnswerReq {
    String lc_id;
    String ans1;
    String ans2;
    String ans3;
    String ans4;
    String ans5;
    String ans6;

    public WriteAnswerReq(String lc_id, String ans1, String ans2, String ans3, String ans4, String ans5, String ans6) {
        this.lc_id = lc_id;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.ans5 = ans5;
        this.ans6 = ans6;
    }
}

