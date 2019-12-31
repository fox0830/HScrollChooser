package com.feibi.trade.UI.View;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.feibi.trade.R;

public class LoadingDialog {
    private Context context;
    private Dialog dialog;
    private Display display;
    private TextView tv_wait_tip;
    public LoadingDialog(Context context, View view) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        builder(view);
    }

    public LoadingDialog builder(View view) {
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);
        tv_wait_tip = view.findViewById(R.id.tv_wait_tip);
        view.findViewById(R.id.rootLayout).setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.7), ViewGroup.LayoutParams.WRAP_CONTENT));
        return this;
    }

    /**
     * 设置点击外部是否消失
     *
     * @param cancel
     * @return
     */
    public LoadingDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }
    /**
     * 设置点击外部是否消失
     *
     * @return
     */
    public LoadingDialog setWaitText(String text) {
        tv_wait_tip.setText(text);
        return this;
    }

    public void show() {
        dialog.show();
    }

    public boolean isShowing() {
        if (dialog != null) {
            if (dialog.isShowing())
                return true;
            else
                return false;
        }
        return false;
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}


