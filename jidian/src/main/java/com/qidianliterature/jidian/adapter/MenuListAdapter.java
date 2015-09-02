package com.qidianliterature.jidian.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qidianliterature.jidian.main.R;
import com.qidianliterature.jidian.util.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liang Guan Quan on 2015/8/8.
 */
public class MenuListAdapter extends BaseAdapter implements View.OnClickListener {

    private static final int[] icons = new int[]{R.drawable.index_icon, R.drawable.care_icon, R.drawable.collection_icon, R.drawable.draft_icon};
    private static final String[] strs = new String[]{"首页", "关注", "收藏", "草稿"};
    private Context mContext;
    private LayoutInflater mInflater;
    private View preView;
    private boolean isFistIn = true;
    private MenuItemClick menuItemClick;
    /**
     * 在Fragment List里，第一个被点击的
     */
    private final int defaultClickedItem = 0;


    public MenuListAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        int count = icons.length > strs.length ? strs.length : icons.length;
        return count;
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
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.menu_adapter_layout, null);
            convertView.setOnClickListener(this);
            holder = new Holder();
            holder.id = position;
            holder.icon = (ImageView) convertView.findViewById(R.id.menu_adapter_icon);
            holder.text = (TextView) convertView.findViewById(R.id.menu_adapter_txt);
            holder.isSelected = false;
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        if (holder.isSelected) {
            convertView.setBackgroundColor(ResourceUtil.getColor(mContext, R.color.clicked_theme_color));
        }

        if(isFistIn == true){
            convertView.setBackgroundColor(ResourceUtil.getColor(mContext, R.color.clicked_theme_color));
            preView = convertView;
            isFistIn = false;
        }

        holder.icon.setImageResource(icons[position]);
        holder.text.setText(strs[position]);

        return convertView;
    }

    private static class Holder {
        int id;
        boolean isSelected;
        ImageView icon;
        TextView text;
    }

    /**
     * 相应Item被点击时候改变样式的事件
     */
    private void updateItemStyle(View v){
        if (preView != null)
            preView.setBackgroundColor(ResourceUtil.getColor(mContext, R.color.theme_color));

        v.setBackgroundColor(ResourceUtil.getColor(mContext, R.color.clicked_theme_color));
        preView = v;
    }

    @Override
    public void onClick(View v) {
        updateItemStyle(v);

        if (menuItemClick != null) {
            //菜单Item点击回调
            menuItemClick.onMenuItemClick(((Holder) v.getTag()).id);
        }
    }

    public void setItemClick(int which){
        if(menuItemClick != null){
            menuItemClick.onMenuItemClick(which);
        }
    }

    public void setMenuItemClick(MenuItemClick menuItemClick) {
        if(menuItemClick != null) {
            this.menuItemClick = menuItemClick;
            //初始化被点击的Item
            setItemClick(defaultClickedItem);
        }else{
            throw new NullPointerException("From MenuListAdapter : MenuItemClick Can not be NULL!");
        }
    }

    public interface MenuItemClick {
        /**
         * @param which 哪一个MenuItem被点击了
         */
        public void onMenuItemClick(int which);
    }

}
