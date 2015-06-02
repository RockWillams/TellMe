package com.taichina.xlt.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.taichina.xlt.Adapter.ViewpagerAdapter;



import com.taichina.xlt.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CheckInfo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CheckInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckInfo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public  ViewPager mPager;
    public List<View> listViews;
    List<String> titleContainer;
    public LayoutInflater minflater;



    private ImageView cursor;// ¶¯»­Í¼Æ¬
    private TextView t1, t2, t3;// Ò³¿¨Í·±ê
    private int offset = 0;// ¶¯»­Í¼Æ¬Æ«ÒÆÁ¿
    private int currIndex = 0;// µ±Ç°Ò³¿¨±àºÅ
    private int bmpW;// ¶¯»­Í¼Æ¬¿í¶È

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
    public static CheckInfo newInstance(String param1, String param2) {
        CheckInfo fragment = new CheckInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CheckInfo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.check, container, false);
        minflater = inflater;

        return v;

    }

    @Override
    public void onResume()
    {
        // TODO Auto-generated method stub
        super.onResume();
        InitViewPager();
        System.out.println("ExampleFragment--onResume");
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

                titleContainer.add("ss1");
                titleContainer.add("ss2");
                mPager.setAdapter(new PagerAdapter() {
                    @Override
                    public int getCount() {
                        Log.e("getcount",""+listViews.size());
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
                        Log.e("position"," "+position);
                        (container).addView(listViews.get(position));
                        return listViews.get(position);
                    }

                    @Override
                    public CharSequence getPageTitle(int position) {
                        return titleContainer.get(position);
                    }
                });
                 mPager.setCurrentItem(0);
/*                 mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                     }
                 });*/
             }









}
