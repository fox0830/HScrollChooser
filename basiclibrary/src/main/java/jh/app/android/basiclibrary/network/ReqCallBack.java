package jh.app.android.basiclibrary.network;

import jh.app.android.basiclibrary.entity.BasicResponseBody;

/**
 * 网络请求回调函数
 * Created by br on 2017/2/24 0024.
 */

public interface ReqCallBack<T> {
    /**
     * 响应成功
     */
    void onReqSuccess(T result);

    /**
     * 响应失败
     */
    void onReqFailed(BasicResponseBody result);
}
