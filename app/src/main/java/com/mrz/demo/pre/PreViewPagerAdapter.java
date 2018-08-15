package com.mrz.demo.pre;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mrz
 * @date 2018/8/14 17:08
 */
public class PreViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> imageList = new ArrayList<>();

    public PreViewPagerAdapter(FragmentManager fm, List<String> image) {
        super(fm);
        this.imageList = image;
    }

    @Override
    public Fragment getItem(int i) {
        return PreViewContentFragment.newInstant(imageList.get(i));
    }

    @Override
    public int getCount() {
        return imageList.size();
    }
}
