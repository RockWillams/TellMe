package com.taichina.xlt.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.taichina.xlt.R;
import com.taichina.xlt.Utils.StaticData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rock on 2015/6/5.
 */
public class taskBaseAdapter extends BaseAdapter {


    public ViewHolder holder;


    public void setmData(ArrayList<ArrayList<HashMap<String, Object>>> mData) {
        this.mData = mData;
    }

    public ArrayList<ArrayList<HashMap<String, Object>>>  mData;

    private int mResource;
    private int mDropDownResource;
    private LayoutInflater mInflater;
    private Context mcontext;
    public final boolean mi;
    StaticData mapplication;


    private ArrayList<HashMap<String,Object>> mUnfilteredData;
    public taskBaseAdapter(Context context, ArrayList<ArrayList<HashMap<String, Object>>>  data, int resource,StaticData application, boolean i){
        mcontext = context;
        mData = data;
        mResource = resource;

        mi = i;
        mapplication = application;

    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mcontext).inflate(mResource,null);
            holder.imageView1 = (ImageView) convertView.findViewById(R.id.imageView1);
            holder.TextView1 = (TextView)convertView.findViewById(R.id.textView1);
            holder.TextView2 = (TextView) convertView.findViewById(R.id.textView2);
            holder.TextView3 = (TextView)convertView.findViewById(R.id.textView3);
            holder.TextView4 = (TextView) convertView.findViewById(R.id.textView4);
            holder.TextView5 = (TextView) convertView.findViewById(R.id.textView5);
            holder.TextView6 = (TextView) convertView.findViewById(R.id.textView6);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        //holder.imageView1.setBackgroundResource((Integer) (mData.get(position).get(0).get("drawable")));
        try {
            holder.TextView1 .setText((mData.get(position).get(0).get("gooffStation")).toString());
        }catch (Exception e){

        }

        try {
            holder.TextView2 .setText((mData.get(position).get(0).get("arriveStation")).toString());
        }catch (Exception e){

        }
        try {
            holder.TextView3.setText((mData.get(position).get(0).get("trainNumber")).toString());
        }catch (Exception e){

        }
        try {
            holder.TextView4.setText((mData.get(position).get(0).get("masterDriver")).toString());
        }catch (Exception e){

        }
        try {
            holder.TextView5.setText((mData.get(position).get(0).get("inspectTime")).toString());
        }catch (Exception e){

        }




        try {
            if((mData.get(position).get(1).get("et1")).toString() != null){
                holder.TextView6.setText((mData.get(position).get(1).get("et1")).toString());
            }
        }catch (Exception e){
            holder.TextView6.setText("空");
        }
        return convertView;

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    static  class ViewHolder{
        public TextView TextView1;
        public TextView TextView2;
        public TextView TextView3;
        public TextView TextView4;
        public TextView TextView5;
        public TextView TextView6;
        public ImageView imageView1;
    }




}
