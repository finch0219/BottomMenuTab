package com.ccjoin.jmumall.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ccjoin.jmumall.R;
import com.ccjoin.jmumall.adapter.TabFragmentPagerAdapter;
import com.ccjoin.jmumall.fragment.TabCategoryFragment;
import com.ccjoin.jmumall.fragment.TabHomeFragment;
import com.ccjoin.jmumall.fragment.TabMeFragment;
import com.ccjoin.jmumall.fragment.TabShoppingCartFragment;
import com.ccjoin.jmumall.widget.TabItemView;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhang
 * @date 2016年07月20日 12:36
 */
public class MainTabActivity extends FragmentActivity implements TabItemView.TabClickListener {

    @BindView(R.id.pager) ViewPager mViewPager;
    @BindView(R.id.tabLayout) View tabLayout;

    private TabItemView mTabHome, mTabCategory, mTabCart, mTabMe, mLastSelectedTab;
    private FragmentManager mFragmentManager;
    private TabFragmentPagerAdapter mTabFragmentPagerAdapter;
    private List<Fragment> mTabFragments = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        ButterKnife.bind(this);
        addFragmentToList();
        initViews();
        initTabClickListener();
    }


    public void addFragmentToList() {
        mFragmentManager = this.getSupportFragmentManager();
        mTabFragments.clear();
        mTabFragments.add(new TabHomeFragment());
        mTabFragments.add(new TabCategoryFragment());
        mTabFragments.add(new TabShoppingCartFragment());
        mTabFragments.add(new TabMeFragment());
        mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(mFragmentManager, mTabFragments);
        mViewPager.setAdapter(mTabFragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(mTabFragments.size() - 1);
        mViewPager.setCurrentItem(0, false);
    }


    private void initViews() {
        mTabHome = ButterKnife.findById(tabLayout, R.id.tab_home);
        mTabCategory = ButterKnife.findById(tabLayout, R.id.tab_category);
        mTabCart = ButterKnife.findById(tabLayout, R.id.tab_cart);
        mTabMe = ButterKnife.findById(tabLayout, R.id.tab_me);
        mTabHome.setTabSelected(true);
        mLastSelectedTab = mTabHome;
    }


    private void initTabClickListener() {
        mTabHome.setTabClickListener(this);
        mTabCategory.setTabClickListener(this);
        mTabCart.setTabClickListener(this);
        mTabMe.setTabClickListener(this);
    }


    @Override
    public void onTabClick(View view) {
        switch (view.getId()) {
            case R.id.tab_home:
                judgeTabSame(mTabHome);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.tab_category:
                judgeTabSame(mTabCategory);
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.tab_cart:
                judgeTabSame(mTabCart);
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.tab_me:
                judgeTabSame(mTabMe);
                mViewPager.setCurrentItem(3, false);
                break;
        }
    }


    private void judgeTabSame(TabItemView clickTab) {
        if (mLastSelectedTab != clickTab) {
            mLastSelectedTab.setTabSelected(false);
            mLastSelectedTab = clickTab;
            mLastSelectedTab.setTabSelected(true);
        }
    }
}
