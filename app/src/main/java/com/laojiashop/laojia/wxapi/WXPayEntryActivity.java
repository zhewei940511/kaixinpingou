package com.laojiashop.laojia.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.laojiashop.laojia.utils.ComParamContact;
import com.laojiashop.laojia.utils.ToastUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {


    private IWXAPI api;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, ComParamContact.APP_ID, false);
        try {
            Intent intent = getIntent();
            api.handleIntent(intent, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
         finish();
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (baseResp.errCode == 0) {
                //支付成功后跳转页面
            // startActivity(new Intent(WXPayEntryActivity.this, MallPaySuccessActivity.class));
            } else if (baseResp.errCode == -1) {
                ToastUtil.showToast("支付错误" + baseResp.errStr);

            } else if (baseResp.errCode == -2) {
                ToastUtil.showToast("取消支付");
            }
        } else {
            ToastUtil.showToast(baseResp.errStr);
        }
    }
}
