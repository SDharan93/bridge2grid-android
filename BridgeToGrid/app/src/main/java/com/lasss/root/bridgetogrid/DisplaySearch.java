package com.lasss.root.bridgetogrid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

public class DisplaySearch extends AppCompatActivity {

    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_search);

        sendButton = (Button) findViewById(R.id.sendButton);
    }

    public void sendMessage(View view) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("6474928225", null, "WIKI-FIND: Mountain Dew", null, null);
    }
}
