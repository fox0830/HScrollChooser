package com.feibi.cinch.NetWork.module;

import android.content.Context;

import com.feibi.cinch.NetWork.basic.BasicModule;
import com.feibi.cinch.NetWork.request.ChangeDataReq;
import com.feibi.cinch.NetWork.request.ChangePwdReq;
import com.feibi.cinch.NetWork.request.CinchLoginReq;
import com.feibi.cinch.NetWork.request.ForgetPwdReq;
import com.feibi.cinch.NetWork.request.GetBmiReq;
import com.feibi.cinch.NetWork.request.GetCinchUserDataReq;
import com.feibi.cinch.NetWork.request.GetVerCodeReq;
import com.feibi.cinch.NetWork.request.MbLoginReq;
import com.feibi.cinch.NetWork.request.RegisterReq;
import com.feibi.cinch.NetWork.request.SloganReq;
import com.feibi.cinch.NetWork.request.UploadPicReq;
import com.feibi.cinch.NetWork.request.UploadRecordReq;
import com.feibi.cinch.NetWork.request.WriteAnswerReq;
import com.feibi.cinch.NetWork.respond.CinchData;
import com.feibi.cinch.NetWork.respond.SloganData;

import java.util.ArrayList;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class NetWork extends BasicModule {
    private Context mContext;
    public NetWork(Context context) {
        mContext = context;
    }


    public void getSlogan(SloganReq req, final ReqCallBack<BasicResponseBody<ArrayList>> reqCallBack) {
        get(mContext, "member.php",req, reqCallBack, ArrayList.class);
    }
    public void getVerCode(GetVerCodeReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        get(mContext, "member.php",req, reqCallBack, Object.class);
    }
    public void register(RegisterReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        get(mContext, "member.php",req, reqCallBack, Object.class);
    }

    public void getBmi(GetBmiReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        get(mContext, "member.php",req, reqCallBack, Object.class);
    }

    public void writeAnswer(WriteAnswerReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        get(mContext, "member.php",req, reqCallBack, Object.class);
    }

    public void cinchLogin(CinchLoginReq req, final ReqCallBack<BasicResponseBody<CinchData>> reqCallBack) {
        get(mContext, "member.php",req, reqCallBack, CinchData.class);
    }
    public void GetCinchUserData(GetCinchUserDataReq req, final ReqCallBack<BasicResponseBody<CinchData>> reqCallBack) {
        get(mContext, "member.php",req, reqCallBack, CinchData.class);
    }
    public void mbLogin(MbLoginReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        get(mContext, "member.php",req, reqCallBack, Object.class);
    }
    public void ForgetPwd(ForgetPwdReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        get(mContext, "member.php",req, reqCallBack, Object.class);
    }
    public void ChangePwd(ChangePwdReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        get(mContext, "member.php",req, reqCallBack, Object.class);
    }
    public void ChangeData(ChangeDataReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        get(mContext, "member.php",req, reqCallBack, Object.class);
    }

//    public void UploadPic(UploadPicReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
//        fileUpload(mContext, "member.php",req, reqCallBack, Object.class);
//    }

    public void UploadRecordPic(UploadRecordReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        fileUpload(mContext, "team.php",req, reqCallBack, Object.class);
    }

}
