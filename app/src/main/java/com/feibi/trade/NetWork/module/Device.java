package com.feibi.trade.NetWork.module;


import android.content.Context;

import com.feibi.trade.NetWork.basic.BasicModule;
import com.feibi.trade.NetWork.request.ChangeHostNameReq;
import com.feibi.trade.NetWork.request.DeviceBindReq;
import com.feibi.trade.NetWork.request.EditDeviceReq;
import com.feibi.trade.NetWork.request.EmptyReq;
import com.feibi.trade.NetWork.request.GetDeviceClassReq;
import com.feibi.trade.NetWork.request.deleteHostReq;
import com.feibi.trade.NetWork.request.getEnvironmentReq;
import com.feibi.trade.NetWork.request.getOneDeviceInfoReq;
import com.feibi.trade.NetWork.request.updateDeviceReq;
import com.feibi.trade.NetWork.respond.DeviceBindRes;
import com.feibi.trade.NetWork.respond.DeviceInfoRes;
import com.feibi.trade.NetWork.respond.GetDeviceClassRes;
import com.feibi.trade.NetWork.respond.getEnvironmentRes;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class Device extends BasicModule {
    public static final String MODULE_URL = "/api/Device";
    public static final String getDeviceClassUrl = MODULE_URL + "/get_device_class";
    public static final String deviceBindUrl = MODULE_URL + "/device_binding";
    public static final String getDeviceUrl = MODULE_URL + "/get_device_info";
    public static final String changeHostNameUrl = MODULE_URL + "/host_info_set";
    public static final String editDeviceUrl = MODULE_URL + "/device_info_set";
    public static final String deleteHostUrl = MODULE_URL + "/host_delete";
    public static final String updateDeviceUrl = MODULE_URL + "/update_device";
    public static final String getOneDeviceInfoUrl = MODULE_URL + "/get_device_info_no_aws";

    public static final String get_environmentUrl = MODULE_URL + "/get_environment";

    private Context mContext;
    public Device(Context context) {
        mContext = context;
    }

    public void getDeviceClass(GetDeviceClassReq req, final ReqCallBack<BasicResponseBody<GetDeviceClassRes>> reqCallBack) {
        post(mContext, getDeviceClassUrl, req, reqCallBack, GetDeviceClassRes.class);
    }
    public void deviceBind(DeviceBindReq req, final ReqCallBack<BasicResponseBody<DeviceBindRes>> reqCallBack) {
        post(mContext, deviceBindUrl, req, reqCallBack, DeviceBindRes.class);
    }
    public void getDevice(EmptyReq req, final ReqCallBack<BasicResponseBody<DeviceBindRes>> reqCallBack) {
        post(mContext, getDeviceUrl, req, reqCallBack, DeviceBindRes.class);
    }
    public void changeHostName(ChangeHostNameReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        post(mContext, changeHostNameUrl, req, reqCallBack, Object.class);
    }
    public void editDevice(EditDeviceReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        post(mContext, editDeviceUrl, req, reqCallBack, Object.class);
    }
    public void deleteHost(deleteHostReq req, final ReqCallBack<BasicResponseBody<Object>> reqCallBack) {
        post(mContext, deleteHostUrl, req, reqCallBack, Object.class);
    }
    public void updateDevice(updateDeviceReq req, final ReqCallBack<BasicResponseBody<DeviceInfoRes>> reqCallBack) {
        post(mContext, updateDeviceUrl, req, reqCallBack, DeviceInfoRes.class);
    }
    public void getOneDeviceInfo(getOneDeviceInfoReq req, final ReqCallBack<BasicResponseBody<DeviceInfoRes>> reqCallBack) {
        post(mContext, getOneDeviceInfoUrl, req, reqCallBack, DeviceInfoRes.class);
    }
    public void getEnvironment(getEnvironmentReq req, final ReqCallBack<BasicResponseBody<getEnvironmentRes>> reqCallBack) {
        post(mContext, get_environmentUrl, req, reqCallBack, getEnvironmentRes.class);
    }

}
