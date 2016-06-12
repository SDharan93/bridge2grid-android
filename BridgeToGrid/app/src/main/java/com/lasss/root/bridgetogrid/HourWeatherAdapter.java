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
public class HourWeatherAdapter extends BaseAdapter {
    private Context mContext;
    private WeatherHour[] mHourData;

    public HourWeatherAdapter(Context context, WeatherHour[] hourData) {
        mContext = context;
        mHourData = hourData;
    }

    @Override
    public int getCount() {
        return mHourData.length;
    }

    @Override
    public Object getItem(int position) {
        return mHourData[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.hourly_list_item, null);
            holder = new ViewHolder();
            holder.iconImageView = (ImageView)convertView.findViewById(R.id.iconImage);
            holder.timeLabel = (TextView)convertView.findViewById(R.id.timeLabel);
            holder.itemTemp = (TextView)convertView.findViewById(R.id.itemTemp);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        WeatherHour hour = mHourData[position];

        holder.iconImageView.setImageResource(hour.getIconId());
        holder.itemTemp.setText(hour.getTemperatureCelcius() + "");
        holder.timeLabel.setText(hour.getTime()+"");

        return convertView;
    }

    private static class ViewHolder {
        ImageView iconImageView; //public by default
        TextView itemTemp;
        TextView timeLabel;
    }
}
