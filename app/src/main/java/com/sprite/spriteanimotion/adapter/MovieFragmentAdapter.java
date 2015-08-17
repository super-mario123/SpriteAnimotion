package com.sprite.spriteanimotion.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dings on 2015/8/5.
 */
public class MovieFragmentAdapter extends FragmentPagerAdapter {

    private final String[] tabTitles = new String[]{"影院热映","经典电影"};
    private Context mContext;
    private List<Fragment> mFragments;

    public MovieFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mFragments = new ArrayList<>();
    }

    public void addFragments(Fragment fragment) {
        if (fragment != null)
            mFragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
