package com.epicodus.chattle.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;

public class DropdownAdapter extends BaseAdapter implements SpinnerAdapter {
    @Override
    public int getCount() {
        //firebase list of users
        return 0; //users.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
