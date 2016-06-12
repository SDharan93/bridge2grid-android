package com.lasss.root.bridgetogrid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = WeatherActivity.class.getSimpleName();

    private Boolean recieverIsRegistered = false;

    @BindView(android.R.id.list)
    ListView mListView;

    @BindView(android.R.id.empty)
    TextView mEmptyTextView;

    @BindView(R.id.currentTemp)
    TextView mCurrentTemp;

    @BindView(R.id.tempDate)
    TextView mCurrentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        IntentFilter filer = new IntentFilter();
        filer.addAction("android.provider.Telephony.SMS_RECEIVED");
        filer.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);

        if(!recieverIsRegistered) {
            registerReceiver(messageReciever, filer);
        }

        preformRequest();
    }

    public void preformRequest() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("6474964388", null, "WEATHER:APP: hourly", null, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!recieverIsRegistered) {
            registerReceiver();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(recieverIsRegistered) {
            unregisterReceiver(messageReciever);
            recieverIsRegistered = false;
        }
    }

    private void parseResponseMessage(String message) throws JSONException {
        JSONArray data_messages = new JSONArray(message);
        WeatherHour[] hours = new WeatherHour[24];
        for(int i = 0; i < data_messages.length(); i++) {
            JSONObject jsonHour = data_messages.getJSONObject(i);
            String summary = jsonHour.getString("summary");
            Log.d(TAG, summary);
            WeatherHour hour = new WeatherHour();
            for(int j = 0; j < 24; j++) {
                hour.setTime(j);
                String temperature = jsonHour.getString(j+1+"Hr");
                double temp_num = Double.parseDouble(temperature);
                hour.setTemperature(temp_num);

                hours[j] = hour;
            }
        }
        HourWeatherAdapter adapter = new HourWeatherAdapter(this, hours);
        mListView.setAdapter(adapter);
        mListView.setEmptyView(mEmptyTextView);
    }

    private BroadcastReceiver messageReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Weather Activity: Recieved the message");
            List<String> fullMessage = new ArrayList<>();
            final Bundle bundle = intent.getExtras();

            try {
                if (bundle != null) {
                    final Object[] pdusObj = (Object[]) bundle.get("pdus");
                    Log.d(TAG, "In OnReciever");
                    for (int i = 0; i < pdusObj.length; i++) {
                        SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                        fullMessage.add(msgs[i].getDisplayMessageBody());
                        }
                    String messageBody = "";
                    for (String message : fullMessage) {
                        messageBody += message;
                    }
                    //Log.d(TAG, messageBody);
                    String shortMessage = messageBody.substring(38);
                    Log.d(TAG,"message recieved from response" + shortMessage);
                    parseResponseMessage(shortMessage);
                }
            } catch (Exception e) {
                Log.e(TAG, "Exception smsRecieve");
            }
        }
    };

    private void registerReceiver() {
        registerReceiver(messageReciever, new IntentFilter());
        recieverIsRegistered = true;
        Log.d(TAG, "Registered the message reciever");
    }
}
