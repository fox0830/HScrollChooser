package com.feibi.trade.NetWork.module;

import android.content.Context;

import com.feibi.trade.NetWork.basic.BasicModule;
import com.feibi.trade.NetWork.request.BindThirdReq;
import com.feibi.trade.NetWork.request.EmptyReq;
import com.feibi.trade.NetWork.request.ForgetPwdSendCodeReq;
import com.feibi.trade.NetWork.request.ForgetPwdSetPwdReq;
import com.feibi.trade.NetWork.request.ForgetPwdVerCodeReq;
import com.feibi.trade.NetWork.request.GetMsgListReq;
import com.feibi.trade.NetWork.request.LoginReq;
import com.feibi.trade.NetWork.request.ReadMsgReq;
import com.feibi.trade.NetWork.request.RegisterReq;
import com.feibi.trade.NetWork.request.SetUserInfoReq;
import com.feibi.trade.NetWork.request.addEventReq;
import com.feibi.trade.NetWork.request.deleteEventReq;
import com.feibi.trade.NetWork.respond.ForgetPwdVerCodeRes;
import com.feibi.trade.NetWork.respond.GetMsgListRes;
import com.feibi.trade.NetWork.respond.LoginRes;
import com.feibi.trade.NetWork.respond.RegisterRes;
import com.feibi.trade.NetWork.respond.getEventRes;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class Account extends BasicModule {
    public static final String MODULE_URL = "/api/Member";
    public static final String registerUrl = MODULE_URL + "/regedit";
    public static final String loginUrl = MODULE_URL + "/login";
    public static final String bindThirdUrl = MODULE_URL + "/binding_third";
    public static final String login_thirdUrl = MODULE_URL + "/login_third";
    public static final String forgetPwdSendCodeUrl = MODULE_URL + "/to_email_forget";
    public static final String forgetPwdVerCodeUrl = MODULE_URL + "/verification";
    public static final String forgetPwdSetPwdUrl = MODULE_URL + "/set_password";
    public static final String setUserInfoUrl = MODULE_URL + "/set_user_info";
    public static final String getMsgListUrl = MODULE_URL + "/get_msg_list";
    public static final String readMsgUrl = MODULE_URL + "/msg_reading";

    public static final String addEventUrl = MODULE_URL + "/build_device_event";
    public static final String getEventUrl = MODULE_URL + "/get_my_event";
    public static final String deleteEventUrl = MODULE_URL + "/event_delete";
    private Context mContext;
    public Account(Context context) {
        mContext = context;
    }

    /**
     * 模块Account 请求方法
     */
    public void Register(RegisterReq req, final ReqCallBack<BasicResponseBody<RegisterRes>> reqCallBack) {
        post(mContext, registerUrl, req, reqCallBack, RegisterRes.class);
    }
    public void Login(LoginReq req, final ReqCallBack<BasicResponseBody<LoginRes>> reqCallBack) {
        post(mContext, loginUrl, req, reqCallBack, LoginRes.class);
    }
    public void BindThird(BindThirdReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        post(mContext, bindThirdUrl, req, reqCallBack, Object.class);
    }
    public void LoginThird(BindThirdReq req, final ReqCallBack<BasicResponseBody<LoginRes>> reqCallBack) {
        post(mContext, login_thirdUrl, req, reqCallBack, LoginRes.class);
    }
    public void ForgetPwdSendCode(ForgetPwdSendCodeReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        post(mContext, forgetPwdSendCodeUrl, req, reqCallBack, Object.class);
    }
    public void ForgetPwdVerCode(ForgetPwdVerCodeReq req, final ReqCallBack<BasicResponseBody<ForgetPwdVerCodeRes>> reqCallBack) {
        post(mContext, forgetPwdVerCodeUrl, req, reqCallBack, ForgetPwdVerCodeRes.class);
    }
    public void ForgetPwdSetPwd(ForgetPwdSetPwdReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        post(mContext, forgetPwdSetPwdUrl, req, reqCallBack, Object.class);
    }
    public void SetUserInfo(SetUserInfoReq req, final ReqCallBack<BasicResponseBody<LoginRes>> reqCallBack) {
        post(mContext, setUserInfoUrl, req, reqCallBack, LoginRes.class);
    }
    public void GetMsgList(GetMsgListReq req, final ReqCallBack<BasicResponseBody<GetMsgListRes>> reqCallBack) {
        post(mContext, getMsgListUrl, req, reqCallBack, GetMsgListRes.class);
    }
    public void ReadMsg(ReadMsgReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        post(mContext, readMsgUrl, req, reqCallBack, Object.class);
    }

    public void addEvent(addEventReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        post(mContext, addEventUrl, req, reqCallBack, Object.class);
    }
    public void getEvent(EmptyReq req, final ReqCallBack<BasicResponseBody<getEventRes>> reqCallBack) {
        post(mContext, getEventUrl, req, reqCallBack, getEventRes.class);
    }
    public void deleteEvent(deleteEventReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        post(mContext, deleteEventUrl, req, reqCallBack, Object.class);
    }
}
