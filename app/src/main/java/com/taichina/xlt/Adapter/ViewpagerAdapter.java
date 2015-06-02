package com.taichina.xlt.Adapter;

import android.support.v4.view.PagerAdapter;

import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/6/1.
 */
public class ViewpagerAdapter extends PagerAdapter{
    public List<View> mListViews ;
    public List<String> string ;
    ViewPager pager = null;
    PagerTabStrip tabStrip = null;

    public String TAG = "tag";
    //viewpager中的组件数量
    public  ViewpagerAdapter(List<View> listview,List<String> string){
        this.mListViews = listview;
        this.string = string;

    }
    @Override
    public int getCount() {
        return mListViews.size();
    }
    //滑动切换的时候销毁当前的组件
    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView(mListViews.get(position));
    }
    //每次滑动的时候生成的组件
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mListViews.get(position));
        return mListViews.get(position);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return string.get(position);
    }
}