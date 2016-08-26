package com.ccjoin.jmumall.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.InflateException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ccjoin.jmumall.R;
import com.ccjoin.jmumall.utils.DensityUtil;

@SuppressWarnings("deprecation")
public class TabItemView extends LinearLayout implements OnClickListener {

    private static final float DEFAULT_TEXT_SIZE = 16f;

    private Context mContext;
    private int mIconBackgroundId;
    private int mTextColor;
    private float mTextSize;
    private int mTabIconSize;
    private int mDefaultColor, mSelectColor;
    private ImageView mTabIcon;
    private TextView mTabText;
    private String mTextString;
    private TabClickListener mClickListener;


    public TabItemView(Context context) {
        this(context, null);
    }


    public TabItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public TabItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init(attrs);
        addView();
    }


    private void init(AttributeSet attrs) {
        this.setOnClickListener(this);
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.TabItemView);
        mIconBackgroundId = ta.getResourceId(R.styleable.TabItemView_contentLogoBack, -1);
        mTextColor = ta.getColor(R.styleable.TabItemView_contentTextColor,
            getResources().getColor(android.R.color.black));
        mTextSize = ta.getDimensionPixelSize(R.styleable.TabItemView_contentTextSize,
            DensityUtil.dp2px(mContext, DEFAULT_TEXT_SIZE));
        mTextString = ta.getString(R.styleable.TabItemView_contentTextString);
        mTabIconSize = ta.getDimensionPixelSize(R.styleable.TabItemView_contentLogoSize,
            LayoutParams.WRAP_CONTENT);
        ta.recycle();
        mDefaultColor = mContext.getResources().getColor(R.color.text_gray_color);
        mSelectColor = mContext.getResources().getColor(R.color.text_red_color);
    }


    private void addView() {
        mTabIcon = new ImageView(mContext);
        mTabIcon.setFocusable(false);
        mTabIcon.setClickable(false);
        LayoutParams logoParams = new LayoutParams(mTabIconSize, mTabIconSize);
        mTabIcon.setLayoutParams(logoParams);

        if (mIconBackgroundId != -1) {
            mTabIcon.setBackgroundResource(mIconBackgroundId);
        } else {
            throw new InflateException("Not set fill image resource");
        }

        this.addView(mTabIcon);

        if (!TextUtils.isEmpty(mTextString)) {
            mTabText = new TextView(mContext);
            mTabText.setFocusable(false);
            mTabText.setClickable(false);
            LayoutParams textParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
            textParams.topMargin = DensityUtil.dp2px(mContext, 0.5f);
            mTabText.setLayoutParams(textParams);
            mTabText.setTextColor(mTextColor);
            mTabText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
            mTabText.setText(mTextString);
            this.addView(mTabText);
        }
    }


    @Override
    public void onClick(View v) {
        setTabSelected(true);
        if (mClickListener != null) {
            mClickListener.onTabClick(this);
        }
    }


    /**
     * 设置点击监听事件
     */
    public void setTabClickListener(TabClickListener listner) {
        this.mClickListener = listner;
    }


    /**
     * 设置填充图片资源
     */
    public void setContentLogoBack(int resourceId) {
        mTabIcon.setBackgroundResource(resourceId);
    }


    /**
     * 设置填充文字
     */
    public void setContentTextString(String text) {
        if (mTabText != null) {
            mTabText.setText(text);
        }
    }


    /**
     * 设置选中状态
     */
    public void setTabSelected(boolean enable) {
        if (mTabIcon != null) {
            mTabIcon.setSelected(enable);
        }
        if (mTabText != null) {
            if (enable) {
                mTabText.setTextColor(mSelectColor);
            } else {
                mTabText.setTextColor(mDefaultColor);
            }
        }
    }


    public interface TabClickListener {
        void onTabClick(View view);
    }

}
