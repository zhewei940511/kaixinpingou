package com.laojiashop.laojia.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.laojiashop.laojia.R;

public class RecruittoDialogUtil {
    public static Dialog showProtocolDialogNoBtns(Activity activity) {
        return showProtocolDialog(activity, null, null);
    }

    public static Dialog showProtocolDialog(Activity activity,
                                            View.OnClickListener okListener,
                                            View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_protocolrecruitto, null);
        final Dialog dialog = new AlertDialog.Builder(activity)
                .setView(view)
                .show();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawable(null);
        if (okListener != null && cancelListener != null) {
            view.findViewById(R.id.quitBtn).setOnClickListener((v) -> {
                dialog.dismiss();
                cancelListener.onClick(v);
            });
            view.findViewById(R.id.agreeBtn).setOnClickListener((v) -> {
                dialog.dismiss();
                okListener.onClick(v);
            });
            dialog.setCancelable(false);
        } else {
            view.findViewById(R.id.btnsContainer).setVisibility(View.GONE);
        }
        String intro = SPUtils.getString(activity, "rulestr");
        // ((WebView) view.findViewById(R.id.webView)).loadUrl(intro);
        WebView wvDetails = view.findViewById(R.id.webView);
        //设置手机样式
        WebSettings webSettings = wvDetails.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js
        //不支持屏幕缩放
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        //不显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);
        wvDetails.loadDataWithBaseURL(null, intro, "text/html", "utf-8", null);
//        wvDetails.setVerticalScrollBarEnabled(false);
//        wvDetails.setHorizontalScrollBarEnabled(false);
//        wvDetails.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return (event.getAction() == MotionEvent.ACTION_MOVE);
//            }
//        });
        return dialog;
    }
}
