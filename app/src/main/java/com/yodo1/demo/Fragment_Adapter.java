package com.yodo1.demo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * mainPager , adapter
 */
public class Fragment_Adapter extends FragmentPagerAdapter {
    
    private final List<Fragment> fragment;
    private final List<Integer> mainIds;

    public Fragment_Adapter(MainActivity mainActivity, List<Fragment> fragments, List<Integer> mainPagrIds) {
        super(mainActivity.getSupportFragmentManager());
        this.fragment = fragments;
        this.mainIds = mainPagrIds;
    }

    @Override
    public Fragment getItem(int i) {
        return fragment.get(i);
    }

    @Override
    public int getCount() {
        return fragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
