package com.taichina.xlt.net;

import android.util.Log;

import com.taichina.xlt.Utils.StaticData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by uu on 2015-06-18.
 */
public class PrePlan {
    public PrePlan(){

    }
   public JSONObject GetJsonObject(JSONObject jsonObject,StaticData mApplication){
       int num = mApplication.getCurrentTask();
       JSONObject mJsonRet = null;
       ArrayList<HashMap<String,Object>> mArrayList = mApplication.getcurrenttask();
       String addr = mApplication.getTargetServerAddr()+"preplan";

       HashMap<String,Object> mhashmap = new HashMap<String,Object>();
       try {
           jsonObject.put("type","ganbu");

           mhashmap.put("masterDriver",jsonObject.get("masterDriver"));
           mhashmap.put("trainModel",jsonObject.get("trainModel"));
           mhashmap.put("trainModel",jsonObject.get("checkedLine"));
           mhashmap.put("trainNumber",jsonObject.get("trainNumber"));
           mhashmap.put("gooffStation",jsonObject.get("gooffStation"));
           mhashmap.put("arriveStation",jsonObject.get("arriveStation"));
           mhashmap.put("arriveTime",jsonObject.get("arriveTime"));
           mhashmap.put("gooffTime",jsonObject.get("gooffTime"));
           //
           mhashmap.put("type","ganbu");
       }catch (Exception e){
       }
       mArrayList.set(0,mhashmap);
       GetJsonObject mGetJson = new GetJsonObject();
       mJsonRet =mGetJson.GetJsonObject(jsonObject,addr);
       if(mJsonRet != null){
           try {
               mhashmap.put("keyNotes",mJsonRet.get("keyNotes").toString());
               mhashmap.put("keyItems",mJsonRet.get("keyItems").toString());
               mhashmap.put("veryfyRecords",mJsonRet.get("veryfyRecords").toString());

           }catch (Exception e){
               Log.e("tjq","返回的key 提示信息 有错误");
           }
           mArrayList.set(0,mhashmap);
           return mJsonRet;
       }
       else return null;  //网络不好
   }

}
