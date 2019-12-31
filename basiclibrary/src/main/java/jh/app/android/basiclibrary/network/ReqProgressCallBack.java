package jh.app.android.basiclibrary.network;

/**
 * 带进度的网络请求回调函数
 * Created by br on 2017/2/24 0024.
 */

public interface ReqProgressCallBack<T> extends ReqCallBack<T> {
    /**
     * 响应进度更新
     */
    void onProgress(long total, long current);
}