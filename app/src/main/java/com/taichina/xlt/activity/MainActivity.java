package com.taichina.xlt.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.taichina.xlt.R;
import com.taichina.xlt.fragment.BlankFragment;
import com.taichina.xlt.fragment.CheckInfoFragment;
import com.taichina.xlt.fragment.MonthPlanFragment;
import com.taichina.xlt.fragment.SyncFragment;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    /**
     * ����չʾ��Ϣ��Fragment
     */
    private BlankFragment messageFragment;
    private MonthPlanFragment  checkFragment;
    private CheckInfoFragment checkInfo;
    private SyncFragment syncFragment;

    /**
     * ����չʾ��ϵ�˵�Fragment
     */
    private BlankFragment contactsFragment;

    /**
     * ����չʾ��̬��Fragment
     */
    private BlankFragment newsFragment;

    /**
     * ����չʾ���õ�Fragment
     */
    private BlankFragment settingFragment;

    public View msgLayout0;
    public ImageView msgbtn0;
    public TextView msgtext0;

    public View msgLayout1;
    public ImageView msgbtn1;
    public TextView msgtext1;

    public View msgLayout2;
    public ImageView msgbtn2;
    public TextView msgtext2;

    public View msgLayout3;
    public ImageView msgbtn3;
    public TextView msgtext3;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        fragmentManager = getFragmentManager();
        // ��һ������ʱѡ�е�0��tab
        setTabSelection(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.check_menu, menu);
        return true;
    }

    public void initview() {
        msgLayout0 = findViewById(R.id.message_layout);
        msgbtn0 = (ImageView) findViewById(R.id.message_image);
        msgtext0 = (TextView) findViewById(R.id.message_text);

        msgLayout1 = findViewById(R.id.message1_layout);
        msgbtn1 = (ImageView) findViewById(R.id.message1_image);
        msgtext1 = (TextView) findViewById(R.id.message1_text);

        msgLayout2 = findViewById(R.id.message2_layout);
        msgbtn2 = (ImageView) findViewById(R.id.message2_image);
        msgtext2 = (TextView) findViewById(R.id.message2_text);

        msgLayout3 = findViewById(R.id.message3_layout);
        msgbtn3 = (ImageView) findViewById(R.id.message3_image);
        msgtext3 = (TextView) findViewById(R.id.message3_text);

        msgLayout0.setOnClickListener(this);
        msgLayout1.setOnClickListener(this);
        msgLayout2.setOnClickListener(this);
        msgLayout3.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.message_layout:
                setTabSelection(0);

                break;
            case R.id.message1_layout:
                setTabSelection(1);
                break;
            case  R.id.message2_layout:
                setTabSelection(2);
                break;
            case  R.id.message3_layout:
                setTabSelection(3);
                break;
            default:
                break;
        }
        return;
    }


    public void  setTabSelection(int index) {
        cleanTabSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);

        switch (index) {
            case 0:
                Log.e("test", "texst");
                msgbtn0.setImageResource(R.mipmap.weixin_pressed);
                msgtext0.setTextColor(getResources().getColor(R.color.msgcover));
                if(syncFragment == null){
                    syncFragment = new SyncFragment();
                    transaction.add(R.id.content,syncFragment);
                }
                else transaction.show(syncFragment);

                break;
            case 1:
                msgbtn1.setImageResource(R.mipmap.weixin_pressed);
                msgtext1.setTextColor(getResources().getColor(R.color.msgcover));
                if(checkFragment == null){
                    checkFragment = new MonthPlanFragment();
                    transaction.add(R.id.content,checkFragment);
                }
                else transaction.show(checkFragment);
                break;
            case 2:
                msgbtn2.setImageResource(R.mipmap.weixin_pressed);
                msgtext2.setTextColor(getResources().getColor(R.color.msgcover));
                if(messageFragment == null){
                     messageFragment = new BlankFragment();
                    transaction.add(R.id.content,messageFragment);
                }
                else transaction.show(messageFragment);
                break;
            case 3:
                msgbtn3.setImageResource(R.mipmap.weixin_pressed);
                msgtext3.setTextColor(getResources().getColor(R.color.msgcover));
                if(messageFragment == null){
                     messageFragment = new BlankFragment();
                    transaction.add(R.id.content,messageFragment);
                }
                else transaction.show(messageFragment);
                break;
            default:
                break;

        }
        transaction.commit();
    }
    public void cleanTabSelection(){
        msgbtn0.setImageResource(R.mipmap.weixin_normal);
        msgtext0.setTextColor(getResources().getColor(R.color.fontcolorBlack));
        msgbtn1.setImageResource(R.mipmap.weixin_normal);
        msgtext1.setTextColor(getResources().getColor(R.color.fontcolorBlack));
        msgbtn2.setImageResource(R.mipmap.weixin_normal);
        msgtext2.setTextColor(getResources().getColor(R.color.fontcolorBlack));
        msgbtn3.setImageResource(R.mipmap.weixin_normal);
        msgtext3.setTextColor(getResources().getColor(R.color.fontcolorBlack));
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (syncFragment != null) {
            transaction.hide(syncFragment);
        }
        if (checkInfo != null) {
            transaction.hide(checkInfo);
            Log.e("tt","tt1");
        }
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (checkFragment != null) {
            transaction.hide(checkFragment);
        }
    }


}
