package com.jiefutong.lehfu.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.utils.UIUtils;

import java.util.List;


/**
 * author：hj
 * description:首页底部tab
 */

public class MainTabHost extends LinearLayout {

    private int curtabPosition = 0;

    public MainTabHost(Context context) {
        this(context, null);
    }

    public MainTabHost(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainTabHost(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(HORIZONTAL);
    }

    /**
     * 初始化tab
     *
     * @param tabNameList
     * @param tabImageList
     */
    public void initTabs(List<String> tabNameList, List<Integer> tabImageList) {
        if (isValidateData(tabNameList, tabImageList)) {
            LinearLayout.LayoutParams layoutParams = new LayoutParams(0,
                    ViewGroup.LayoutParams.MATCH_PARENT, 1);
            removeAllViews();
            for (int i = 0; i < tabImageList.size(); i++) {
                final View tabView = UIUtils.inflate(R.layout.item_main_tab);
                final ImageView imageView = (ImageView) tabView.findViewById(R.id.imageview);
                TextView textView = (TextView) tabView.findViewById(R.id.textview);
                imageView.setImageResource(tabImageList.get(i));
                textView.setText(tabNameList.get(i));
                addView(tabView, layoutParams);

                final int finalI = i;
                tabView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setCurSelect(finalI);
                        if (clickItemListener != null) {
                            clickItemListener.onItemClick(finalI);
                        }
                    }
                });
            }

            //默认选中第一项
            setCurSelect(0);
        }
    }


    /**
     * 设置当前选中的item
     *
     * @param position
     */
    public void setCurSelect(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            if (i == position) {
                getChildAt(i).setSelected(true);
            } else {
                getChildAt(i).setSelected(false);
            }
        }
        curtabPosition = position;
    }


    /**
     * 验证数据是否有效
     *
     * @param tabNameList
     * @param tabImageList
     * @return
     */
    private boolean isValidateData(List<String> tabNameList, List<Integer> tabImageList) {
        return tabNameList != null && tabImageList != null
                && tabNameList.size() > 0 && tabNameList.size() == tabImageList.size();
    }


    private OnClickItemListener clickItemListener;

    public void setOnClickItemListener(OnClickItemListener clickItemListener) {
        this.clickItemListener = clickItemListener;
    }


    public interface OnClickItemListener {

        void onItemClick(int position);
    }
    public int getCurtabPosition() {
        return curtabPosition;
    }

    /**
     * 根据tab名称获取tab的index
     * @return
     */
    public int getPositionByTabName(String tabName) {
        for (int i = 0; i < getChildCount(); i++) {
            ViewGroup tabView = (ViewGroup) getChildAt(i);
            TextView textView = (TextView) tabView.getChildAt(1);
            if (textView.getText().toString().trim().equals(tabName)) {
                return i;
            }
        }

        return -1;
    }


    /**
     * 根据index获取tab名称
     * @param index
     */
    public String getTabNameByPosition(int index) {
        if (index >= 0 && index < getChildCount()) {
            ViewGroup tabView = (ViewGroup) getChildAt(index);
            TextView textView = (TextView) tabView.getChildAt(1);
            return textView.getText().toString().trim();
        }
        return "";
    }

    public void setupViewPager(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurSelect(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
