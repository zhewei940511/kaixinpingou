package com.laojiashop.laojia.interceptor;

import android.text.TextUtils;

import com.laojiashop.laojia.utils.ComParamContact;
import com.zhouyou.http.interceptor.BaseDynamicInterceptor;
import com.zhouyou.http.utils.HttpLog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CustomSignInterceptor extends BaseDynamicInterceptor<CustomSignInterceptor> {
    @Override
    public TreeMap<String, String> dynamic(TreeMap<String, String> dynamicMap) {
        //判断是否验签
        if (isSign())
        {
            dynamicMap.put(ComParamContact.Common.SIGN, sign(dynamicMap));
        }
        return dynamicMap;
    }
    private String sign(TreeMap<String, String> dynamicMap) {
        //这里将map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(dynamicMap.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            //升序排序
            @Override
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }

        });
        StringBuilder sb = new StringBuilder("");
        for (Map.Entry<String, String> entry : dynamicMap.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        String signStr=sb.substring(0,sb.length()-1);
      HttpLog.i(signStr);
      return getSHA(getSHA(signStr)+md5("132K7dQNW8%E!I*DoPVZwGzinrxtcRs@"));
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
