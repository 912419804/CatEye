package com.franky.cateye.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.franky.cateye.utils.NetworkUtils;

/**
 * Created by Administrator on 2017/2/15.
 * 网络连接变化Receiver
 */

public class NetworkReceiver extends BroadcastReceiver {
    private NetworkStateListener mListener;


    public interface NetworkStateListener{
        void onNetworkConnected();
    }
    private Context context;
    private static final String TAG = "NetWork";
    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;

        if (NetworkUtils.isWifiAvailable() || NetworkUtils.is4G()){
            if (null != mListener){
                mListener.onNetworkConnected();
            }
        }
//
//        if(isNetworkConnected(context)){
//            Log.e(TAG,"网络已连接");
//            if(isWifiConnected(context)){
//                Log.v(TAG, "当前为wifi连接");
//            } else {
//                String c = isFastMobileNetwork(context);
//                Log.e(TAG,"当前网络为"+c);
//            }
//        } else {
//            Log.e(TAG,"网络无连接");
//        }
    }

    public void setNetworkStateListener(NetworkStateListener listener){
        this.mListener = listener;
    }

    //网络是否有连接
    public boolean isNetworkConnected(Context context) {
        if(context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if(mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    //是否wifi连接
    public boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    //对应的网络类型
    public static String isFastMobileNetwork(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "2G";
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
            case 17: // TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                return "3G";
            case TelephonyManager.NETWORK_TYPE_LTE:
            case 18: // TelephonyManager.NETWORK_TYPE_IWLAN:
                return "4G";
            default:
                return "NETWORK_CLASS_UNKNOWN";

        }
    }
}
