package jh.app.android.basiclibrary.network;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import jh.app.android.basiclibrary.Constants;
import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.utils.MD5;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/**
 * 网络请求类
 * Created by br on 2017/2/24 0024.
 */

public class RequestManager {

    private final String TAG = RequestManager.class.getSimpleName();

    private final long mConnectTimeout = 10;// 秒
    private final long mReadTimeout = 10;// 秒
    private final long mWriteTimeout = 10;// 秒
    private final long mFileUploadWriteTimeout = 600;// 秒

    private final MediaType MEDIA_TYPE_FORM = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");//mdiatype 这个需要和服务端保持一致
    private final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");//mdiatype 这个需要和服务端保持一致
    private final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");//mdiatype 这个需要和服务端保持一致

    private final MediaType MEDIA_OBJECT_STREAM = MediaType.parse("multipart/form-data");

    public static final int TYPE_GET = 0;//get请求
    public static final int TYPE_POST_JSON = 1;//post请求参数为json
    public static final int TYPE_POST_FORM = 2;//post请求参数为表单
    public static final int TYPE_POST_JSON_OBJ = 3;//post请求参数为json

    private static volatile RequestManager mInstance;//单例引用
    private OkHttpClient mOkHttpClient;//okHttpClient 实例
    private Handler okHttpHandler;//全局处理子线程和M主线程通信

