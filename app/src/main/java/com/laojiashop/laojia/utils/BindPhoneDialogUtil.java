package com.laojiashop.laojia.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;

import java.io.IOException;

/**
 * 绑定手机弹窗
 */
public class BindPhoneDialogUtil {
    private static String phonecode,phonenum;
    private static  CountDownTimerUtils countDownTimer;
    private static  Button btnBindphonecode;
    public static Dialog showProtocolDialogNoBtns(Activity activity) {
        return showProtocolDialogphone(activity, null, null);
    }

    public static Dialog showProtocolDialogphone(Activity activity,
                                                 View.OnClickListener okListener,
                                                 View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_phonedialog, null);
        final Dialog dialog = new AlertDialog.Builder(activity)
                .setView(view)
                .show();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //文本框
        EditText phonetext = view.findViewById(R.id.et_phonenum);
        EditText phonecodes=view.findViewById(R.id.et_bindphonecode);
        //按钮
        btnBindphonecode=view.findViewById(R.id.btn_bindphonecode);
        //设置点击事件
        btnBindphonecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phonecode =phonecodes.getText().toString().trim();
                phonenum = phonetext.getText().toString().trim();
                if (TextUtils.isEmpty(phonenum))
                {
                    ToastUtil.showToast("请输入手机号");
                    return;
                }
                HttpRxObservable.getObservable(ApiUtils.getApiService().getcode(phonenum, "3")).subscribe(new BaseObserver<Object>(activity) {
                    @Override
                    public void onHandleSuccess(Object o) throws IOException {
                        countDownTimer = new CountDownTimerUtils(btnBindphonecode, 60000, 1000);
                        countDownTimer.start();
                        ToastUtil.showToast("请求数据" + o.toString());
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
            }
        });
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawable(null);
        // 点击事件
        view.findViewById(R.id.canclebtn).setOnClickListener((v) -> {
            dialog.dismiss();
        });
        view.findViewById(R.id.makesureBtn).setOnClickListener((v) -> {
            phonecode =phonecodes.getText().toString().trim();
            phonenum = phonetext.getText().toString().trim();
            if (TextUtils.isEmpty(phonenum)) {
                ToastUtil.showToast("请输入手机号");
                return;
            }
            if (TextUtils.isEmpty(phonecode))
            {
                ToastUtil.showToast("请输入邀请码");
                return;
            }
            //调用接口
            HttpRxObservable.getObservable(ApiUtils.getApiService().bindPhoneSave(phonenum,phonecode)).subscribe(new BaseObserver<Object>(activity) {
                @Override
                public void onHandleSuccess(Object o) throws IOException {
                    ToastUtil.showToast("绑定成功");
                    dialog.dismiss();
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                }
            });
        });
        dialog.setCancelable(false);
        return dialog;

    }
}
