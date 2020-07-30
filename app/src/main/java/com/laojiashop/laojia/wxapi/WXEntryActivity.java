package com.laojiashop.laojia.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.utils.ComParamContact;
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

	@Override
	public void onResp(BaseResp resp) {

		int result = 0;

		switch (resp.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			String code = ((SendAuth.Resp) resp).code;
		//	getAccessToken(code);
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
//	//获取token
//	private void getAccessToken(String code)
//	{
//		//获取授权
//		StringBuffer loginUrl = new StringBuffer();
//		loginUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token")
//				.append("?appid=")
//				.append("wx45ccf8958a0a24c7")
//				.append("&secret=")
//				.append("e9c071f3326663856bc6cf02c2d6b657")
//				.append("&code=")
//				.append(code)
//				.append("&grant_type=authorization_code");
//		OkHttpClient okHttpClient=new OkHttpClient();
//		Request request=new Request.Builder().url(loginUrl.toString()).get().build();
//		Call call = okHttpClient.newCall(request);
//		call.enqueue(new Callback() {
//			@Override
//			public void onFailure(Call call, IOException e) {
//
//			}
//
//			@Override
//			public void onResponse(Call call, Response response) throws IOException {
//				String responseInfo= response.body().string();
//				String access = null;
//				String openId = null;
//				JSONObject jsonObject = null;
//				try {
//					jsonObject = new JSONObject(responseInfo);
//					access = jsonObject.getString("access_token");
//					openId = jsonObject.getString("openid");
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				getUserInfo(access,openId);
//			}
//		});
//	}
//	//获取用户信息
//	private void getUserInfo(String access, String openid)
//	{
//		String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access + "&openid=" + openid;
//		OkHttpClient okHttpClient = new OkHttpClient();
//		final Request request = new Request.Builder()
//				.url(getUserInfoUrl)
//				.get()//默认就是GET请求，可以不写
//				.build();
//		Call call = okHttpClient.newCall(request);
//		call.enqueue(new Callback() {
//			@Override
//			public void onFailure(Call call, IOException e) {
//				Log.d("fan12", "onFailure: ");
//				//mProgressDialog.dismiss();
//			}
//
//			@Override
//			public void onResponse(Call call, Response response) throws IOException {
//				String responseInfo= response.body().string();
//				Log.d("fan123", "onResponse: " + responseInfo);
//				SharedPreferences.Editor editor= getSharedPreferences("userInfo", MODE_PRIVATE).edit();
//				editor.putString("responseInfo", responseInfo);
//				editor.commit();
//				finish();
//
//			}
//		});
//
//	}
}