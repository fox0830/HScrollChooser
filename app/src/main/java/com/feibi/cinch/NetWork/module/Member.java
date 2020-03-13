package com.feibi.cinch.NetWork.module;

import android.content.Context;

import com.feibi.cinch.NetWork.basic.BasicModule;
import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.NetWork.respond.CinchData;

import java.util.ArrayList;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class Member extends BasicModule {
    private Context mContext;

    public Member(Context context) {
        mContext = context;
    }

//    public void getSlogan(SloganReq req, final ReqCallBack<BasicResponseBody<ArrayList>> reqCallBack) {
//        get(mContext, "member.php",req, reqCallBack, ArrayList.class);
//    }
//    public void getVerCode(OnlyLcIdReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
//        get(mContext, "member.php",req, reqCallBack, Object.class);
//    }
//    public void register(RegisterReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
//        get(mContext, "member.php",req, reqCallBack, Object.class);
//    }
//    public void getBmi(GetBmiReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
//        get(mContext, "member.php",req, reqCallBack, Object.class);
//    }
//
//    public void writeAnswer(WriteAnswerReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
//        get(mContext, "member.php",req, reqCallBack, Object.class);
//    }

//    public void cinchLogin(BasicReq req, final ReqCallBack<BasicResponseBody<CinchData>> reqCallBack) {
//        get(mContext, "member.php",req, reqCallBack, CinchData.class);
//    }
//    public void GetCinchUserData(GetCinchUserDataReq req, final ReqCallBack<BasicResponseBody<CinchData>> reqCallBack) {
//        get(mContext, "member.php",req, reqCallBack, CinchData.class);
//    }

    //    public void mbLogin(MbLoginReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
//        get(mContext, "member.php",req, reqCallBack, Object.class);
//    }
//    public void ForgetPwd(ForgetPwdReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
//        get(mContext, "member.php",req, reqCallBack, Object.class);
//    }
//    public void ChangePwd(ChangePwdReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
//        get(mContext, "member.php",req, reqCallBack, Object.class);
//    }
//    public void ChangeData(ChangeDataReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
//        get(mContext, "member.php",req, reqCallBack, Object.class);
//    }
//    public void CheckId(BasicReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
//        get(mContext, "member.php",req, reqCallBack, Object.class);
//    }
//    public void GetFriendList(BasicReq req, final ReqCallBack<BasicResponseBody<ArrayList>> reqCallBack) {
//        get(mContext, "member.php",req, reqCallBack, ArrayList.class);
//    }
    public void GetArrayList(BasicReq req, final ReqCallBack<BasicResponseBody<ArrayList>> reqCallBack) {
        get(mContext, "member.php", req, reqCallBack, ArrayList.class);
    }

    public void GetCinchData(BasicReq req, final ReqCallBack<BasicResponseBody<CinchData>> reqCallBack) {
        get(mContext, "member.php", req, reqCallBack, CinchData.class);
    }

    public void GetObject(BasicReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        get(mContext, "member.php", req, reqCallBack, Object.class);
    }
//    public void UploadPic(UploadPicReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
//        fileUpload(mContext, "member.php",req, reqCallBack, Object.class);
//    }

//    public void UploadRecordPic(UploadRecordReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
//        fileUpload(mContext, "team.php", req, reqCallBack, Object.class);
//    }

}
