package com.ccjoin.jmumall.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * @author liuzhang
 * @description 配合ViewPager一起使用的FragmentPagerAdapter适配器
 * @date 2016年08月25日 17:07
 */
public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    public List<Fragment> mFragmentList;


    public TabFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }


    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
