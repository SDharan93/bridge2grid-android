package com.lasss.root.bridgetogrid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by shane on 12/06/16.
 */
public class HackerNewsAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mNewsArticles = new String[10];

    public HackerNewsAdapter(Context context, String[] newsArticles) {
        mContext = context;
        //mNewsArticles = newsArticles;
        mNewsArticles[1] = "Testing";
        mNewsArticles[2] = "Testing";
        mNewsArticles[3] = "Testing";
        mNewsArticles[4] = "Testing";
        mNewsArticles[5] = "Testing";
        mNewsArticles[6] = "Testing";
        mNewsArticles[7] = "Testing";
    }

    @Override
    public int getCount() {
        return mNewsArticles.length;
    }

    @Override
    public Object getItem(int position) {
        return mNewsArticles[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        mNewsArticles = new String[10];
        mNewsArticles[1] = "Testing";
        mNewsArticles[2] = "Testing";
        mNewsArticles[3] = "Testing";
        mNewsArticles[4] = "Testing";
        mNewsArticles[5] = "Testing";
        mNewsArticles[6] = "Testing";
        mNewsArticles[7] = "Testing";

        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.hourly_list_item, null);
            holder = new ViewHolder();
            holder.headlineLabel = (TextView)convertView.findViewById(R.id.HNArticleTitle);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String title = mNewsArticles[position];

        holder.headlineLabel.setText(title);

        return convertView;
    }

    private static class ViewHolder {
        TextView headlineLabel;
    }
}
