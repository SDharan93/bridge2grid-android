package com.lasss.root.bridgetogrid;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/06/16.
 */
public class ChosenCategory {

    List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        categories.add(category);
    }

    public Category returnChosenCategory() {
        Category toReturn = null;
        for (Category c : categories) {
            if (c.isChosen()) {
                if (toReturn == null) {
                    toReturn = c;
                } else {
                    Log.d("Chosen Category", "Multiple chosen categories...");
                }
            }
        }

        return toReturn;
    }

    public void switchCategory(View view) {
        for (Category c : categories) {
            if (c.getView() == view) {
                c.setChosen(true);
            } else {
                c.setChosen(false);
            }
        }
    }

    public void switchCategory(Category category) {
        for (Category c : categories) {
            if (c == category) {
                c.setChosen(true);
            } else {
                c.setChosen(false);
            }
        }
    }
}
