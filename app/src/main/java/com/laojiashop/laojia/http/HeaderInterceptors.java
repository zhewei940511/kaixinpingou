package com.laojiashop.laojia.http;


import android.nfc.Tag;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.ApiUtils;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class HeaderInterceptors implements Interceptor {
    private String signst;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody requestBody = request.body();
        String oldUrl = request.url().toString();
        Logger.e("HeaderInterceptors", oldUrl);
        Map<String, String> map = getPublicParams(request);
        if ("POST".equals(request.method())) {
            MediaType mediaType = null;
            if (request.body().contentType() == null) {
                mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
            }
//            //测试打印数据
//            String test="age=20&name=zhangshuai&sex=man";
//            System.out.println("打印数据测试"+getSHA(getSHA(test)+HeaderInterceptors.md5("132K7dQNW8%E!I*DoPVZwGzinrxtcRs@")));
            //直接调用post请求
            String postBodyString = getBuildPostParams(request, map);
            signst = getSHA(getSHA(postBodyString)+HeaderInterceptors.md5("132K7dQNW8%E!I*DoPVZwGzinrxtcRs@"));
            String newpostBodyString = postBodyString.toString() + "&sign=" + signst;
            // String postBodyString = createMd5(request, map);
            //+"&sign"+signst
            request = request.newBuilder().url(oldUrl).method(request.method(),
                    RequestBody.create(requestBody.contentType() == null ? mediaType : requestBody.contentType(), newpostBodyString)).build();
        } else if ("GET".equals(request.method())) {
            String newUrl = getBuildGetParams(map, oldUrl);
            request = request.newBuilder().url(newUrl).build();

        }
        return chain.proceed(request);
    }

    private String bodyToString(RequestBody request) {
        try {
            Buffer buffer = new Buffer();
            request.writeTo(buffer);
            return buffer.readUtf8();
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * SHA1验证
     */
    public static String getSHA(String info) {
        byte[] digesta = null;
        try {
// 得到一个SHA-1的消息摘要
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
// 添加要进行计算摘要的信息
            alga.update(info.getBytes());
// 得到该摘要
            digesta = alga.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
// 将摘要转为字符串
        String rs = byte2hex(digesta);
        return rs;
    }

    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (byte aB : b) {
            stmp = (Integer.toHexString(aB & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }

    /**
     * MD5加密
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * post公共参数
     * @param request
     * @return
     */
    private Map<String, String> getPublicParams(Request request) {
        Map<String, String> map = new HashMap<>();
        //        String deviceId = new DeviceUuidFactory(BaseApplication.getInstance()).getDeviceUuid().toString();
        //        map.put("versionCode", BuildConfig.VERSION_CODE + "");
        //        map.put("os", "android");
        //        map.put("useport", "2");
        //        map.put("deviceId", deviceId);
        //        map.put("uid", SharedPreferencesManager.getUid());
        //        map.put("phoneName", DeviceUuidFactory.getPhoneBrand());
        //        map.put("deviceName", DeviceUuidFactory.getPhoneModel());
        //        map.put("ram", DeviceUuidFactory.getAvailMemory(BaseApplication.getInstance()));
        //        map.put("metrics", DeviceUuidFactory.getMetrics(BaseApplication.getInstance()));
        //        map.put("system", DeviceUuidFactory.getSystemVersion());
        //        map.put("operator", DeviceUuidFactory.getOperators(BaseApplication.getInstance()));
        //        map.put("netType", DeviceUuidFactory.getNetworkType(BaseApplication.getInstance()));
        return map;
    }

    public static Headers SetHeaders(Map<String, String> headersParams) {
        Headers headers = null;
        okhttp3.Headers.Builder headersbuilder = new okhttp3.Headers.Builder();
        if (!headersParams.isEmpty()) {
            Iterator<String> iterator = headersParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                headersbuilder.add(key, headersParams.get(key));
            }
        }
        headers = headersbuilder.build();
        return headers;
    }

//    private FormBody.Builder getBuildPostParams(Map<String, String> map) {
//        FormBody.Builder builder = new FormBody.Builder();
//        try {
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                builder.add(entry.getKey(), entry.getValue());
//            }
//        } catch (Exception e) {
//
//        }
//        return builder;
//    }

    /**
     * 公共参数不加密调用
     *
     * @param request
     * @param map
     * @return
     */
    private String getBuildPostParams(Request request, Map<String, String> map) {
        try {
            FormBody.Builder builder = new FormBody.Builder();
            Map<String, String> newmap = new TreeMap<String, String>();
            //JSONObject formData = new JSONObject();
            int count = 0;
            if (request.body().contentLength() > 0 && request.body() instanceof FormBody) {
                count = ((FormBody) request.body()).size();
                System.out.println(count);
            }
            for (int i = 0; i < count; i++) {
                String n = ((FormBody) request.body()).encodedName(i);
                String v = ((FormBody) request.body()).value(i);
                newmap.put(n, v);
                //  builder.add(n, v);
            }
            //这里将map.entrySet()转换成list
            List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(newmap.entrySet());
            //然后通过比较器来实现排序
            Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
                //升序排序
                @Override
                public int compare(Map.Entry<String, String> o1,
                                   Map.Entry<String, String> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }

            });

            for (Map.Entry<String, String> mapping : list) {
                System.out.println(mapping.getKey() + ":" + mapping.getValue());
            }
            if (!newmap.isEmpty()) {
                try {
                    for (Map.Entry<String, String> entry : newmap.entrySet()) {

                        builder.add(entry.getKey(), entry.getValue() + "");
                    }
                } catch (Exception e) {

                }
            }
            return bodyToString(builder.build());
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * get方法
     * @param map
     * @param oldUrl
     * @return
     */
    private String getBuildGetParams(Map<String, String> map, String oldUrl) {
        Iterator<String> keys = map.keySet().iterator();
        Iterator<String> values = map.values().iterator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?");

        for (int i=0;i<map.size();i++ ) {
            String value=null;
            try {
                value= URLEncoder.encode(values.next(),"utf-8");
            }catch (Exception e){
                e.printStackTrace();
            }

            stringBuffer.append(keys.next()+"="+value);
            if (i!=map.size()-1) {
                stringBuffer.append("&");
            }
            System.out.println(""+stringBuffer.toString());
        }

        return oldUrl + stringBuffer.toString();

//        StringBuilder stringBuilder = new StringBuilder();//创建一个stringBuilder...字符串缓冲区
//        stringBuilder.append(oldUrl);
//        if (oldUrl.contains("?")) {
//            if (oldUrl.indexOf("?") != oldUrl.length() - 1) {
//                stringBuilder.append("&");
//            }
//        } else {
//            stringBuilder.append("?");
//        }
//        try {
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//            }
//        } catch (Exception e) {
//
//        }
//        if (stringBuilder.indexOf("&") != -1) {
//            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("&"));
//        }
//        return stringBuilder.toString();
    }
    /**
     * 加密
     *
     * @param map
     * @return
     */
    private String createMd5(Request request, Map<String, String> map) {
        try {
            FormBody.Builder builder = new FormBody.Builder();
            //JSONObject formData = new JSONObject();
            int count = 0;
            if (request.body().contentLength() > 0 && request.body() instanceof FormBody) {
                count = ((FormBody) request.body()).size();
            }
            for (int i = 0; i < count; i++) {
                String n = ((FormBody) request.body()).encodedName(i);
                String v = ((FormBody) request.body()).value(i);
                builder.add(n, v);
            }
            if (!map.isEmpty()) {
                try {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        builder.add(entry.getKey(), entry.getValue() + "");
                    }
                } catch (Exception e) {

                }
            }
            return bodyToString(builder.build());
        } catch (Exception e) {
            return "";
        }
    }

}
