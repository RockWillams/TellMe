package com.taichina.xlt.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.taichina.xlt.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/6/5.
 */
public class myBaseAdapter extends BaseAdapter {
    private int[] mTo;
    private String[] mFrom;

   public ViewHolder holder;

    public ArrayList<HashMap<String,Object>> mData;

    private int mResource;
    private int mDropDownResource;
    private LayoutInflater mInflater;
    private Context mcontext;
    public final boolean mi;


    private ArrayList<HashMap<String,Object>> mUnfilteredData;
    public myBaseAdapter(Context context, ArrayList<HashMap<String,Object>> data, int resource, String[] from, int[] to,boolean i){
        mcontext = context;
        mData = data;
        mResource = resource;
        mFrom = from;
        mTo = to;
        mi = i;




    }


    @Override
    public int getCount() {
        return mData.size()-1;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position+1);
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
            holder.EditText1 = (EditText) convertView.findViewById(R.id.submit_text);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }


            holder.imageView1.setBackgroundResource((Integer) (mData.get(position+1).get("drawable")));

            holder.TextView1 .setText((mData.get(position+1).get("tv1")).toString());
            holder.TextView2 .setText((mData.get(position+1).get("tv2")).toString());
            holder.TextView3 .setText((mData.get(position+1).get("tv3")).toString());
            holder.TextView4.setText((mData.get(position+1).get("tv4")).toString());
            holder.TextView5.setText((mData.get(position+1).get("tv5")).toString());
            if(mi){
                holder.EditText1.setVisibility(View.VISIBLE);
                holder.EditText1.setText(mData.get(position+1).get("et1").toString());
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
        public EditText EditText1;
        public ImageView imageView1;
    }

}
