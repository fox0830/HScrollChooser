package com.feibi.cinch.UI.Basic;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.feibi.cinch.R;
import com.feibi.cinch.UI.View.LoadingDialog;

public abstract class BasicFragment extends Fragment implements View.OnClickListener {
    public Context context;
    static LoadingDialog loadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView(inflater, container, savedInstanceState);
//        View systemBar = view.findViewById(R.id.system_bar);
//        if (systemBar != null) {
//            ConstraintLayout.LayoutParams linearParams = (ConstraintLayout.LayoutParams) systemBar.getLayoutParams(); //取控件textView当前的布局参数
//            linearParams.height = SyatemUtils.getStatusBarHeight(getContext());
//            systemBar.setLayoutParams(linearParams);
//        }
        initLoadingDialog();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    public abstract void loadData();

    protected void initLoadingDialog() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        loadingDialog = new LoadingDialog(context, view);
        loadingDialog.setCancelable(false);
    }
    protected static void showLoading(){
        showLoading("Loading...");
    }

    protected static void showLoading(String text){
        if(loadingDialog!=null){
            loadingDialog.setWaitText(text);
            loadingDialog.show();
        }
    }

    protected static void dismissLoading(){
        if(loadingDialog!=null){
            loadingDialog.dismiss();
        }
    }
    /**
     * 显示toast
     *
     * @param msg      内容
     * @param duration 时间
     */
    public void showToast(String msg, int duration) {
        if (getContext() != null) {
            Toast.makeText(getContext(), msg, duration).show();
        }
    }

    public void showToast(String msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

}

