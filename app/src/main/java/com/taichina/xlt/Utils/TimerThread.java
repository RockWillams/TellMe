package com.taichina.xlt.Utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class TimerThread implements Runnable{
    Handler mHandler = null;
    StaticData sd = null;

      public TimerThread(Handler mHandler,StaticData sd) {
        this.mHandler = mHandler;
          this.sd=sd;
    }

    @Override
    public void run() {
        while (sd.getEditCheckFlag()){
            try {
                Thread.sleep(1000);
                Message message = new Message();
                message.what=1;
                Log.e("send","send tjq");
                mHandler.sendMessage(message);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

