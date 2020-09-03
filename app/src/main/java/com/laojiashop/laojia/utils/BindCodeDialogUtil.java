package com.laojiashop.laojia.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;

import java.io.IOException;

public class BindCodeDialogUtil {
    public static Dialog showProtocolDialogNoBtns(Activity activity) {
        return showProtocolDialogphone(activity, null, null);
    }
    public static Dialog showProtocolDialogphone(Activity activity,
                                                 View.OnClickListener okListener,
                                                 View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_codedialog, null);
        final Dialog dialog = new AlertDialog.Builder(activity)
                .setView(view)
                .show();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //文本框
        EditText codetext = view.findViewById(R.id.et_code);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawable(null);
        // 点击事件
        view.findViewById(R.id.canclebtn).setOnClickListener((v) -> {
            dialog.dismiss();//这样实现不了?
        });
        view.findViewById(R.id.makesureBtn).setOnClickListener((v) -> {
            String code = codetext.getText().toString().trim();
            if (TextUtils.isEmpty(code)) {
                ToastUtil.showToast("请输入邀请码");
                return;
            }
            //调用接口
            HttpRxObservable.getObservable(ApiUtils.getApiService().makeRelationByCode(code)).subscribe(new BaseObserver<Object>(activity) {
                @Override
                public void onHandleSuccess(Object o) throws IOException {
                    ToastUtil.showToast("绑定成功");
                    dialog.dismiss();
                }
            });
        });
        dialog.setCancelable(false);
        return dialog;

    }
}
