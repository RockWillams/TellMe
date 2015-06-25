package com.taichina.xlt.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.taichina.xlt.Adapter.taskBaseAdapter;
import com.taichina.xlt.R;
import com.taichina.xlt.Utils.StaticData;
import com.taichina.xlt.activity.EditCheckActivity;
import com.taichina.xlt.net.GetJsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Handler;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SyncFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SyncFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SyncFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TextView mTextView;
    public boolean online_state;
    public boolean login_state;
    public  ListView tasklistView;
    public  static ArrayList<ArrayList<HashMap<String, Object>>> mLis1;
    public  static ArrayList<HashMap<String, String>> mListsimple  = new  ArrayList<HashMap<String, String>>();
    public  View listItemView;
    public StaticData mApplication;

    taskBaseAdapter mTaskBaseAdapter;
    AlertDialog alertDialog = null;
    AlertDialog taskAlertDialog = null;
    TextView masterDriver;
    TextView gooffTimeText;
    TextView arriveTimeText;
    TextView inspectTimeText;
    TextView undefText;



    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SyncFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SyncFragment newInstance(String param1, String param2) {
        SyncFragment fragment = new SyncFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SyncFragment() {
        // Required empty public constructor
    }

    Handler taskHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mApplication = (StaticData)getActivity().getApplication();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("sss","click ok");
       final View views = inflater.inflate(R.layout.fragment_sync, container, false);
        mTextView = (TextView)views.findViewById(R.id.online_state);
        if(mTextView != null) {
            Log.e("not null","not null");
        }
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View mlayouts = View.inflate(getActivity(), R.layout.login, null);
                Log.e("sss","click ok");

                alertDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("登陆会话")
                        .setView(mlayouts).create();
                mlayouts.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((TextView)(mlayouts.findViewById(R.id.tips))).setText(" ");
                                HashMap<String, String> hashMap = new HashMap<String, String>();
                                hashMap.put("userid", ((EditText) mlayouts.findViewById(R.id.userid)).getText().toString());
                                hashMap.put("passwd", ((EditText) mlayouts.findViewById(R.id.pswd)).getText().toString());
                                if(login(hashMap).get("loginstate") == "success"){
                                    //Toast.makeText(getActivity(),"OKOKOKO",Toast.LENGTH_LONG).show();
                                    JSONObject mJson = new JSONObject();
                                    JSONObject mJsonrRet = new JSONObject();
                                    try {
                                        mJson.put("username",hashMap.get("userid"));
                                        mJson.put("password",hashMap.get("passwd"));
                                        mJson.put("t","test");
                                    }catch (Exception e){
                                        Log.e("Tjq",e.getMessage());
                                    }

                                    GetJsonObject myGetJson =  new GetJsonObject();
                                    mJsonrRet = myGetJson.GetJsonObject(mJson, mApplication.getTargetServerAddr()+"login");
                                    if(mJsonrRet == null){
                                        ((TextView)(mlayouts.findViewById(R.id.tips))).setText("亲，网络没连好喔");

                                    }
                                    else {
                                        try {
                                            int t = mJsonrRet.getInt("t");
                                            if(mJsonrRet.getInt("e")==0?true:false) {
                                                mApplication.setGlobalInfo("loginState","online");
                                                alertDialog.dismiss();
                                                mTextView.setText("在线");
                                                Log.e("Tjq", "log success");


                                            }
                                            else {
                                                Log.e("Tjq", "log failed");
                                                mApplication.setGlobalInfo("loginState","offline");
                                                ((TextView)(mlayouts.findViewById(R.id.tips))).setText("用户名或密码有误喔，再来一次吧");
                                            }


                                        }catch (Exception e){
                                            Log.e("Tjq",e.getMessage());
                                        }

                                    }
                                }
                    }
                });
                mlayouts.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
        mLis1 = new ArrayList<ArrayList<HashMap<String, Object>>> ();
        HashMap<String, Object> mMap1 = new HashMap<String, Object>();
        mMap1.put("drawable", R.mipmap.megaphone);
        mMap1.put("tv1", "上海");
        mMap1.put("tv2", "杭州");
        mMap1.put("tv3", " S008X ");
        mMap1.put("tv4", "王大卫");
        mMap1.put("tv5", "2015年10月1日");
        mMap1.put("et1", "+");

        final  HashMap<String, Object> mMap = new HashMap<String, Object>();
        mMap.put("drawable", R.mipmap.megaphone);
        mMap.put("goffStation", "起点");
        mMap.put("arriveStation", "终点");
        mMap.put("trainNum", " 编号 ");
        mMap.put("masterDriver", "被检查人");
        mMap.put("inspectTime", "今天");
        mMap.put("et1", "+");

        tasklistView = (ListView)views.findViewById(R.id.list_task);
         mTaskBaseAdapter = new taskBaseAdapter(getActivity(),mApplication.dota,R.layout.task_layout,mApplication,false);
        tasklistView.setAdapter(mTaskBaseAdapter);
        tasklistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listItemView = view;
                //下面为设置当前为第几个任务到全局数据中。
                mApplication.setCurrentTask(i);
                Intent intent=new Intent();
                intent.setClass(getActivity(), EditCheckActivity.class);
                startActivityForResult(intent, 0);//这里采用startActivityForResult来做跳转，此处的0为一个依据，可以写其他的值，但一定要>=0
            }
        });
//绿色的添加按键的响应事件
        TextView addtextView = (TextView)views.findViewById(R.id.add_button);
        addtextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //将创建一个新的预想放入全局数据dota中,仅仅包含预想的用户id数据：currentUserId。
                final View mlayoutss = View.inflate(getActivity(), R.layout.tabs_syn, null);
               HashMap<String,Object> tmpHash = retHashMap();
                initMap(tmpHash);
                ArrayList<HashMap<String,Object>> tmpArray = retArrayList();
                tmpArray.add(tmpHash);
                //将新添加的预想的初始数据放入全局数据中
                mApplication.addDota(tmpArray);
                //改变listview的数据并通知更新列表


                ((taskBaseAdapter)(tasklistView.getAdapter())).mData =  mApplication.dota;
                ((taskBaseAdapter)(tasklistView.getAdapter())).notifyDataSetChanged();

            }
        });


        return views;
    }

    public void  initMap(HashMap<String,Object> mMap){
        mMap.put("drawable", R.mipmap.megaphone);
        mMap.put("gooffStation", "起点");
        mMap.put("arriveStation", "终点");
        mMap.put("trainNumber", " 编号 ");
        mMap.put("masterDriver", "被检查人");
        mMap.put("inspectTime", "今天");
        mMap.put("et1", "空");

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
public  HashMap<String,String> login(HashMap<String,String> hm1){
    hm1.put("loginstate", "success");
    return hm1;
}



   public HashMap<String,Object> retHashMap(){
        return  new HashMap<String,Object>();
    }

    public ArrayList<HashMap<String,Object>> retArrayList(){
        return  new  ArrayList<HashMap<String,Object>>();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
        //本次检查的基本信息。
       ArrayList< HashMap<String,Object>>taskResult =( ArrayList< HashMap<String,Object>>)(data.getSerializableExtra("oneTaskDetails"));
       //上传本次任务的提交内容到全局变量。
            mApplication.setDota(mApplication.getCurrentTask(),taskResult);
            ((taskBaseAdapter)(tasklistView.getAdapter())).setmData(mApplication.retDota());
             mTaskBaseAdapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }







    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
