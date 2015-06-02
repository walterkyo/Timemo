package com.timemohk.timemo.adapter;

/**
 * Created by walter.lam on 4/2/2015.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.timemohk.timemo.MyApplication;
import com.timemohk.timemo.R;
import com.timemohk.timemo.fragment.GuidingFragment;
import com.viewpagerindicator.IconPagerAdapter;

public class GuidingFragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
    private static final int SPLASH = 1;
    private static final int INTRODUCTION = 2;
    private static final int GET_STARTED = 3;

    protected static final int[] CONTENT = new int[] { SPLASH, INTRODUCTION, GET_STARTED };

    private int mCount = CONTENT.length;

    public GuidingFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GuidingFragment.newInstance(CONTENT[position % CONTENT.length]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return MyApplication.getAppContext().getString(R.string.app_name);
    }

    @Override
    public int getIconResId(int index) {
        return R.drawable.ic_launcher;
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}