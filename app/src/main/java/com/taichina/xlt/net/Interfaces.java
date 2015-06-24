package com.taichina.xlt.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * @ClassName Interfaces
 * @Description 网络接口封装
 * @date 2015-4-11
 */
public class Interfaces {

    /**
     * 服务访问地址
     */
    private final static String SERVER = "http://192.168.1.129:8080/e-house";


    /**
     * 绑定百度推送
     */
    public static String updateCleanerPushConfig(double clnLatitude, String clnServiceId, double clnLongitude) {
        return SERVER + "/updateCleanerPushConfig?clnLatitude=" + clnLatitude + "&clnServiceId=" + clnServiceId + "&clnLongitude=" + clnLongitude;
    }

    /**
     * 绑定百度推送
     */
    public static String commitPushUrl(String clnServiceId, String baiduUserId, String baiduChannelId) {
        return SERVER + "/updateCleanerPushConfig?clnServiceId=" + clnServiceId
                + "&baiduUserId=" + baiduUserId + "&baiduChannelId=" + baiduChannelId;
    }

    /**
     * @param responseBody
     * @param key
     * @return
     */

    public static String getJson(String responseBody, String key) {
        String str = null;
        try {
            JSONObject json = new JSONObject(responseBody);
            str = json.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


}
