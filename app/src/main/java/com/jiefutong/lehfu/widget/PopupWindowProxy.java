package com.jiefutong.lehfu.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.FloatRange;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by chenf on 2017-05-26.
 * 解决popupWindow在7.0机器显示位置不正常问题
 */

public class PopupWindowProxy extends PopupWindow {
    private final boolean isFixAndroidN = Build.VERSION.SDK_INT == 24;
    private final boolean isOverAndroidN = Build.VERSION.SDK_INT > 24;
    private Context mContext;

    public PopupWindowProxy(Context context) {
        super(context);
        init(context);
    }

    public PopupWindowProxy(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PopupWindowProxy(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public PopupWindowProxy(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public PopupWindowProxy(View contentView) {
        super(contentView);
        init(contentView.getContext());
    }

    public PopupWindowProxy(View contentView, int width, int height) {
        super(contentView, width, height);
        init(contentView.getContext());
    }

    public PopupWindowProxy(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
        init(contentView.getContext());
    }

    private void init(Context context) {
        mContext = context;
        setOutsideTouchable(false);
        setFocusable(true);
        setTouchable(true);
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setBackgroundDrawable(new BitmapDrawable());
        //setBackgroundDimEnabled(true);
    }

    private void setBackgroundDimEnabled(boolean enabled) {
        if (enabled) {
            setBackgroundDimAmount(0.5f);
        } else {
            setBackgroundDimAmount(1.0f);
        }
    }

    /**
     * Set Background Dim Value, 1.0 means fully opaque and 0.0 means fully transparent;
     * default 0.5
     * @param dimAmount dim value
     */
    public void setBackgroundDimAmount(@FloatRange(from=0.0, to=1.0) float dimAmount) {
        if (mContext instanceof Activity) {
            Activity activity = (Activity) mContext;
            WindowManager.LayoutParams params = activity.getWindow().getAttributes();
            if (params != null) {
                params.alpha = dimAmount;
                activity.getWindow().setAttributes(params);
            }
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        setBackgroundDimEnabled(false);
    }

    /**
     * fix showAsDropDown when android api ver is over N
     * <p>
     * https://code.google.com/p/android/issues/detail?id=221001
     *
     * @param anchor
     * @param xoff
     * @param yoff
     * @param gravity
     */
    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        if (isFixAndroidN && anchor != null) {
            int[] a = new int[2];
            anchor.getLocationInWindow(a);
            Activity activity = (Activity) anchor.getContext();
            super.showAtLocation((activity).getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, a[1] + anchor.getHeight());
        } else {
            if (isOverAndroidN) {
                setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            super.showAsDropDown(anchor, xoff, yoff, gravity);
        }
    }
}
