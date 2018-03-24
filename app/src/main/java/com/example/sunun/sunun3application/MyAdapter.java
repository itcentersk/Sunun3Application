package com.example.sunun.sunun3application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<DataModel> mDatas;
    private LayoutInflater mLayoutInflater;
    Context mContext;
    private String iconBaseUrl = "http://itpart.com/android/json/img/"; // เพิ่มใหม่

    public MyAdapter(Context context, List<DataModel> aList) {
        mDatas = aList;
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }


    static class ViewHolder {
        TextView tvTitle;
        TextView tvDesc;
        ImageView img;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.row_layout,viewGroup,false);
            holder = new ViewHolder();
            holder.tvTitle = (TextView)view.findViewById(R.id.txtTitle);
            holder.tvDesc = (TextView)view.findViewById(R.id.txtDescription);
            holder.img = (ImageView)view.findViewById(R.id.icon);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        String title = mDatas.get(position).getTitle();
        holder.tvTitle.setText(title);
        holder.tvDesc.setText(mDatas.get(position).getTitle());
     // เพิ่มใหม่
        String imgUrl = iconBaseUrl +mDatas.get(position).getImg();

        Picasso.with(mContext)
                .load(imgUrl)
                .placeholder(R.mipmap.ic_launcher).fit()
                .error(R.mipmap.ic_launcher)
                .into(holder.img);

        return view;
    }
}
