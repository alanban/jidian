package com.qidianliterature.jidian.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qidianliterature.jidian.model.TodayHot;

import java.util.List;

/**
 * Created by Liang Guan Quan on 2015/8/18.
 */
public class TodayHotListAdapter extends BaseAdapter{
    private List<TodayHot> hots;
    private Context mContext;
    private LayoutInflater inflater;

    public TodayHotListAdapter(List<TodayHot> hots,Context mContext) {
        this.mContext = mContext;
        this.hots = hots;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return hots.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null){
            holder = new Holder();

            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        return convertView;
    }

    public static class Holder{

    }

}
