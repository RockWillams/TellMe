package com.taichina.xlt.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.taichina.xlt.R;
import com.taichina.xlt.Adapter.myBaseAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class AddItemActivity extends ActionBarActivity {
    ArrayList<HashMap<String,Object>> mList = new ArrayList<HashMap<String, Object>>();

    ListView mListView;
    public  boolean[] cache = new  boolean[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        HashMap<String, Object> mMap = new HashMap<String, Object>();
        mMap.put("drawable", R.mipmap.keyboard);
        mMap.put("tv1", "安全意识->");
        mMap.put("tv2", "出勤预想");
        mMap.put("tv3", "  重要");
        mMap.put("tv4", "1：根据天气（冰，雪，雾，风），区段（单线，闭塞方式）、时段、车型展开预想");
        mMap.put("tv5", "ST-0192");
        mMap.put("et1", "+");
        mList.add(mMap);

        HashMap<String, Object> mMap1 = new HashMap<String, Object>();
        mMap1.put("drawable", R.mipmap.lens);
        mMap1.put("tv1", "精神面貌->");
        mMap1.put("tv2", "呼唤应答");
        mMap1.put("tv3", "  一般");
        mMap1.put("tv4", "1：型号不错呼，变化及时呼（调车型号由远及近，依次确认），主体信号不漏呼");
        mMap1.put("tv5", "ST-019");
        mMap1.put("et1", "+");
        mList.add(mMap1);
        HashMap<String, Object> mMap2 = new HashMap<String, Object>();
        mMap2.put("drawable", R.mipmap.lightbulb);
        mMap2.put("tv1", "应急处置->");
        mMap2.put("tv2", "信息回报");
        mMap2.put("tv3", " 重要 ");
        mMap2.put("tv4", "1：采取措施正确、果断，严禁臆断行车");
        mMap2.put("tv5", "ST-008");
        mMap2.put("et1", "+");
        mList.add(mMap2);

        HashMap<String, Object> mMap3 = new HashMap<String, Object>();
        mMap3.put("drawable", R.mipmap.magicwand);
        mMap3.put("tv1", "质量控制->");
        mMap3.put("tv2", "巡检作业");
        mMap3.put("tv3", "一般 ");
        mMap3.put("tv4", "1：注意劳动人身安全");
        mMap3.put("tv5", "LT-009");
        mMap3.put("et1", "+");
        mList.add(mMap3);

        HashMap<String, Object> mMap4 = new HashMap<String, Object>();
        mMap4.put("drawable", R.mipmap.locked);
        mMap4.put("tv1", "调车作业->");
        mMap4.put("tv2", "计划传达");
        mMap4.put("tv3", " 重要 ");
        mMap4.put("tv4", "机班共同听取调车指挥人传达作业重点");
        mMap4.put("tv5", "JB-001");
        mMap4.put("et1", "+");
        mList.add(mMap4);

        HashMap<String, Object> mMap5 = new HashMap<String, Object>();
        mMap5.put("drawable", R.mipmap.loading);
        mMap5.put("tv1", "调车作业->");
        mMap5.put("tv2", "变更计划");
        mMap5.put("tv3", " 一般 ");
        mMap5.put("tv4", "1：不超三沟口头布置");
        mMap5.put("tv5", "BG-012");
        mMap5.put("et1", "+");
        mList.add(mMap5);

        HashMap<String, Object> mMap6 = new HashMap<String, Object>();
        mMap6.put("drawable", R.mipmap.mic);
        mMap6.put("tv1", "调车作业->");
        mMap6.put("tv2", "调车控速");
        mMap6.put("tv3", " 重要 ");
        mMap6.put("tv4", "1：接近被连挂车辆时，使用听觉信号辅助");
        mMap6.put("tv5", "TC-042");
        mMap6.put("et1", "+");
        mList.add(mMap6);

        HashMap<String, Object> mMap7 = new HashMap<String, Object>();
        mMap7.put("drawable", R.mipmap.megaphone2);
        mMap7.put("tv1", "调车作业->");
        mMap7.put("tv2", "溜放作业");
        mMap7.put("tv3", " 一般 ");
        mMap7.put("tv4", "1：强迫加速");
        mMap7.put("tv5", "LF-042");
        mMap7.put("et1", "+");
        mList.add(mMap7);

        HashMap<String, Object> mMap8 = new HashMap<String, Object>();
        mMap8.put("drawable", R.mipmap.megaphone);
        mMap8.put("tv1", "平稳操纵->");
        mMap8.put("tv2", "换向操作");
        mMap8.put("tv3", " 一般 ");
        mMap8.put("tv4", "1：车位停稳，LKJ速度未到零严禁换向");
        mMap8.put("tv5","HX-001");
        mMap8.put("et1", "+");
        mList.add(mMap8);


        mListView = (ListView) findViewById(R.id.search_list);
        mListView.setAdapter(new myBaseAdapter(this, mList, R.layout.seatch_list_submit, new String[]{"drawable", "tv1", "tv2", "tv3", "tv4", "tv5", "et1"}
                , new int[]{R.id.imageView1, R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5, R.id.submit_text},false));
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("ss++++", "position =" + position);
                final int mPosition = position;
                final View mlayout = getLayoutInflater().inflate(R.layout.dialog,(ViewGroup)findViewById(R.id.dialog));
                ((TextView)mlayout.findViewById(R.id.textView1)).setText(mList.get(mPosition).get("tv1").toString());
                ((TextView)mlayout.findViewById(R.id.textView2)).setText(mList.get(mPosition).get("tv2").toString());
                ((TextView)mlayout.findViewById(R.id.textView3)).setText(mList.get(mPosition).get("tv3").toString());
                ((TextView)mlayout.findViewById(R.id.textView4)).setText(mList.get(mPosition).get("tv4").toString());
                ((TextView)mlayout.findViewById(R.id.textView5)).setText(mList.get(mPosition).get("tv5").toString());
                ((ImageView)mlayout.findViewById(R.id.imageView1)).setBackgroundResource((Integer) mList.get(mPosition).get("drawable"));

                AlertDialog alterDlg = new AlertDialog.Builder(AddItemActivity.this)
                        .setTitle("请评价本项目：") //设置标题
                        .setView(mlayout)//在这一步实现了和资源文件中的selfdialog.xml的关联
                        .setPositiveButton("提交", new DialogInterface.OnClickListener() {//设置按钮，以及按钮的事件

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                mList.get(mPosition).put("et1", ((EditText) mlayout.findViewById(R.id.userName)).getText().toString());

                                Intent mIntent = new Intent();
                                mIntent.putExtra("mListreturn", (Serializable)(mList.get(mPosition)));
                                setResult(RESULT_OK, mIntent);
                                finish();
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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("返回");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id==android.R.id.home) {// 点击返回图标事件
            this.finish();
        }

        //noinspection SimplifiableIfStatement
/*        if (id == R.id.action_settings1) {
            Log.e("缓存已选", "s");
            for (int i = 0; i < 100; i++) {

                if (cache[i] == true) {
                    mListretrun.add(mList.get(i));
                }

            }
            Intent mIntent = new Intent();
            mIntent.putExtra("mListreturn",(Serializable)mListretrun);
            setResult(RESULT_OK, mIntent);
            finish();
        }
        else if (id == R.id.action_settings)
            Log.e("提交已选","t");*/

        return super.onOptionsItemSelected(item);
    }
}
