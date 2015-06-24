package com.taichina.xlt.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.taichina.xlt.R;
import com.taichina.xlt.activity.AddItemActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.taichina.xlt.Adapter.myBaseAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CheckInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CheckInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View v;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ViewPager mPager;
    public PagerTitleStrip mPagerTitleStrip;
    public PagerTabStrip mPagerTabStrip;
    public List<View> listViews;
    public ListView mListView;
    public ArrayList<HashMap<String, Object>> mListViewCache;
    List<String> titleContainer;
    public LayoutInflater minflater;
    public static Context mContext;
    public ArrayList<HashMap<String, Object>> maplist;
    public myBaseAdapter mSimpleAdapter;
    public List<String> edittextret = new ArrayList<String>();
    public int count = 0;


    private ImageView cursor;// ����ͼƬ
    private TextView t1, t2, t3;// ҳ��ͷ��
    private int offset = 0;// ����ͼƬƫ����
    private int currIndex = 0;// ��ǰҳ�����
    private int bmpW;// ����ͼƬ���

    public String TAG = "tag";


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckInfoFragment newInstance(String param1, String param2, Context context) {
        CheckInfoFragment fragment = new CheckInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        ListView mListView;
        mContext = context;
        return fragment;
    }

    public CheckInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            Log.e("ss33", "onCreate");
        }
        mListViewCache = new ArrayList<HashMap<String, Object>>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.check, container, false);
        minflater = inflater;
        setHasOptionsMenu(true);
        Log.e("ss33", "onCreateView");

        return v;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_menu) {


            Intent intent = new Intent();
            intent.setClass(getActivity(), AddItemActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", "111");
            intent.putExtras(bundle);
            intent.putExtra("id", "222");
            startActivityForResult(intent, 1);
        }
        if (item.getItemId() == R.id.submit_menu) {
            Log.e("ss33", "已经保存" );
            Toast.makeText(getActivity(),"已经保存",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            maplist = (ArrayList<HashMap<String, Object>>) data.getSerializableExtra("mListreturn");
        }

    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        InitViewPager();



        if (maplist != null) {
            Log.e("ss33", "onResume" + count);
            mListViewCache.addAll(maplist);
            Log.e("ss32323ss009", mListViewCache.get(0).get("et1").toString());
            mListView = (ListView)listViews.get(1).findViewById(R.id.list1);
              if(true){
                    mSimpleAdapter = new myBaseAdapter(getActivity(), mListViewCache, R.layout.seatch_list_submit, new String[]{"drawable", "tv1", "tv2", "tv3", "tv4", "tv5", "et1"}
                            , new int[]{R.id.imageView1, R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5, R.id.submit_text},true);
                    //mListView = (ListView)getActivity().findViewById(R.id.list1);
                    mListView.setAdapter(mSimpleAdapter);
                    mPager.setCurrentItem(1);
                }
                else if(mSimpleAdapter != null){
                    mSimpleAdapter.mData=mListViewCache;
                    mSimpleAdapter.notifyDataSetChanged();
                    Log.e("1111", "列表刷新"+mListViewCache.toString());
                    mPager.setCurrentItem(1);
                }


        }




    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
       /* try {
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


  /*  views.findViewById(R.id.basic_info4).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (spinner1 == null) {
                views.findViewById(R.id.sss1).setVisibility(View.VISIBLE);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getData());
                ((Spinner) (views.findViewById(R.id.spinner))).setAdapter(adapter);


            }


        }
    });
*/

    private List<String> getData(int i) {
        // 数据源
        List<String> dataList = new ArrayList<String>();
        dataList.add("北京");
        dataList.add("上海");
        dataList.add("南京");
        dataList.add("宜昌");
        return dataList;
    }

    public boolean add_passenger(View view1,Spinner spinner,int spinner_num ){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getData(spinner_num));
        String ss = "R.id.spinner"+spinner_num;
        //view1.findViewById(ss);
        return true;
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
    private void InitViewPager() {
        mPager = (ViewPager) getActivity().findViewById(R.id.vPager);
                listViews = new ArrayList<View>();
                titleContainer = new ArrayList<String>();
              //  LayoutInflater mInflater =getActivity().getLayoutInflater();
                 listViews.add(minflater.inflate(R.layout.checkinfo, null));
                 listViews.add(minflater.inflate(R.layout.checksubmit, null));
                listViews.get(0).findViewById(R.id.basic_info).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(listViews.get(0).findViewById(R.id.myTable).getVisibility() == View.VISIBLE){
                            listViews.get(0).findViewById(R.id.myTable).setVisibility(View.GONE);
                        }
                        else listViews.get(0).findViewById(R.id.myTable).setVisibility(View.VISIBLE);

                    }
                });
                titleContainer.add("检查任务");
                titleContainer.add("反馈任务");


                mPager.setAdapter(new PagerAdapter() {
                    @Override
                    public int getCount() {

                        return listViews.size();
                    }

                    @Override
                    public void destroyItem(ViewGroup container, int position, Object object) {
                        container.removeView(listViews.get(position));
                    }

                    @Override
                    public boolean isViewFromObject(View view, Object object) {
                        return object == view;
                    }

                    @Override
                    public Object instantiateItem(ViewGroup container, int position) {

                                (container).addView(listViews.get(position));

                        return listViews.get(position);
                    }

                    @Override
                    public CharSequence getPageTitle(int position) {
                        return titleContainer.get(position);
                    }

                    @Override
                    public int getItemPosition(Object object) {

                        return super.getItemPosition(object);
                    }
                });
                 mPager.setCurrentItem(0);
                mPager.setOnPageChangeListener(new OnPageChangeListener() {
                    @Override
                    public void onPageScrollStateChanged(int arg0) {
                        Log.d(TAG, "--------changed:" + arg0);
                    }

                    @Override
                    public void onPageScrolled(int arg0, float arg1, int arg2) {
                        Log.d(TAG, "-------scrolled arg0:" + arg0);
                        Log.d(TAG, "-------scrolled arg1:" + arg1);
                        Log.d(TAG, "-------scrolled arg2:" + arg2);
                    }

                    @Override
                    public void onPageSelected(int arg0) {
                        Log.d(TAG, "------selected:" + arg0);
                        if (arg0 == 1) {
                            // getActionBar().hide;
                        }
                    }
                });
             }


}
