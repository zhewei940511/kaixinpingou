package com.laojiashop.laojia.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.utils.ComParamContact;
import com.laojiashop.laojia.utils.ToastUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	api = WXAPIFactory.createWXAPI(this, ComParamContact.APP_ID, false);
        api.handleIntent(getIntent(), this);
	}
	@Override
	public void onReq(BaseReq req) {
	}
	// 微信发送请求到第三方应用时，会回调到该方法
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent,this);
	}
	@Override
	public void onResp(BaseResp resp) {
		int result = 0;
    	if (resp.getType()== ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX)
		{
			switch (resp.errCode)
			{
				case BaseResp.ErrCode.ERR_OK:
					result = R.string.errcode_success;
					break;
				case BaseResp.ErrCode.ERR_USER_CANCEL:
					result = R.string.errcode_cancel;
					break;
				case BaseResp.ErrCode.ERR_UNSUPPORT:
					break;
				default:
					break;
			}
			finish();
		}else if(resp.getType()== ConstantsAPI.COMMAND_SENDAUTH)
		{
			switch (resp.errCode) {
				case BaseResp.ErrCode.ERR_OK:
					String code = ((SendAuth.Resp) resp).code;
					Log.d("ASd_sdx0",code);
					Intent intent=new Intent();
					intent.setAction("wxdl");
					intent.putExtra("code",code);
					sendOrderedBroadcast(intent,null);
					result = R.string.errcode_success;
					break;
				case BaseResp.ErrCode.ERR_USER_CANCEL:
					result = R.string.errcode_cancel;
					break;
				case BaseResp.ErrCode.ERR_AUTH_DENIED:
					result = R.string.errcode_deny;
					break;
				case BaseResp.ErrCode.ERR_UNSUPPORT:
					result = R.string.errcode_unsupported;
					break;
				default:
					result = R.string.errcode_unknown;
					break;
			}
			finish();
		}


	}
}