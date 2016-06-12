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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

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
        smsManager.sendTextMessage("6474928225", null, "WEATHER: hourly", null, null);
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
                    Log.d(TAG, messageBody);
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
