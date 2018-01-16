package com.jiefutong.lehfu.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiefutong.lehfu.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 包含ToolBar的Activity基类
 */
public class BaseTitleActivity extends BaseAppCompatActivity {
    private LinearLayout rootLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 这句很关键，注意是调用父类的方法
        super.setContentView(R.layout.activity_base_layout);
        setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        initToolbar();

        setTitleBackVisible(View.VISIBLE);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    public void setTitle(CharSequence title) {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        if (null != tvTitle) {
            tvTitle.setText(title);
        }
    }

    protected void setToolbarBg(int color) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setBackgroundColor(color);
        }

        RelativeLayout rlTitle = (RelativeLayout) findViewById(R.id.titlebar_layout);
        if (rlTitle != null) {
            rlTitle.setBackgroundColor(color);
        }
    }

    protected void setToolbarBg(Drawable drawable) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setBackground(drawable);
        }
        RelativeLayout rlTitle = (RelativeLayout) findViewById(R.id.titlebar_layout);
        if (rlTitle != null) {
            rlTitle.setBackground(drawable);
        }
    }

    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout == null) return;
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }

    /**
     * 设置是否显示toolbar
     *
     * @param isShow
     */
    public void setToolBarVisible(boolean isShow) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void setTitleBackVisible(int visibility) {
        ImageButton back_Btn = (ImageButton) findViewById(R.id.titlebar_back);
        back_Btn.setVisibility(visibility);
        back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
    }

    public void setTitleBackLinstener(View.OnClickListener linstener) {
        ImageButton back_Btn = (ImageButton) findViewById(R.id.titlebar_back);
        back_Btn.setOnClickListener(linstener);
    }


    //显示关闭
    public void setTitleCloseVisible(boolean isShow) {
        TextView back_Btn = (TextView) findViewById(R.id.tv_close);
        back_Btn.setVisibility(isShow ? View.VISIBLE : View.GONE);
        back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
    }

    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent _Intent = new Intent(this, pClass);
        if (pBundle != null) {
            _Intent.putExtras(pBundle);
        }
        startActivity(_Intent);
    }

    protected void openActivityForResult(Class<?> pClass, Bundle pBundle,
                                         int pRequestCode) {
        Intent _Intent = new Intent(this, pClass);
        if (pBundle != null) {
            _Intent.putExtras(pBundle);
        }
        startActivityForResult(_Intent, pRequestCode);
    }

    // 隐藏软键盘
    protected void hideSoftInput(View focusView) {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager.isActive()) {
            inputManager.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
        }
    }

    //弹出软键盘
    protected void showSoftInput(final View focusView) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(focusView, 0);
            }
        }, 200);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
}
