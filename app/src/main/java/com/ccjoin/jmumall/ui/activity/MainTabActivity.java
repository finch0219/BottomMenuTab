package com.ccjoin.jmumall.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ccjoin.jmumall.R;
import com.ccjoin.jmumall.adapter.TabFragmentPagerAdapter;
import com.ccjoin.jmumall.ui.fragment.TabCategoryFragment;
import com.ccjoin.jmumall.ui.fragment.TabHomeFragment;
import com.ccjoin.jmumall.ui.fragment.TabMeFragment;
import com.ccjoin.jmumall.ui.fragment.TabShoppingCartFragment;
import com.ccjoin.jmumall.widget.TabItemView;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhang
 * @date 2016年07月20日 12:36
 */
public class MainTabActivity extends FragmentActivity implements TabItemView.TabClickListener {

    private static final String TAG = MainTabActivity.class.getSimpleName();
    @BindView(R.id.pager) ViewPager mViewPager;
    @BindView(R.id.tabLayout) View tabLayout;

    private TabItemView mTabHome, mTabCategory, mTabCart, mTabMe, mLastSelectedTab;
    private FragmentManager mFragmentManager;
    private TabFragmentPagerAdapter mTabFragmentPagerAdapter;
    private List<Fragment> mTabFragments = new ArrayList<>();

    private Context mContext;

    private boolean isLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        ButterKnife.bind(this);
        addFragmentToList();
        initViews();
        initTabClickListener();
        mContext = this;

    }


    @Override
    protected void onRestart() {
        super.onRestart();

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
                if (isLogin) {
                    judgeTabSame(mTabMe);
                    mViewPager.setCurrentItem(3, false);
                } else {
                    Intent loginIntent = new Intent(mContext, LoginActivity.class);
                    startActivityForResult(loginIntent, 100);
                    mTabMe.setTabSelected(false);
                }
                break;
            default:
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                isLogin = data.getBooleanExtra("isLogin", false);
                if (isLogin) {
                    judgeTabSame(mTabMe);
                    mViewPager.setCurrentItem(3, false);
                    Toast.makeText(MainTabActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainTabActivity.this, "登录失败，请重新打登录", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
