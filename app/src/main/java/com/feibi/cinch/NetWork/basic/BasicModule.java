package com.feibi.cinch.NetWork.basic;

import android.content.Context;
import android.text.TextUtils;

import com.feibi.cinch.NetWork.request.FileUploadReq;
import com.feibi.cinch.utils.GsonUtil;
import com.feibi.cinch.utils.PreferencesUtil;
import com.google.gson.Gson;

import java.util.HashMap;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;
import jh.app.android.basiclibrary.network.RequestManager;
import jh.app.android.basiclibrary.utils.ObjUtils;

public class BasicModule {
    public static final String DO_MAIN = "https://dev.adventrip.net/";   //測試
//    public static final String DO_MAIN = "https://view.adventrip.net/";  //正式

    public static String getImageUrl(String imgPathInNet) {
        return DO_MAIN + imgPathInNet;
    }

    public <T> void get(Context context, String url, BasicReq req, final ReqCallBack<T> reqCallBack, final Class<T> entityClass) {
        RequestManager requestManager = RequestManager.getInstance(context);
        HashMap<String, Object> paramsMap = ObjUtils.jsonToMap(new Gson().toJson(req));
        requestManager.requestAsyn(DO_MAIN + url, RequestManager.TYPE_GET, paramsMap, getHeaderMap(context), getReqCallBack(reqCallBack, entityClass));
    }

    public <T> void delete(Context context, String url, BasicReq req, final ReqCallBack<T> reqCallBack, final Class<T> entityClass) {
        RequestManager requestManager = RequestManager.getInstance(context);
        HashMap<String, Object> paramsMap = ObjUtils.jsonToMap(new Gson().toJson(req));
        requestManager.requestAsyn(DO_MAIN + url, RequestManager.TYPE_DELETE, paramsMap, getHeaderMap(context), getReqCallBack(reqCallBack, entityClass));
    }

    public <T> void post(Context context, String url, BasicReq req, final ReqCallBack<T> reqCallBack, final Class<T> entityClass) {
        RequestManager requestManager = RequestManager.getInstance(context);
        HashMap<String, Object> paramsMap = ObjUtils.jsonToMap(new Gson().toJson(req));
        requestManager.requestAsyn(DO_MAIN + url, RequestManager.TYPE_POST_FORM, paramsMap, getHeaderMap(context), getReqCallBack(reqCallBack, entityClass));
    }
    public <T> void postJson(Context context, String url, BasicReq req, final ReqCallBack<T> reqCallBack, final Class<T> entityClass) {
        RequestManager requestManager = RequestManager.getInstance(context);
        HashMap<String, Object> paramsMap = ObjUtils.jsonToMap(new Gson().toJson(req));
        requestManager.requestAsyn(DO_MAIN + url, RequestManager.TYPE_POST_JSON, paramsMap, getHeaderMap(context), getReqCallBack(reqCallBack, entityClass));
    }
    public <T> void postJsonObj(Context context, String url, BasicReq req, final ReqCallBack<T> reqCallBack, final Class<T> entityClass) {
        RequestManager requestManager = RequestManager.getInstance(context);
        HashMap<String, Object> paramsMap = ObjUtils.jsonToMap(new Gson().toJson(req));
        requestManager.requestAsyn(DO_MAIN + url, RequestManager.TYPE_POST_JSON_OBJ, paramsMap, getHeaderMap(context), getReqCallBack(reqCallBack, entityClass));
    }

    public <T> void fileUpload(Context context, String url, FileUploadReq req, final ReqCallBack<T> reqCallBack, final Class<T> entityClass) {
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
//        paramsMap.put("type", req.getType());
        requestManager.upLoadFile(DO_MAIN + url, paramsMap, getHeaderMap(context), getReqCallBack(reqCallBack, entityClass));
    }


    private HashMap<String, Object> getHeaderMap(Context context) {
        HashMap<String, Object> headerMap = new HashMap<>();
//        headerMap.put("Content-Type", "application/json");// Android、iOS
//        headerMap.put("platform-v", "XIAOMI MIUI9.0");// 標示平台系統版本信息
//        headerMap.put("version-name", "1.0.0");// 標示APP版本號
//        String token = PreferencesUtil.getUserToken(context);
//        if (!TextUtils.isEmpty(token)) {
//            headerMap.put("token", token);
//        }
        return headerMap;
    }


    private <T> ReqCallBack<String> getReqCallBack(final ReqCallBack<T> reqCallBack, final Class<T> entityClass) {
        return new ReqCallBack<String>() {
            @Override
            public void onReqSuccess(String result) {
                Object response = null;
                try {
                    response = GsonUtil.fromJson(result, entityClass);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (reqCallBack == null) {
                    return;
                }
                if (response == null) {
                    response = new Object();
                    reqCallBack.onReqFailed(response);
                } else {
                    reqCallBack.onReqSuccess((T) response);
                }
            }

            @Override
            public void onReqFailed(Object result) {
                reqCallBack.onReqFailed(result);
            }


//            @Override
//            public void onReqFailed(String errorMsg) {
//                if (reqCallBack != null) {
//                    BasicResponseBody<T> response = new BasicResponseBody<T>();
//                    response.setCode("400");
//                    response.setMsg(errorMsg);
//                    reqCallBack.onReqFailed(response);
//                }
//            }
        };
    }
}
