package com.feibi.cinch.NetWork.module;

import android.content.Context;

import com.feibi.cinch.NetWork.basic.BasicModule;
import com.feibi.cinch.NetWork.basic.BasicReq;

import java.util.ArrayList;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class Team extends BasicModule {
    private Context mContext;

    public Team(Context mContext) {
        this.mContext = mContext;
    }

    public void GetArrayList(BasicReq req, final ReqCallBack<BasicResponseBody<ArrayList>> reqCallBack) {
        get(mContext, "team.php", req, reqCallBack, ArrayList.class);
    }
    public void GetObject(BasicReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        get(mContext, "team.php", req, reqCallBack, Object.class);
    }
}
