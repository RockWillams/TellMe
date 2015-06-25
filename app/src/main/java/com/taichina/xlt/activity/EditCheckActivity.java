package com.taichina.xlt.activity;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.taichina.xlt.Adapter.myBaseAdapter;
import com.taichina.xlt.R;
import com.taichina.xlt.Utils.StaticData;
import com.taichina.xlt.Utils.TimerThread;
import com.taichina.xlt.fragment.TimerPickFragment;
import com.taichina.xlt.net.GetJsonObject;
import com.taichina.xlt.net.PrePlan;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class EditCheckActivity extends ActionBarActivity {

    ArrayList<Spinner> SpinnerArray;
    TextView gooffTimeText;
    TextView arriveTimeText;
    TextView inspectTimeText;
    TextView undefText;

    TextView preplanId;
    TextView keyNotes;
    TextView keyItems;
    TextView verifyRecords;
    TextView addProblemButton;
    TextView masterDriver;
    public ListView mListView;

    public Spinner sp1;
    public Spinner sp2;
    public Spinner sp3;

    public  String tmp1 ="- - -";


    public HashMap<String, Object> maplist;
    public ArrayList<HashMap<String, Object>> mListViewCache;
    public ArrayList<HashMap<String, Object>> myCurrentTask;
    public myBaseAdapter madapter;
    public StaticData mApplication;
    public View chooseDriverLayout;

    public JSONObject tmpjs=null;

    public int test =1;

    public ArrayList<HashMap<String,Object>> tmplist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (StaticData)getApplication();//获取全局数据application
        setContentView(R.layout.checkinfo);
        //mListViewCache.get(0).这个map是基本信息+keynote+keyitem+verifyrecords,后面的get(1)，get(2)....是添加的问题。
          Log.e("current int ",mApplication.currentTask+"");
         tmplist = mApplication.getcurrenttask();


            mListViewCache = tmplist;

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        findviews();
        setspinerAdapter_action(sp1, sp2, sp3);
        updataUI();

        gooffTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("sss", "sdfsdf");
                Calendar c = Calendar.getInstance();
                Dialog dialog = new TimePickerDialog(EditCheckActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int min) {
                        gooffTimeText.setText(hourOfDay + ":" + min);
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);
                dialog.show();

            }
        });

        arriveTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("sss", "sdfsdf");
                Calendar c = Calendar.getInstance();
                Dialog dialog = new TimePickerDialog(EditCheckActivity.this,new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int min) {
                        arriveTimeText.setText(hourOfDay+":"+min);
                    }
                },c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),true);
                dialog.show();

            }
        });




        addProblemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(EditCheckActivity.this, AddItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", "111");
                intent.putExtras(bundle);
                intent.putExtra("id", "222");
                startActivityForResult(intent, 1);
            }
        });

        findViewById(R.id.get_keyinfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if ((masterDriver.getText().toString() == tmp1)
                            || (((Spinner) (SpinnerArray.get(0))).getSelectedItem().toString() == tmp1)
                            || (((Spinner) (SpinnerArray.get(1))).getSelectedItem().toString() == tmp1)
                            || (((Spinner) (SpinnerArray.get(2))).getSelectedItem().toString() == tmp1)
                            || (((Spinner) (SpinnerArray.get(3))).getSelectedItem().toString() == tmp1)
                            || (((Spinner) (SpinnerArray.get(4))).getSelectedItem().toString() == tmp1)) {
                        Toast.makeText(getApplicationContext(), "请填写完整的“基本信息”！", Toast.LENGTH_LONG).show();
                    } else {
                        JSONObject mJson = new JSONObject();
                        JSONObject mJsonret = new JSONObject();
                        mJson.put("masterDriver", masterDriver.getText().toString());
                        mJson.put("trainModel", ((Spinner) (SpinnerArray.get(0))).getSelectedItem().toString());
                        mJson.put("checkedLine", ((Spinner) (SpinnerArray.get(1))).getSelectedItem().toString());
                        mJson.put("trainNumber", ((Spinner) (SpinnerArray.get(2))).getSelectedItem().toString());
                        mJson.put("gooffStation", ((Spinner) (SpinnerArray.get(3))).getSelectedItem().toString());
                        mJson.put("arriveStation", ((Spinner) (SpinnerArray.get(4))).getSelectedItem().toString());
                        mJson.put("gooffTimeText", gooffTimeText.getText().toString());
                        mJson.put("arriveTimeText", arriveTimeText.getText().toString());
                        mJson.put("inspectTimeText", inspectTimeText.getText().toString());

                        GetJsonObject myGetJson = new GetJsonObject();
                        mJsonret = myGetJson.GetJsonObject(mJson, mApplication.getTargetServerAddr() + "preplan");
                        Log.e("GetJsonRet", mJsonret.toString());
                        mJsonret.put("keyNotes","keyNotes0000");
                        mJsonret.put("keyItems","keyItems0000");
                        mJsonret.put("verifyRecords", "verifyRecords00000");
                        mJsonret.put("e",0);
                        Log.e("s++","s s -++-");
                        if (mJsonret == null) {
                            Log.e("s++1","s s -++-");
                            Toast.makeText(getApplicationContext(), "网络不好呦！", Toast.LENGTH_LONG).show();
                        } else {
                            if (mJsonret.getInt("e") != 0) {
                                Log.e("s++2","s s -++-");
                                Toast.makeText(getApplicationContext(), "数据错误！", Toast.LENGTH_LONG).show();
                            } else {
                                Log.e("s++3","s s -++-");
                                Toast.makeText(getApplicationContext(), "正常！", Toast.LENGTH_LONG).show();
                                keyNotes.setText(mJsonret.getString("keyNotes"));
                                keyItems.setText(mJsonret.getString("keyItems"));
                                verifyRecords.setText(mJsonret.getString("verifyRecords"));
                                findViewById(R.id.get_keyinfo).setVisibility(View.GONE);
                                findViewById(R.id.tips).setVisibility(View.VISIBLE);
                                findViewById(R.id.question_list).setVisibility(View.VISIBLE);
                                //更新listview问题列表项目




                            }
                        }


                    }

                } catch (Exception e) {

                }


            }
        });
        mApplication.setEditCheckFlag(true);
       // new Thread(new TimerThread(handler,mApplication)).start();

    }
    public void  updataUI(){
        if(tmplist.get(0).get("keyNotes") ==null){
            findViewById(R.id.get_keyinfo).setVisibility(View.VISIBLE);
            findViewById(R.id.tips).setVisibility(View.GONE);
            findViewById(R.id.question_list).setVisibility(View.GONE);
            return;
        }


        findViewById(R.id.get_keyinfo).setVisibility(View.GONE);
        findViewById(R.id.tips).setVisibility(View.VISIBLE);
        findViewById(R.id.question_list).setVisibility(View.VISIBLE);
    try {
            masterDriver.setText((tmplist.get(0).get("masterDriver").toString()));
            ((Spinner) (SpinnerArray.get(0))).setSelection(Integer.parseInt(String.valueOf((tmplist.get(0).get("trainModel")))), true);
            //((Spinner) (SpinnerArray.get(0))).setSelection(5, true);
            ((Spinner) (SpinnerArray.get(1))).setSelection(Integer.parseInt(String.valueOf((tmplist.get(0).get("checkedLine")))),true);
            ((Spinner) (SpinnerArray.get(2))).setSelection(Integer.parseInt(String.valueOf((tmplist.get(0).get("trainNumber")))),true);
            ((Spinner) (SpinnerArray.get(3))).setSelection(Integer.parseInt(String.valueOf((tmplist.get(0).get("gooffStation")))),true);
            ((Spinner) (SpinnerArray.get(4))).setSelection(Integer.parseInt(String.valueOf((tmplist.get(0).get("arriveStation")))),true);
        //inspectTime.setText((tmplist.get(0).get("inspectTime").toString()));
            gooffTimeText.setText(tmplist.get(0).get("gooffTime").toString());
            arriveTimeText.setText(tmplist.get(0).get("arriveTime").toString());

            keyNotes.setText(tmplist.get(0).get("keyNotes").toString());
            keyItems.setText(tmplist.get(0).get("keyItems").toString());
            verifyRecords.setText(tmplist.get(0).get("verifyRocords").toString());
            inspectTimeText.setText(tmplist.get(0).get("inspectTime").toString());
    }catch (Exception e){
        Log.e("exception " ,"update ui error");
    }
        finally {
        masterDriver.setText((tmplist.get(0).get("masterDriver").toString()));
    }
        if(madapter == null){
            madapter = new myBaseAdapter(EditCheckActivity.this, mListViewCache, R.layout.seatch_list_submit, new String[]{"drawable", "tv1", "tv2", "tv3", "tv4", "tv5", "et1"}
                    , new int[]{R.id.imageView1, R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5, R.id.submit_text},true);
            mListView.setAdapter(madapter);
        }
        else{
            madapter.mData=mListViewCache;
            madapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onPause() {
        Log.e("pasuse", "sdfsdfs");
        super.onPause();
    }

    @Override
    protected void onStop() {
        mApplication.setEditCheckFlag(false);
        super.onStop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            maplist = (HashMap<String, Object>) (data.getSerializableExtra("mListreturn"));
        }
        if (maplist != null) {
            mListViewCache.add(maplist);


            if(madapter == null){
                madapter = new myBaseAdapter(EditCheckActivity.this, mListViewCache, R.layout.seatch_list_submit, new String[]{"drawable", "tv1", "tv2", "tv3", "tv4", "tv5", "et1"}
                        , new int[]{R.id.imageView1, R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5, R.id.submit_text},true);
                mListView.setAdapter(madapter);
            }
            else{
                madapter.mData=mListViewCache;
                madapter.notifyDataSetChanged();
            }
        }
    }

    public  void findviews(){
        SpinnerArray = new ArrayList<Spinner>();
        mListView =(ListView)findViewById(R.id.list1);

        masterDriver=(TextView) findViewById(R.id.spinner1);
        SpinnerArray.add((Spinner)findViewById(R.id.spinner2));
        SpinnerArray.add((Spinner) findViewById(R.id.spinner3));
        SpinnerArray.add((Spinner) findViewById(R.id.spinner4));
        SpinnerArray.add((Spinner) findViewById(R.id.spinner5));
        SpinnerArray.add((Spinner) findViewById(R.id.spinner6));
        gooffTimeText= (TextView)findViewById(R.id.spinner7);
        arriveTimeText= (TextView)findViewById(R.id.spinner8);
        inspectTimeText= (TextView)findViewById(R.id.spinner9);
        undefText= (TextView)findViewById(R.id.spinner10);

        keyNotes = (TextView)findViewById(R.id.keyNotes);
        keyItems = (TextView)findViewById(R.id.keyItems);
        verifyRecords = (TextView)findViewById(R.id.verifyRecords);
        mListView =(ListView)findViewById(R.id.list1);
        addProblemButton =(TextView) findViewById(R.id.add_problem);
    }

    public void setspinerAdapter_action(Spinner sp1,Spinner sp2,Spinner sp3){
        final ArrayAdapter level1=null;
        final ArrayAdapter level2=null;
        final ArrayAdapter level3=null;



        inspectTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("sss", "sdfsdf");
                Calendar c = Calendar.getInstance();
                Dialog dialog = new DatePickerDialog(EditCheckActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month,int day) {
                        inspectTimeText.setText(year + "." + month+"."+day);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
                dialog.show();

            }
        });






        masterDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View driverlayout_tmp = getLayoutInflater().inflate(R.layout.choose_driver,null);
                 final Spinner sp1_= ((Spinner)driverlayout_tmp.findViewById(R.id.sp1));
                final Spinner sp2_= ((Spinner)driverlayout_tmp.findViewById(R.id.sp2));
                final Spinner  sp3_= ((Spinner)driverlayout_tmp.findViewById(R.id.sp3));
                chooseDriverLayout = driverlayout_tmp;
                setSpinners(sp1_,sp2_,sp3_,level1,level2,level3);
                AlertDialog alterDlg = new AlertDialog.Builder(EditCheckActivity.this)
                        .setTitle("请评价本项目：") //设置标题
                        .setView(driverlayout_tmp)//在这一步实现了和资源文件中的selfdialog.xml的关联
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {//设置按钮，以及按钮的事件

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Log.e("ssstgttt",""+sp1_.getSelectedItem()+sp2_.getSelectedItem()+sp3_.getSelectedItem());
                                masterDriver.setText(""+sp1_.getSelectedItem()+"."+sp2_.getSelectedItem()+"."+sp3_.getSelectedItem());

                            }

                        }).setNeutralButton("取消", new DialogInterface.OnClickListener() { //设置按钮，以及按钮的事件
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                // TODO Auto-generated method stub

                            }
                        }).create();//完成对话框的创建
                alterDlg.show();
            }
        });
        String[] trainModel = mApplication.getTrainModel();
        ArrayAdapter<String> _Adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, trainModel);
        SpinnerArray.get(0).setAdapter(_Adapter1);

        String[] checkedLine = mApplication.getCheckedLine();
        ArrayAdapter<String> _Adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, checkedLine);
        SpinnerArray.get(1).setAdapter(_Adapter2);

        String[] trainNumber = mApplication.getTrainNumber();
        ArrayAdapter<String> _Adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, trainNumber);
        SpinnerArray.get(2).setAdapter(_Adapter3);

        String[] gooffStation = mApplication.getGooffStation();
        ArrayAdapter<String> _Adapter4=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, gooffStation);
        SpinnerArray.get(3).setAdapter(_Adapter4);

        String[] arriveStation = mApplication.getArriveStation();
        ArrayAdapter<String> _Adapter5=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arriveStation);
        SpinnerArray.get(4).setAdapter(_Adapter5);
        //set gooffTime & arriveTime

    }
    void setSpinners(final Spinner sp1,final Spinner sp2,final Spinner sp3,ArrayAdapter level1, ArrayAdapter level2,ArrayAdapter level3){
       final ArrayAdapter level1_=level1;
        ArrayAdapter level2_=level2;
        ArrayAdapter level3_=level3;
        int position2;

         level1=new ArrayAdapter<String>(EditCheckActivity.this,android.R.layout.simple_spinner_item,mApplication.getLine1());
        level2=new ArrayAdapter<String>(EditCheckActivity.this,android.R.layout.simple_spinner_item,(mApplication.getLine2())[0]);
        level3=new ArrayAdapter<String>(EditCheckActivity.this,android.R.layout.simple_spinner_item,mApplication.getLine3()[0][0]);

        sp1.setAdapter(level1);
        sp2.setAdapter(level2);
        sp3.setAdapter(level3);

        sp1.setSelection(0, true);
        sp2.setSelection(0, true);
        sp3.setSelection(0, true);


        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                mApplication.setPostiton2(position);
                ArrayAdapter ap = new ArrayAdapter<String>(EditCheckActivity.this, android.R.layout.simple_spinner_item, mApplication.getLine2()[position]);
                sp2.setAdapter(ap);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView,View view,int position,long l) {
                ArrayAdapter ap=new ArrayAdapter<String>(EditCheckActivity.this,android.R.layout.simple_spinner_item,mApplication.getLine3()[mApplication.getPostiton2()][position]);
                sp3.setAdapter(ap);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public String showTimePickerDialog(){
        TimerPickFragment  timePicker = new TimerPickFragment();
        timePicker.show(getFragmentManager(), "timePicker");
        return timePicker.gettime();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_check, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_submmit) {

            HashMap<String,Object> mMap = new HashMap<String,Object>();



            (mMap).put("userid", mApplication.getGlobalInfo("currentUserId"));
            mMap.put("preplanId", ((Spinner) (SpinnerArray.get(0))).getSelectedItem().toString());
            mMap.put("type","ganbu");
            mMap.put("drawable",R.mipmap.magicwand);
            mMap.put("masterDriver", ((masterDriver).getText()).toString());
            mMap.put("trainModel", ((Spinner) (SpinnerArray.get(0))).getSelectedItemId());
            mMap.put("checkedLine",((Spinner) (SpinnerArray.get(1))).getSelectedItemId());
            mMap.put("trainNumber",((Spinner) (SpinnerArray.get(2))).getSelectedItemId());
            mMap.put("gooffStation", ((Spinner) (SpinnerArray.get(3))).getSelectedItemId());
            mMap.put("arriveStation", ((Spinner) (SpinnerArray.get(4))).getSelectedItemId());
            mMap.put("gooffTime", (gooffTimeText.getText().toString()));
            mMap.put("arriveTime",(arriveTimeText.getText().toString()));
            mMap.put("inspectTime", ((TextView) inspectTimeText).getText().toString());
            mMap.put("keyNotes",keyNotes.getText().toString());
            mMap.put("keyItems", keyItems.getText().toString());
            mMap.put("verifyRocords", verifyRecords.getText().toString());


            mListViewCache.set(0,mMap);





            Intent mIntent = new Intent();
            mIntent.putExtra("oneTaskDetails", (Serializable) mListViewCache);
            setResult(RESULT_OK, mIntent);
            finish();
            return true;
        }
        else  if (id==android.R.id.home) {// 点击返回图标事件
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
