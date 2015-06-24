package com.taichina.xlt.activity;

import com.taichina.xlt.Utils.StaticData;
import com.taichina.xlt.Utils.util.SystemUiHider;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import com.taichina.xlt.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class SplashActivity extends Activity {
    private ConnectivityManager mConnectivityManager;

    private NetworkInfo netInfo;
    final StaticData mApplication =  StaticData.getInstance();
    BroadcastReceiver myNetReceiver = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, 3500);

             myNetReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {


                String action = intent.getAction();
                if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {

                    mConnectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    netInfo = mConnectivityManager.getActiveNetworkInfo();
                    if(netInfo != null && netInfo.isAvailable()) {


                        /////////////ÍøÂçÁ¬½Ó
                        String name = netInfo.getTypeName();

                        if(netInfo.getType()==ConnectivityManager.TYPE_WIFI){
                            mApplication.setGlobalInfo("wifiState","on");

                        }else if(netInfo.getType()==ConnectivityManager.TYPE_ETHERNET){
                            mApplication.setGlobalInfo("ethernet","on");

                        }else if(netInfo.getType()==ConnectivityManager.TYPE_MOBILE){
                            mApplication.setGlobalInfo("mobileData","on");

                        }
                    } else {
                        mApplication.setGlobalInfo("net","off");

                    }
                }

            }
        };
        //×¢²á¹ã²¥
        IntentFilter mFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(myNetReceiver, mFilter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myNetReceiver!=null){
            unregisterReceiver(myNetReceiver);
        }
    }
}
