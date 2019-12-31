package com.feibi.trade.NetWork.module;

import android.content.Context;

import com.feibi.trade.NetWork.basic.BasicModule;
import com.feibi.trade.NetWork.request.FileUploadReq;
import com.feibi.trade.NetWork.request.GetAreaListReq;
import com.feibi.trade.NetWork.request.GetCountryListReq;
import com.feibi.trade.NetWork.respond.FileUploadRes;
import com.feibi.trade.NetWork.respond.GetAreaListRes;
import com.feibi.trade.NetWork.respond.GetCountryListRes;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class System extends BasicModule {
    public static final String MODULE_URL = "/api/System";
    public static final String getCountryListUrl = MODULE_URL + "/get_country_list";
    public static final String getAreaListUrl = MODULE_URL + "/get_district_list";
    public static final String fileUploadUrl = MODULE_URL + "/file_upload_user_head";
    private Context mContext;
    public System(Context context) {
        mContext = context;
    }

    public void GetCountryList(GetCountryListReq req, final ReqCallBack<BasicResponseBody<GetCountryListRes>> reqCallBack) {
        post(mContext, getCountryListUrl, req, reqCallBack, GetCountryListRes.class);
    }
    public void GetAreaList(GetAreaListReq req, final ReqCallBack<BasicResponseBody<GetAreaListRes>> reqCallBack) {
        post(mContext, getAreaListUrl, req, reqCallBack, GetAreaListRes.class);
    }
    public void FileUpload(FileUploadReq req, final ReqCallBack<BasicResponseBody<FileUploadRes>> reqCallBack) {
//        post(mContext, fileUploadUrl, req, reqCallBack, FileUploadRes.class);
        fileUpload(mContext, fileUploadUrl, req, reqCallBack, FileUploadRes.class);
    }
}
