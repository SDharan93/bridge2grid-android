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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HackerNewsActivity extends AppCompatActivity {

    private static final String TAG = HackerNewsActivity.class.getSimpleName();

    private Boolean recieverIsRegistered = false;
    private List<String> mTitles = new ArrayList<>();
    private ArrayAdapter adapter;

    @BindView(R.id.listHN)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hacker_news);
        ButterKnife.bind(this);
        IntentFilter filer = new IntentFilter();
        filer.addAction("android.provider.Telephony.SMS_RECEIVED");
        filer.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);

        if(!recieverIsRegistered) {
            registerReceiver(messageReciever, filer);
        }

        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mTitles);
        //mListView.setAdapter(adapter);
        preformRequest();
    }

    public void preformRequest() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("6474964388", null, "HACKNEWS:APP: headlines", null, null);
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
            Log.d(TAG, "Got Json Array!");
            for(int j = 0; j < data_messages.length(); j++) {
                JSONObject titleObj = data_messages.getJSONObject(j);
                if(titleObj.has("title")) {
                    mTitles.add(titleObj.getString("title"));
                }
            }
            Log.d(TAG, "Made it out of loop");
            HackerNewsAdapter adapter = new HackerNewsAdapter(this, mTitles);
            //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mTitles);
            mListView.setAdapter(adapter);
            //mListView.setEmptyView(mEmptyTextView);
            ((BaseAdapter) mListView.getAdapter()).notifyDataSetChanged();
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
                Log.e(TAG, "Exception smsRecieve " + e);
            }
        }
    };

    private void registerReceiver() {
        registerReceiver(messageReciever, new IntentFilter());
        recieverIsRegistered = true;
        Log.d(TAG, "Registered the message reciever");
    }
}
