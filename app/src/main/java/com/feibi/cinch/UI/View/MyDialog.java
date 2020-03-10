package com.feibi.cinch.UI.View;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feibi.cinch.R;

public class MyDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private Display display;
    private boolean showIcon = false;
    private boolean showTitle = false;
    private boolean showMsg = false;

    public MyDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        builder();
    }

    public MyDialog builder() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_alert_dialog, null);
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.6), ViewGroup.LayoutParams.WRAP_CONTENT));
        setGone();
//        ll_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//        ll_done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
        return this;
    }

    /**
     * 恢复初始
     *
     * @return
     */
    public MyDialog setGone() {
        if (lLayout_bg != null) {
//            iv_icon.setVisibility(View.GONE);
//            txt_title.setVisibility(View.GONE);
//            txt_msg.setVisibility(View.GONE);
        }
        showIcon = false;
        showTitle = false;
        showMsg = false;
        return this;
    }

    /**
     * 设置icon
     *
     * @return
     */
    public MyDialog setIcon(Drawable drawable) {
        showIcon = true;
        if (drawable!=null) {
//            iv_icon.setImageDrawable(drawable);
        }
        return this;
    }
    /**
     * 设置title
     *
     * @param title
     * @return
     */
    public MyDialog setTitle(String title) {
        showTitle = true;
        if (TextUtils.isEmpty(title)) {
//            txt_title.setText("提示");
        } else {
//            txt_title.setText(title);
        }
        return this;
    }

    /**
     * 设置Message
     *
     * @param msg
     * @return
     */
    public MyDialog setMsg(String msg) {
        showMsg = true;
        if (TextUtils.isEmpty(msg)) {
//            txt_msg.setText("");
        } else {
//            txt_msg.setText(msg);
        }
        return this;
    }

    /**
     * 设置点击外部是否消失
     * @param cancel
     * @return
     */
    public MyDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     *
     * @param listener
     * @return
     */
    public MyDialog setDoneButton(final View.OnClickListener listener) {
//        ll_done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null)
//                    listener.onClick(v);
//                dismiss();
//            }
//        });
        return this;
    }

    /**
     *
     * @param listener
     * @return
     */

    public MyDialog setCloseButton(final View.OnClickListener listener) {
//        ll_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null)
//                    listener.onClick(v);
//                dismiss();
//            }
//        });
        return this;
    }

    public static class AlertButton {
        String text;
        int color;
        final View.OnClickListener listener;

        public AlertButton(String text, int color, View.OnClickListener listener) {
            this.text = text;
            this.color = color;
            this.listener = listener;
        }

        public AlertButton(String text, View.OnClickListener listener) {
            this.text = text;
            this.color = -1;
            this.listener = listener;
        }
    }

    /**
     * 设置显示
     */
    private void setLayout() {
//        if (!showTitle && !showMsg) {
//            txt_title.setText("");
//            txt_title.setVisibility(View.VISIBLE);
//        }
//
//        if (showIcon) {
//            iv_icon.setVisibility(View.VISIBLE);
//        }
//
//        if (showTitle) {
//            txt_title.setVisibility(View.VISIBLE);
//        }
//
//        if (showMsg) {
//            txt_msg.setVisibility(View.VISIBLE);
//        }
    }

    public void show() {
        setLayout();
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


