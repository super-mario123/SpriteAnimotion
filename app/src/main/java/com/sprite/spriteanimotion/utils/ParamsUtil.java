package com.sprite.spriteanimotion.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by dings on 2015/8/5.
 */
public class ParamsUtil {
    private static final String TAG = "NetUtils";
    public static final String CHARSET = "UTF-8";

    /**
     * 将URL和参数拼接
     * @param url
     * @param params
     * @return
     */
    public static String makeUrl(String url, Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return url;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(url);
        sb.append("?");

        for (Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator(); iterator.hasNext(); sb.append("&")) {
            Map.Entry<String, String> item = iterator.next();
            sb.append(concatKeyValue(item.getKey(), item.getValue()));
        }

        return sb.toString();
    }

    /**
     * 将参数名和参数值用等号相连
     * @param key
     * @param value
     * @return
     */
    private static String concatKeyValue(String key, String value) {
        return encodeString(key) + "=" + encodeString(value);
    }

    /**
     * 对字符串进行编码
     * @param str
     * @return
     */
    private static String encodeString(String str) {
        try {
            return URLEncoder.encode(str, CHARSET);
        } catch (UnsupportedEncodingException ignored) {
            return "";
        }
    }
}
