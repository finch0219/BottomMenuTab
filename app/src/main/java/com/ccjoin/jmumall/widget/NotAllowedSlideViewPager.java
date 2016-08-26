package com.ccjoin.jmumall.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author liuzhang
 * @description 禁掉ViewPager左右滑动事件
 * @date 2016年08月25日 17:29
 */
public class NotAllowedSlideViewPager extends ViewPager {

    private boolean isScrolled = true;


    public NotAllowedSlideViewPager(Context context) {
        super(context);
    }


    public NotAllowedSlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 设置是否允许ViewPager左右滑动
     */
    public void setScroll(boolean isScrolled) {
        this.isScrolled = isScrolled;
    }


    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isScrolled) {
            return false;
        } else {
            return super.onTouchEvent(ev);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isScrolled) {
            return false;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }


    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }


    @Override public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }
}
