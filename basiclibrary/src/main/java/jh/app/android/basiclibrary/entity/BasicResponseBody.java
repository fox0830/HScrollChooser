package jh.app.android.basiclibrary.entity;

/**
 * 网络请求响应体基类
 * Created by baorui on 2018/3/9.
 */

public class BasicResponseBody<T> extends BasicEntity {
    private String code;
    private String msg;
    //    private T[] data;
    private T data;

    public BasicResponseBody(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BasicResponseBody() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
