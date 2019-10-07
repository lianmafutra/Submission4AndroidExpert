package com.Sunflower.myapplication.Fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Pager extends FragmentStatePagerAdapter {

    int tabCount;


    public Pager(FragmentManager fm, int tabCount) {
        super(fm);

        this.tabCount = tabCount;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Tab_fragMovie tab1 = new Tab_fragMovie();
                return tab1;
            case 1:
                Tab_fragTv tab2 = new Tab_fragTv();
                return tab2;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return tabCount;
    }
}
