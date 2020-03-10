package com.feibi.cinch.UI.View;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.feibi.cinch.R;

public class CustomDialog {
    private Context context;
    private Dialog dialog;
    private Display display;

    public CustomDialog(Context context, View view) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        builder(view);
    }

    public CustomDialog builder(View view) {
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);
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
    public CustomDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
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


