package com.lasss.root.bridgetogrid;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shane on 12/06/16.
 */
public class WeatherHour {
    private int mTime;
    private String mSummary;
    private double mTemperature;
    private double mTemperatureCelcius;
    private String mIcon;
    private String mTimezone;
    private int mIconId;

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int iconId) {
        mIconId = iconId;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(int time) {
        mTime = time;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }

    public double getTemperatureCelcius() {
        return mTemperatureCelcius;
    }

    public void setTemperatureCelcius(double temperatureCelcius) {
        mTemperatureCelcius = temperatureCelcius;
    }
}
