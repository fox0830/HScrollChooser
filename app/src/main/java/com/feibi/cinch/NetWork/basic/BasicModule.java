package com.feibi.cinch.NetWork.basic;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.feibi.cinch.NetWork.request.FileUploadReq;
import com.feibi.cinch.utils.GsonUtil;
import com.feibi.cinch.utils.PreferencesUtil;
import com.google.gson.Gson;

import java.util.HashMap;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;
import jh.app.android.basiclibrary.network.RequestManager;
import jh.app.android.basiclibrary.utils.ActivityUtils;
import jh.app.android.basiclibrary.utils.ObjUtils;

public class BasicModule {
    public static final String DO_MAIN = "http://60.250.137.141/CinchAPP/";   //測試


    public static String getImageUrl(String imgPathInNet) {
        return DO_MAIN + imgPathInNet;
    }

    public <T> void get(Context context, String url, BasicReq req, final ReqCallBack<BasicResponseBody<T>> reqCallBack, final Class<T> entityClass) {
        RequestManager requestManager = RequestManager.getInstance(context);
        HashMap<String, Object> paramsMap = ObjUtils.jsonToMap(new Gson().toJson(req));
        requestManager.requestAsyn(DO_MAIN + url, RequestManager.TYPE_GET, paramsMap, getHeaderMap(context), getReqCallBack(reqCallBack, entityClass));
    }

    public <T> void post(Context context, String url, BasicReq req, final ReqCallBack<BasicResponseBody<T>> reqCallBack, final Class<T> entityClass) {
        RequestManager requestManager = RequestManager.getInstance(context);
        HashMap<String, Object> paramsMap = ObjUtils.jsonToMap(new Gson().toJson(req));
        requestManager.requestAsyn(DO_MAIN + url, RequestManager.TYPE_POST_FORM, paramsMap, getHeaderMap(context), getReqCallBack(reqCallBack, entityClass));
    }

    public <T> void fileUpload(Context context, String url, FileUploadReq req, final ReqCallBack<BasicResponseBody<T>> reqCallBack, final Class<T> entityClass) {
        RequestManager requestManager = RequestManager.getInstance(context);

        HashMap<String, Object> paramsMap = new HashMap<>();
        if (req.getFile() == null) {
            if (reqCallBack != null) {
                BasicResponseBody<T> response = new BasicResponseBody<T>();
                response.setCode("666");
                response.setMsg("no files");
                reqCallBack.onReqFailed(response);
            }
            return;
        }
        paramsMap.put("file", req.getFile());
        requestManager.upLoadFile(DO_MAIN + url, paramsMap, getHeaderMap(context), getReqCallBack(reqCallBack, entityClass));
    }


    private HashMap<String, Object> getHeaderMap(Context context) {
        HashMap<String, Object> headerMap = new HashMap<>();
        return headerMap;
    }


    private <T> ReqCallBack<String> getReqCallBack(final ReqCallBack<BasicResponseBody<T>> reqCallBack, final Class<T> entityClass) {
        return new ReqCallBack<String>() {
            @Override
            public void onReqSuccess(String result) {
                BasicResponseBody<T> response = null;
                try {
                    response = GsonUtil.fromJson(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (reqCallBack == null) {
                    return;
                }
                if (response == null) {
                    response = new BasicResponseBody<T>();
                    response.setCode("666");
                    response.setMsg("數據異常");
                    reqCallBack.onReqFailed(response);
                } else {
                    if ("ok".equals(response.getCode())) {
                        try {
                            response = GsonUtil.fromJson(result,entityClass);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        reqCallBack.onReqSuccess(response);
                    } else {
                        reqCallBack.onReqFailed(response);
                    }
                }
            }

            @Override
            public void onReqFailed(BasicResponseBody body) {
                reqCallBack.onReqFailed(body);
            }

        };
    }
}
