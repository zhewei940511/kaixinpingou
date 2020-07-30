package com.laojiashop.laojia.interceptor;

import android.text.TextUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CommonParamsInterceptor implements Interceptor {
    /**
     * 请求方法-GET
     */
    private static final String REQUEST_METHOD_GET = "GET";

    /**
     * 请求方法POST
     */
    private static final String REQUEST_METHOD_POST = "POST";

    @Override
    public Response intercept(Chain chain) throws IOException {


        //获取原先的请求对象
        Request request = chain.request();
        if (REQUEST_METHOD_GET.equals(request.method())) {
            request = addGetBaseParams(request);
        } else if (REQUEST_METHOD_POST.equals(request.method())) {
            request = addPostBaseParams(request);
        }
        return chain.proceed(request);

    }

    /**
     * get请求方式拦截
     *
     * @param request
     * @return
     */
    private Request addGetBaseParams(Request request) {
        HttpUrl httpUrl = request.url().newBuilder().build();
        //添加签名
        Set<String> nameSet = httpUrl.queryParameterNames();
        ArrayList<String> nameList = new ArrayList<>();
        nameList.addAll(nameSet);
        Map<String, String> paramMap = new HashMap<>();
        for (int i = 0; i < nameList.size(); i++) {
            paramMap.put(nameList.get(i), httpUrl.queryParameterValue(i));
        }
        //判断返回的参数是否为空
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(paramMap.entrySet());
        if (list.size()!=0)
        {
            Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
                //升序排序
                @Override
                public int compare(Map.Entry<String, String> o1,
                                   Map.Entry<String, String> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
            });
            StringBuilder sb = new StringBuilder("");
            for (Map.Entry<String, String> mapping : list) {
                System.out.println(mapping.getKey() + ":" + mapping.getValue());
                sb.append(mapping.getKey()).append("=").append(mapping.getValue()).append("&");
            }
            String signStr = sb.substring(0, sb.length() - 1);
            String signst = getSHA(getSHA(signStr) + CommonParamsInterceptor.md5("132K7dQNW8%E!I*DoPVZwGzinrxtcRs@"));
            httpUrl = httpUrl.newBuilder().addQueryParameter("sign", signst).build();
            request = request.newBuilder().url(httpUrl).build();
        }else
        {
            StringBuilder sb = new StringBuilder("");
            for (Map.Entry<String, String> mapping : list) {
                System.out.println(mapping.getKey() + ":" + mapping.getValue());
                sb.append(mapping.getKey()).append("=").append(mapping.getValue()).append("&");
            }
            httpUrl = httpUrl.newBuilder().build();
            request = request.newBuilder().url(httpUrl).build();
        }
//        StringBuilder sb = new StringBuilder("");
//        for (Map.Entry<String, String> mapping : list) {
//            System.out.println(mapping.getKey() + ":" + mapping.getValue());
//            sb.append(mapping.getKey()).append("=").append(mapping.getValue()).append("&");
//        }
//        String signStr = sb.substring(0, sb.length() - 1);
//        String signst = getSHA(getSHA(signStr) + CommonParamsInterceptor.md5("132K7dQNW8%E!I*DoPVZwGzinrxtcRs@"));
//        httpUrl = httpUrl.newBuilder().addQueryParameter("sign", signst).build();
       // request = request.newBuilder().url(httpUrl).build();
        return request;
    }

    /**
     * post
     *
     * @param request
     * @return
     */
    private Request addPostBaseParams(Request request) {
        if (request.body() instanceof FormBody) {
            FormBody formBody = (FormBody) request.body();
            FormBody.Builder builder = new FormBody.Builder();
            for (int i = 0; i < formBody.size(); i++) {
                //      @FieldMap 注解 Map元素中 key 与 value 皆不能为null,否则会出现NullPointerException
                if (formBody.value(i) != null)
                    builder.add(formBody.name(i), formBody.value(i));

            }
            //添加签名
            Map<String, String> paramMap = new TreeMap<String, String>();
            formBody = builder.build();
            for (int i = 0; i < formBody.size(); i++) {
                paramMap.put(formBody.name(i), formBody.value(i));
            }
            List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(paramMap.entrySet());
            //判断参数是否为空
            if (list.size()!=0)
            {
                Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
                    //升序排序
                    @Override
                    public int compare(Map.Entry<String, String> o1,
                                       Map.Entry<String, String> o2) {
                        return o1.getKey().compareTo(o2.getKey());
                    }
                });
                StringBuilder sb = new StringBuilder("");
                for (Map.Entry<String, String> mapping : list) {
                    System.out.println(mapping.getKey() + ":" + mapping.getValue());
                    sb.append(mapping.getKey()).append("=").append(mapping.getValue()).append("&");
                }

                String signStr = sb.substring(0, sb.length() - 1);
                String signst = getSHA(getSHA(signStr) + CommonParamsInterceptor.md5("132K7dQNW8%E!I*DoPVZwGzinrxtcRs@"));
                formBody = builder.add("sign", signst).build();
                request = request.newBuilder().post(formBody).build();
            }else {
                StringBuilder sb = new StringBuilder("");
                for (Map.Entry<String, String> mapping : list) {
                    System.out.println(mapping.getKey() + ":" + mapping.getValue());
                    sb.append(mapping.getKey()).append("=").append(mapping.getValue()).append("&");
                }

                String signStr = sb.substring(0, sb.length() - 1);
                formBody = builder.build();
                request = request.newBuilder().post(formBody).build();
            }
//            Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
//                //升序排序
//                @Override
//                public int compare(Map.Entry<String, String> o1,
//                                   Map.Entry<String, String> o2) {
//                    return o1.getKey().compareTo(o2.getKey());
//                }
//            });
//            StringBuilder sb = new StringBuilder("");
//            for (Map.Entry<String, String> mapping : list) {
//                System.out.println(mapping.getKey() + ":" + mapping.getValue());
//                sb.append(mapping.getKey()).append("=").append(mapping.getValue()).append("&");
//            }
//
//            String signStr = sb.substring(0, sb.length() - 1);
//            String signst = getSHA(getSHA(signStr) + CommonParamsInterceptor.md5("132K7dQNW8%E!I*DoPVZwGzinrxtcRs@"));
//            formBody = builder.add("sign", signst).build();
//            request = request.newBuilder().post(formBody).build();
        }
        return request;
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
}
