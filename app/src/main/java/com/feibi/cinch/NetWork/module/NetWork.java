package com.feibi.cinch.NetWork.module;

import android.content.Context;

import com.feibi.cinch.NetWork.basic.BasicModule;
import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.NetWork.request.FileUploadReq;
import com.feibi.cinch.NetWork.request.UploadTradeReq;
import com.feibi.cinch.NetWork.respond.AddTradeRes;
import com.feibi.cinch.NetWork.respond.FileUploadRes;
import com.feibi.cinch.NetWork.respond.GetTradeRes;
import com.feibi.cinch.NetWork.respond.UploadInfoRes;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class NetWork extends BasicModule {
    private Context mContext;
    public NetWork(Context context) {
        mContext = context;
    }

    public void addTrade(String url, final ReqCallBack<AddTradeRes> reqCallBack) {
        get(mContext, url, new BasicReq(), reqCallBack, AddTradeRes.class);
    }
    public void uploadPic(String url, FileUploadReq req ,final ReqCallBack<FileUploadRes> reqCallBack) {
        fileUpload(mContext, url, req, reqCallBack, FileUploadRes.class);
    }
    public void getTrade(String url, final ReqCallBack<GetTradeRes> reqCallBack) {
        get(mContext, url,  new BasicReq(), reqCallBack, GetTradeRes.class);
    }
    public void delSpot(String url, final ReqCallBack<UploadInfoRes> reqCallBack) {
        delete(mContext, url,  new BasicReq(), reqCallBack, UploadInfoRes.class);
    }
    public void uploadInfo(String url, UploadTradeReq req , final ReqCallBack<UploadInfoRes> reqCallBack) {
        postJsonObj(mContext, url, req, reqCallBack, UploadInfoRes.class);
    }
}
