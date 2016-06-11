package com.lasss.root.bridgetogrid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button search, wikipedia, sports, recipies, stocks, news;
    private EditText searchBar;
    private ChosenCategory chosenCategory = new ChosenCategory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (Button) findViewById(R.id.searchButton);

        searchBar = (EditText) findViewById(R.id.searchBar);

        wikipedia = (Button) findViewById(R.id.wikipediaButton);
        sports = (Button) findViewById(R.id.sportsButton);
        recipies = (Button) findViewById(R.id.recipiesButton);
        stocks = (Button) findViewById(R.id.stocksButton);
        news = (Button) findViewById(R.id.newsButton);

        Category wikipediaCat = new Category(wikipedia, true);
        Category sportsCat = new Category(sports);
        Category recipiesCat = new Category(recipies);
        Category stocksCat = new Category(stocks);
        Category newsCat = new Category(news);

        chosenCategory.addCategory(wikipediaCat);
        chosenCategory.addCategory(sportsCat);
        chosenCategory.addCategory(recipiesCat);
        chosenCategory.addCategory(stocksCat);
        chosenCategory.addCategory(newsCat);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            requestPermissions();
        }
    }

    private void requestPermissions() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int hasReceieveSmsPermission = checkSelfPermission(Manifest.permission.RECEIVE_SMS);
            int hasSmsReadPermission = checkSelfPermission(Manifest.permission.READ_SMS);
            int hasSmsSendPermission = checkSelfPermission(Manifest.permission.SEND_SMS);
            List<String> permissions = new ArrayList<>();

            // check if the permissions are already granted
            if( hasReceieveSmsPermission != PackageManager.PERMISSION_GRANTED ) {
                permissions.add( Manifest.permission.RECEIVE_SMS );
            }

            if( hasSmsReadPermission != PackageManager.PERMISSION_GRANTED ) {
                permissions.add( Manifest.permission.READ_SMS );
            }

            if( hasSmsSendPermission != PackageManager.PERMISSION_GRANTED ) {
                permissions.add( Manifest.permission.SEND_SMS );
            }

            if( !permissions.isEmpty() ) {
                //private static final int REQUEST_SMS_HANDLE_PERMISSIONS = 1;
                requestPermissions( permissions.toArray( new String[permissions.size()] ), 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch ( requestCode ) {
            case 1: {
                for( int i = 0; i < permissions.length; i++ ) {
                    if( grantResults[i] == PackageManager.PERMISSION_GRANTED ) {
                        Log.d( "Permissions", "Permission Granted: " + permissions[i] );
                    } else if( grantResults[i] == PackageManager.PERMISSION_DENIED ) {
                        Log.d( "Permissions", "Permission Denied: " + permissions[i] );
                    }
                }
            }
            break;
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    public void changeCategory(View view) {
        chosenCategory.switchCategory(view);
    }

    public void performSearch(View view) {
        String searchTerm = searchBar.getText().toString();
        View chosenView = chosenCategory.returnChosenCategory().getView();

        if (chosenView == wikipedia) {

        } else if (chosenView == sports) {

        } else if (chosenView == recipies) {

        } else if (chosenView == news) {

        } else if (chosenView == stocks) {

        } else {
            Log.d("Main Class", "Should not be getting here...");
        }

        Intent intent = new Intent(this, DisplaySearch.class);
        startActivity(intent);
    }
}
