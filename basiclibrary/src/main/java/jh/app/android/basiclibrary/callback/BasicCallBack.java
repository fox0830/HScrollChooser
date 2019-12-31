package jh.app.android.basiclibrary.callback;


/**
 * 基础回调函数
 * Created by baorui on 2017/4/17.
 */

public interface BasicCallBack<T> {
    /**
     * 成功
     */
    void onSuccess(T result);

    /**
     * 异常
     */
    void onError(String errorMsg);
}
