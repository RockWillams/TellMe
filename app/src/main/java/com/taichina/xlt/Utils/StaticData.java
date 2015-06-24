package com.taichina.xlt.Utils;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public  class  StaticData extends Application {
    private int b;
    public  int currentTask;//当前的任务预想编号

    public  boolean EditCheckFlag = false;

    public boolean getEditCheckFlag() {
        return EditCheckFlag;
    }

    public void setEditCheckFlag(boolean editCheckFlag) {
        EditCheckFlag = editCheckFlag;
    }






    private static StaticData instance = new StaticData();

    public static  StaticData getInstance(){

        return instance;
    }



    public  String targetServerAddr = "http://10.136.246.253:8080/app/jwj/";
    public String getTargetServerAddr() {
        return targetServerAddr;
    }
    public void setTargetServerAddr(String targetServerAddr) {
        this.targetServerAddr = targetServerAddr;
    }
    //当前的全局数据
    //结构: daoa[0] 的为第0个任务，dota[1］为第一个任务，一次递增
    //在dota[0]的每个数据中，第0个map即dota[0].get(0)为基本信息+userid+taskid+keynotes+keyitem+verifyRecords，
    // 之后多个map即第0个map即dota[0].get(1),第0个map即dota[0].get(2)....，每个map为一个问题数据，
    // 包括问题的反馈内容和编号.
    //

    public ArrayList<ArrayList<HashMap<String,Object>>> dota = new  ArrayList<ArrayList<HashMap<String,Object>>>();
    //currentTask的get和set
    public int getCurrentTask() {
        return currentTask;
    }
    public void setCurrentTask(int currentTask) {
        this.currentTask = currentTask;
    }

    //dota的get,即获取第i个任务；
    public ArrayList<HashMap<String, Object>> getDota(int i) {
        if (dota.size()>=i+1){
            Log.e("error!!!", "getDota haha");
            return dota.get(i);

        }
        else return  null;


    }
    public void setDota(int i,ArrayList<HashMap<String, Object>> dota) {
        if (dota.size()>=i+1){
            this.dota.set(i, dota);
        }
        else Log.e("error!!!","setDota的i的范围超界");
    }
    //在array的尾巴上增添一个新的任务
    public  void addDota(ArrayList<HashMap<String, Object>> dota){
        this.dota.add(dota);
    }
    public ArrayList<ArrayList<HashMap<String,Object>>> retDota(){
        return  dota;
    }

    //获取当前的任务arrarylist<Hash<String,string>>;
    public ArrayList<HashMap<String, Object>> getcurrenttask() {
        return getDota(currentTask);
    }




    //当前的用户全局信息：globalInfo，包含字段：loginState,currentUserId;
    HashMap<String,String> globalInfo=new HashMap<String,String>();
    public String getGlobalInfo(String key) {
        return globalInfo.get(key);
    }
    public String setGlobalInfo(String key,String value) {
        if(key != null&&value!=null){
            return globalInfo.put(key, value);
        }
        Log.e("eoor","key/value  is null");
        return  null;

    }






    /********************
     *当前的基础信息。
     *
     *
     */
    String[] line1 =new String[]{"- - -","京杭线","京沪线"};
    String[][]  line2 = new String[][]{{"- - -"},{"- - -","线路1","线路2"},{"- - -","线路3","线路4"}};
    String[][][] line3 = new String[][][]{{{"- - -"}},{{"- - -"},{"- - -","司机1","司机2"},{"- - -","司机3","司机4"}},{{"- - -"},{"司机5","司机6"},{"司机7","司机8"}}};
    public String[] getLine1(){
        return  line1;
    }
    public String[][] getLine2(){
        return  line2;
    }
    public String[][][] getLine3(){
        return  line3;
    }


    String[] trainModel=new String[]{"- - -","东风1","东风2","东风3","和谐1","和谐2","和谐3"};
    public String[] getTrainModel() {
        return trainModel;
    }




    String[] checkedLine=new String[]{"- - -","线路1","线路2","线路3","线路4","线路5","线路6"};
    public String[] getCheckedLine() {
        return checkedLine;
    }




    String[] trainNumber=new String[]{"- - -","车次1","车次2","车次3","车次4","车次5","车次6"};
    public String[] getTrainNumber() {
        return trainNumber;
    }


    String[] gooffStation=new String[]{"- - -","成都1","北京2","深圳3","洛杉矶4","上海5","柏林6"};

    public String[] getGooffStation() {
        return gooffStation;
    }


    String[] arriveStation=new String[]{"- - -","成都1","北京2","深圳3","洛杉矶4","上海5","柏林6"};
    public String[] getArriveStation() {
        return arriveStation;
    }






    public int postiton2;
    public int getPostiton2() {
        return postiton2;
    }
    public void setPostiton2(int postiton2) {
        this.postiton2 = postiton2;
    }










    @Override
    public void onCreate(){
        b = 0;
        globalInfo = new HashMap<String,String>();
        globalInfo.put("currentUserId","tang");
        super.onCreate();
    }
}