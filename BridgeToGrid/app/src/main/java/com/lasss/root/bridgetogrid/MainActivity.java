package com.lasss.root.bridgetogrid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