    private String  uploadFailureMsg = "上傳失敗";
    private String  FailureMsg = "訪問失敗，請檢查您的網絡設置";
    private String  ResponseMsg = "數據錯誤";
    /**
     * 初始化RequestManager
     */
    private RequestManager(Context context) {
        //初始化OkHttpClient
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(mConnectTimeout, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(mReadTimeout, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(mWriteTimeout, TimeUnit.SECONDS)//设置写入超时时间
                .build();
        //初始化Handler
        okHttpHandler = new Handler(context.getMainLooper());
    }

    private void setReturnMsg(String language) {
        if ("en_US".equals(language)) {
            FailureMsg = "Upload failure";
            ResponseMsg = "Server Error";
        } else {
            uploadFailureMsg = "上傳失敗";
            FailureMsg = "訪問失敗";
            ResponseMsg = "數據錯誤";
        }
    }

    /**
     * 获取单例引用
     *
     * @return RequestManager Instance
     */
    public static RequestManager getInstance(Context context) {
        RequestManager inst = mInstance;
        if (inst == null) {
            synchronized (RequestManager.class) {
                inst = mInstance;
                if (inst == null) {
                    inst = new RequestManager(context.getApplicationContext());
                    mInstance = inst;
                }
            }
        }
        return inst;
    }

    /**
     * okHttp同步请求统一入口
     *
     * @param actionUrl   接口地址
     * @param requestType 请求类型
     * @param paramsMap   请求参数
     * @return 响应体字符串，或null
     */
    public String requestSyn(String actionUrl, int requestType, HashMap<String, String> paramsMap) {
        switch (requestType) {
            case TYPE_GET:
                return requestGetBySyn(actionUrl, paramsMap);
            case TYPE_POST_JSON:
                return requestPostBySyn(actionUrl, paramsMap);
            case TYPE_POST_FORM:
                return requestPostBySynWithForm(actionUrl, paramsMap);
            default:
                return null;
        }
    }

    /**
     * okHttp get同步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     */
    private String requestGetBySyn(String actionUrl, HashMap<String, String> paramsMap) {
        StringBuilder tempParams = new StringBuilder();
        try {
            //处理参数
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                //对参数进行URLEncoder
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            //补全请求地址
            String requestUrl = String.format("%s?%s", actionUrl, tempParams.toString());
            //创建一个请求
            Request request = addHeaders().url(requestUrl).build();
            //创建一个Call
            final Call call = mOkHttpClient.newCall(request);
            //执行请求
            final Response response = call.execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
//                Log.e(TAG, "response ----->" + result);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
//            Log.e(TAG, e.toString());
        }
        return null;
    }

    /**
     * okHttp post同步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     */
    private String requestPostBySyn(String actionUrl, HashMap<String, String> paramsMap) {
        try {
            //处理参数
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            //补全请求地址
            String requestUrl = String.format("%s", actionUrl);
            //生成参数
            String params = tempParams.toString();
            //创建一个请求实体对象 RequestBody
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
            //创建一个请求
            final Request request = addHeaders().url(requestUrl).post(body).build();
            //创建一个Call
            final Call call = mOkHttpClient.newCall(request);
            //执行请求
            Response response = call.execute();
            //请求执行成功
            if (response.isSuccessful()) {
                //获取返回数据 可以是String，bytes ,byteStream
                String result = response.body().string();
//                Log.e(TAG, "response ----->" + result);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
//            Log.e(TAG, e.toString());
        }
        return null;
    }

    /**
     * okHttp post同步请求表单提交
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     */
    private String requestPostBySynWithForm(String actionUrl, HashMap<String, String> paramsMap) {
        try {
            //创建一个FormBody.Builder
            FormBody.Builder builder = new FormBody.Builder();
            for (String key : paramsMap.keySet()) {
                //追加表单信息
                builder.add(key, paramsMap.get(key));
            }
            //生成表单实体对象
            RequestBody formBody = builder.build();
            //补全请求地址
            String requestUrl = String.format("%s", actionUrl);
            //创建一个请求
            final Request request = addHeaders().url(requestUrl).post(formBody).build();
            //创建一个Call
            final Call call = mOkHttpClient.newCall(request);
            //执行请求
            Response response = call.execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
//                Log.e(TAG, "response ----->" + result);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
//            Log.e(TAG, e.toString());
        }
        return null;
    }

    /**
     * okHttp异步请求统一入口
     *
     * @param actionUrl   接口地址
     * @param requestType 请求类型
     * @param paramsMap   请求参数
     * @param callBack    请求返回数据回调
     * @param <T>         数据泛型
     **/
    public <T> Call requestAsyn(String actionUrl, int requestType, HashMap<String, Object> paramsMap, HashMap<String, Object> headerMap, ReqCallBack<T> callBack) {
        Call call = null;
        switch (requestType) {
            case TYPE_GET:
                call = requestGetByAsyn(actionUrl, paramsMap, headerMap, callBack);
                break;
            case TYPE_POST_JSON:
                call = requestPostByAsyn(actionUrl, paramsMap, headerMap, callBack);
                break;
            case TYPE_POST_FORM:
                call = requestPostByAsynWithForm(actionUrl, paramsMap, headerMap, callBack);
                break;
            case TYPE_POST_JSON_OBJ:
                call = requestPostByAsynWithJson(actionUrl, paramsMap, headerMap, callBack);
                break;
        }
        return call;
    }

    /**
     * okHttp get异步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @param <T>       数据泛型
     */
    private <T> Call requestGetByAsyn(String actionUrl, HashMap<String, Object> paramsMap, HashMap<String, Object> headerMap, final ReqCallBack<T> callBack) {
        StringBuilder tempParams = new StringBuilder();
        try {
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, paramsMap.get(key).toString()));
                pos++;
            }
            String requestUrl = String.format("%s?%s", actionUrl, tempParams.toString());
            Request.Builder builder = addHeaders().url(requestUrl);
            if (headerMap != null) {
                for (String hKey : headerMap.keySet()) {
                    builder.addHeader(hKey, headerMap.get(hKey).toString());
                }
            }
            final Request request = builder.build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    failedCallBack(BasicBodyParse(FailureMsg), callBack);
//                    Log.e(TAG, e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
//                        Log.e(TAG, "response ----->" + string);
                        successCallBack((T) string, callBack);
                    } else {
                        failedCallBack(BasicBodyParse(ResponseMsg), callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            e.printStackTrace();
            failedCallBack(BasicBodyParse(e.toString()), callBack);
//            Log.e(TAG, e.toString());
        }
        return null;
    }
    public BasicResponseBody BasicBodyParse(String msg){
        BasicResponseBody body = new BasicResponseBody();
        if(TextUtils.isEmpty(msg)){
            msg = "未知錯誤";
        }
        body.setCode("666");
        body.setMsg(msg);
        return body; 
    }

    /**
     * okHttp post异步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @param <T>       数据泛型
     * @return
     */
    private <T> Call requestPostByAsyn(String actionUrl, HashMap<String, Object> paramsMap, HashMap<String, Object> headerMap, final ReqCallBack<T> callBack) {
        try {
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key).toString(), "utf-8")));
                pos++;
            }
            String params = tempParams.toString();
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
            String requestUrl = String.format("%s", actionUrl);

            //创建Request
            Request.Builder builder = addHeaders().url(requestUrl).post(body);
            if (headerMap != null) {
                for (String hKey : headerMap.keySet()) {
                    builder.addHeader(hKey, headerMap.get(hKey).toString());
                }
            }
            final Request request = builder.build();


            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    failedCallBack(BasicBodyParse(FailureMsg), callBack);
