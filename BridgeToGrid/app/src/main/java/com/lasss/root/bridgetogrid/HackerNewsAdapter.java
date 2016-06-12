package com.lasss.root.bridgetogrid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shane on 12/06/16.
 */
public class HackerNewsAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mNewsArticles;

    public HackerNewsAdapter(Context context, List<String> newsArticles) {
        mContext = context;
        mNewsArticles = newsArticles;

    }

    @Override
    public int getCount() {
        return mNewsArticles.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewsArticles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.hacker_news_item, null);
            holder = new ViewHolder();
            holder.headlineLabel = (TextView)convertView.findViewById(R.id.HNArticleTitle);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String title = mNewsArticles.get(position);

        holder.headlineLabel.setText(title);

        return convertView;
    }

    private static class ViewHolder {
        TextView headlineLabel;
    }
}
