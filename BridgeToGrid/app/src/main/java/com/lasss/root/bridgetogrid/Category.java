package com.lasss.root.bridgetogrid;

import android.view.View;

/**
 * Created by root on 11/06/16.
 */
public class Category {

    View view;
    boolean chosen;

    public Category(View view) {
        this.view = view;
        this.chosen = false;
    }

    public Category(View view, boolean chosen) {
        this.view = view;
        this.chosen = chosen;
    }

    public View getView() {
        return view;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }
}