//                    Log.e(TAG, e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
//                        Log.e(TAG, "response ----->" + string);
                        successCallBack((T) string, callBack);
                    } else {
                        failedCallBack(BasicBodyParse(ResponseMsg), callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            e.printStackTrace();
//            Log.e(TAG, e.toString());
        }
        return null;
    }

    /**
     * okHttp post异步请求表单提交
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @param <T>       数据泛型
     * @return
     */
    private <T> Call requestPostByAsynWithJson(String actionUrl, HashMap<String, Object> paramsMap, HashMap<String, Object> headerMap, final ReqCallBack<T> callBack) {
        try {
            RequestBody formBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(paramsMap));
            String requestUrl = String.format("%s", actionUrl);

            Request.Builder builder1 = addHeaders().url(requestUrl).post(formBody);
            if (headerMap != null) {
                for (String hKey : headerMap.keySet()) {
                    builder1.addHeader(hKey, headerMap.get(hKey).toString());
                }
            }
            final Request request = builder1.build();

            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    failedCallBack(BasicBodyParse(FailureMsg), callBack);
//                    Log.e(TAG, e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
//                        Log.e(TAG, "response ----->" + string);
                        successCallBack((T) string, callBack);
                    } else {
                        failedCallBack(BasicBodyParse(ResponseMsg), callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            e.printStackTrace();
            failedCallBack(BasicBodyParse(e.toString()), callBack);
//            Log.e(TAG, e.toString());
        }
        return null;
    }

    /**
     * okHttp post异步请求表单提交
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @param <T>       数据泛型
     * @return
     */
    private <T> Call requestPostByAsynWithForm(String actionUrl, HashMap<String, Object> paramsMap, HashMap<String, Object> headerMap, final ReqCallBack<T> callBack) {
        try {
            FormBody.Builder builder = new FormBody.Builder();
            for (String key : paramsMap.keySet()) {
                builder.add(key, paramsMap.get(key).toString());
            }
            RequestBody formBody = builder.build();
            String requestUrl = String.format("%s", actionUrl);

            Request.Builder builder1 = addHeaders().url(requestUrl).post(formBody);
            if (headerMap != null) {
                for (String hKey : headerMap.keySet()) {
                    builder1.addHeader(hKey, headerMap.get(hKey).toString());
                }
            }
            final Request request = builder1.build();

            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    failedCallBack(BasicBodyParse(FailureMsg), callBack);
//                    Log.e(TAG, e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
//                        Log.e(TAG, "response ----->" + string);
                        successCallBack((T) string, callBack);
                    } else {
                        failedCallBack(BasicBodyParse(ResponseMsg), callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            e.printStackTrace();
            failedCallBack(BasicBodyParse(e.toString()), callBack);
//            Log.e(TAG, e.toString());
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param actionUrl 接口地址
     * @param filePath  本地文件地址
     */
    public <T> void upLoadFile(String actionUrl, String filePath, HashMap<String, Object> headerMap, final ReqCallBack<T> callBack) {
        try {
            //补全请求地址
            String requestUrl = String.format("%s", actionUrl);
            //创建File
            File file = new File(filePath);
            //创建RequestBody
            RequestBody body = RequestBody.create(MEDIA_OBJECT_STREAM, file);
            //创建Request
            Request.Builder builder = new Request.Builder().url(requestUrl).post(body);
            if (headerMap != null) {
                for (String hKey : headerMap.keySet()) {
                    builder.addHeader(hKey, headerMap.get(hKey).toString());
                }
            }
            final Request request = builder.build();

            final Call call = mOkHttpClient.newBuilder().writeTimeout(mFileUploadWriteTimeout, TimeUnit.SECONDS).build().newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
//                Log.e(TAG, e.toString());
                    failedCallBack(BasicBodyParse(uploadFailureMsg), callBack);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
//                    Log.e(TAG, "response ----->" + string);
                        successCallBack((T) string, callBack);
                    } else {
                        failedCallBack(BasicBodyParse(uploadFailureMsg), callBack);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     * 1111111
     *
     * @param actionUrl 接口地址
     * @param paramsMap 参数
     * @param callBack  回调
     * @param <T>
     */
    public <T> void upLoadFile(String actionUrl, HashMap<String, Object> paramsMap, HashMap<String, Object> headerMap, final ReqCallBack<T> callBack) {
        try {
            //补全请求地址
            String requestUrl = String.format("%s", actionUrl);
            requestUrl = actionUrl;
            MultipartBody.Builder builder = new MultipartBody.Builder();

            //设置类型
            builder.setType(MultipartBody.FORM);
            //追加参数
            for (String key : paramsMap.keySet()) {
                Object object = paramsMap.get(key);
                if (!(object instanceof File)) {
                    builder.addFormDataPart(key, object.toString());
                } else {
                    File file = (File) object;
//                    builder.addFormDataPart(key, file.getName(), RequestBody.create(null, file));
                    String path_key = "";
                    if (key.contains(Constants.SEPARATOR)) {
                        path_key = key.split(Constants.SEPARATOR)[0];
                    } else {
                        path_key = key;
                    }
                    builder.addFormDataPart(path_key, URLEncoder.encode(file.getName()), RequestBody.create(MediaType.parse("image/jpeg"), file));
                }
            }
            //创建RequestBody
            RequestBody body = builder.build();
            //创建Request
            Request.Builder builder1 = new Request.Builder().url(requestUrl).post(body);
            if (headerMap != null) {
                for (String hKey : headerMap.keySet()) {
                    builder1.addHeader(hKey, headerMap.get(hKey).toString());
                }
            }
            Request request = builder1.build();

            //单独设置参数 比如读取超时时间
            final Call call = mOkHttpClient.newBuilder().writeTimeout(mFileUploadWriteTimeout, TimeUnit.SECONDS).build().newCall(request);
//            final Call call = mOkHttpClient.newBuilder().connectTimeout(50, TimeUnit.SECONDS).writeTimeout(50, TimeUnit.SECONDS).readTimeout(50, TimeUnit.SECONDS).build().newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
//                    Log.e(TAG, e.toString());
                    failedCallBack(BasicBodyParse(uploadFailureMsg), callBack);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
//                        Log.e(TAG, "response ----->" + string);
                        successCallBack((T) string, callBack);
                    } else {
                        failedCallBack(BasicBodyParse(uploadFailureMsg), callBack);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            failedCallBack(BasicBodyParse(e.toString()), callBack);
//            Log.e(TAG, e.toString());
        }
    }

    /**
     * 上传文件带进度
     *
     * @param actionUrl 接口地址
     * @param paramsMap 参数
     * @param callBack  回调
     * @param <T>
     */
    public <T> void upLoadFile(String actionUrl, HashMap<String, Object> paramsMap, HashMap<String, Object> headerMap, final ReqProgressCallBack<T> callBack) {
        try {
            //补全请求地址
            String requestUrl = String.format("%s", actionUrl);
            MultipartBody.Builder builder = new MultipartBody.Builder();
            //设置类型
            builder.setType(MultipartBody.FORM);
            //追加参数
            for (String key : paramsMap.keySet()) {
                Object object = paramsMap.get(key);
                if (!(object instanceof File)) {
                    builder.addFormDataPart(key, object.toString());
                } else {
                    File file = (File) object;
//                    builder.addFormDataPart(key, file.getName(), createProgressRequestBody(MEDIA_OBJECT_STREAM, file, callBack));
                    String path_key = "";
                    if (key.contains(Constants.SEPARATOR)) {
                        path_key = key.split(Constants.SEPARATOR)[0];
                    } else {
                        path_key = key;
                    }
                    builder.addFormDataPart(path_key, URLEncoder.encode(file.getName()), createProgressRequestBody(MEDIA_OBJECT_STREAM, file, callBack));
                }
            }
            //创建RequestBody
            RequestBody body = builder.build();
            //创建Request
            Request.Builder builder1 = new Request.Builder().url(requestUrl).post(body);
            if (headerMap != null) {
                for (String hKey : headerMap.keySet()) {
                    builder1.addHeader(hKey, headerMap.get(hKey).toString());
                }
            }
            final Request request = builder1.build();

            final Call call = mOkHttpClient.newBuilder().writeTimeout(mFileUploadWriteTimeout, TimeUnit.SECONDS).build().newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
//                    Log.e(TAG, e.toString());
                    failedCallBack(BasicBodyParse(uploadFailureMsg), callBack);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
//                        Log.e(TAG, "response ----->" + string);
                        successCallBack((T) string, callBack);
                    } else {
                        failedCallBack(BasicBodyParse(uploadFailureMsg), callBack);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
//            Log.e(TAG, e.toString());
        }
    }

    /**
     * 创建带进度的RequestBody
     *
     * @param contentType MediaType
     * @param file        准备上传的文件
     * @param callBack    回调
     * @param <T>
     * @return
     */
    public <T> RequestBody createProgressRequestBody(final MediaType contentType, final File file, final ReqProgressCallBack<T> callBack) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return contentType;
            }

            @Override
            public long contentLength() {
                return file.length();
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                Source source;
                try {
                    source = Okio.source(file);
                    Buffer buf = new Buffer();
                    long remaining = contentLength();
                    long current = 0;
                    for (long readCount; (readCount = source.read(buf, 2048)) != -1; ) {
                        sink.write(buf, readCount);
                        current += readCount;
//                        Log.e(TAG, "current------>" + current);
                        progressCallBack(remaining, current, callBack);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * 下载文件
     *
     * @param fileUrl     文件url
     * @param destFileDir 存储目标目录
     */
    public <T> void downLoadFile(String fileUrl, HashMap<String, Object> headerMap, final String destFileDir, final ReqCallBack<T> callBack) {
        try {
            final String fileName = MD5.encode(fileUrl);
            final File file = new File(destFileDir, fileName);
            if (file.exists()) {
                successCallBack((T) file, callBack);
                return;
            }
            Request.Builder builder = new Request.Builder().url(fileUrl);
            if (headerMap != null) {
                for (String hKey : headerMap.keySet()) {
                    builder.addHeader(hKey, headerMap.get(hKey).toString());
                }
            }
            final Request request = builder.build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
//                Log.e(TAG, e.toString());
                    failedCallBack(BasicBodyParse("下载失败"), callBack);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    InputStream is = null;
                    byte[] buf = new byte[2048];
                    int len = 0;
                    FileOutputStream fos = null;
                    try {
                        long total = response.body().contentLength();
//                    Log.e(TAG, "total------>" + total);
                        long current = 0;
                        is = response.body().byteStream();
                        fos = new FileOutputStream(file);
                        while ((len = is.read(buf)) != -1) {
                            current += len;
                            fos.write(buf, 0, len);
//                        Log.e(TAG, "current------>" + current);
                        }
                        fos.flush();
                        successCallBack((T) file, callBack);
                    } catch (IOException e) {
//                    Log.e(TAG, e.toString());
                        failedCallBack(BasicBodyParse("下载失败"), callBack);
                    } finally {
                        try {
                            if (is != null) {
                                is.close();
                            }
                            if (fos != null) {
                                fos.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
//                        Log.e(TAG, e.toString());
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件带进度
     *
     * @param fileUrl     文件url
     * @param destFileDir 存储目标目录
     */
    public <T> void downLoadFile(String fileUrl, HashMap<String, Object> headerMap, final String destFileDir, final ReqProgressCallBack<T> callBack) {
        try {
            final String fileName = MD5.encode(fileUrl);
            final File file = new File(destFileDir, fileName);
            if (file.exists()) {
                successCallBack((T) file, callBack);
                return;
            }
            Request.Builder builder = new Request.Builder().url(fileUrl);
            if (headerMap != null) {
                for (String hKey : headerMap.keySet()) {
                    builder.addHeader(hKey, headerMap.get(hKey).toString());
                }
            }
            final Request request = builder.build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
//                Log.e(TAG, e.toString());
                    failedCallBack(BasicBodyParse("下载失败"), callBack);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    InputStream is = null;
                    byte[] buf = new byte[2048];
                    int len = 0;
                    FileOutputStream fos = null;
                    try {
                        long total = response.body().contentLength();
//                    Log.e(TAG, "total------>" + total);
                        long current = 0;
                        is = response.body().byteStream();
                        fos = new FileOutputStream(file);
                        while ((len = is.read(buf)) != -1) {
                            current += len;
                            fos.write(buf, 0, len);
//                        Log.e(TAG, "current------>" + current);
                            progressCallBack(total, current, callBack);
                        }
                        fos.flush();
                        successCallBack((T) file, callBack);
                    } catch (IOException e) {
//                    Log.e(TAG, e.toString());
                        failedCallBack(BasicBodyParse("下载失败"), callBack);
                    } finally {
                        try {
                            if (is != null) {
                                is.close();
                            }
                            if (fos != null) {
                                fos.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
//                        Log.e(TAG, e.toString());
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 统一处理进度信息
     *
     * @param total    总计大小
     * @param current  当前进度
     * @param callBack
     * @param <T>
     */
    private <T> void progressCallBack(final long total, final long current, final ReqProgressCallBack<T> callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onProgress(total, current);
                }
            }
        });
    }

    /**
     * 统一为请求添加头信息
     *
     * @return
     */
    private Request.Builder addHeaders() {
        Request.Builder builder = new Request.Builder()
                .addHeader("Connection", "keep-alive")
                .addHeader("platform", "2")
                .addHeader("phoneModel", Build.MODEL)
                .addHeader("systemVersion", Build.VERSION.RELEASE)
                .addHeader("appVersion", "3.2.0");
        return builder;
    }

    /**
     * 统一同意处理成功信息
     *
     * @param result
     * @param callBack
     * @param <T>
     */
    private <T> void successCallBack(final T result, final ReqCallBack<T> callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onReqSuccess(result);
                }
            }
        });
    }

    /**
     * 统一处理失败信息
     *
     * @param body
     * @param callBack
     * @param <T>
     */
    private <T> void failedCallBack(final BasicResponseBody body, final ReqCallBack<T> callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onReqFailed(body);
                }
            }
        });
    }


    /**
     * 根据Tag取消请求
     */
    public void cancelTag(Object tag) {
        if (tag == null) return;
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    /**
     * 根据Tag取消请求
     */
    public void cancelTag(OkHttpClient client, Object tag) {
        if (client == null || tag == null) return;
        for (Call call : client.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : client.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    /**
     * 取消所有请求请求
     */
    public void cancelAll() {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            call.cancel();
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            call.cancel();
        }
    }

    /**
     * 取消所有请求请求
     */
    public void cancelAll(OkHttpClient client) {
        if (client == null) return;
        for (Call call : client.dispatcher().queuedCalls()) {
            call.cancel();
        }
        for (Call call : client.dispatcher().runningCalls()) {
            call.cancel();
        }
    }

}
