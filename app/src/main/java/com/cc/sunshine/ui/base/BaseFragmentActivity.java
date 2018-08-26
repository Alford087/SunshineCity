package com.cc.sunshine.ui.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cc.sunshine.R;
import com.cc.sunshine.core.base.Logger;

/**
 * 所有Activity基类
 * Created by TC on 2016/1/26.
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.logd(getClass().getSimpleName(), "onCreate " + getClass().getSimpleName());
        super.onCreate(savedInstanceState);
        boolean result = initDataUponLoadXML(savedInstanceState);
        if (!result) {
            return;
        }
        if (getLayoutResID() > 0) {
            setContentView(getLayoutResID());
            initComponent(savedInstanceState);
        } else {
            Logger.loges(getClass().getSimpleName(), "缺少布局文件");
        }
    }

    /**
     * 初始化actionbar
     */
    public void initActionBar(int title) {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        if (tb != null) {
//            setSupportActionBar(tb);
//            getSupportActionBar().setTitle(title);
        }
    }

    /**
     * 获得Activity代号.
     *
     * @return
     */
    public String getActivityNO() {
        return String.valueOf(getTaskId());
    }

    /**
     * @return
     * @notice:必须返回true，界面方能继续加载.
     */
    public boolean initDataUponLoadXML(Bundle arg0) {
        return true;
    }

    /**
     * 提供layout id，必须返回，否则页面无.
     *
     * @return
     */
    public abstract int getLayoutResID();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化控件.
     */
    public abstract void initComponent(Bundle savedInstanceState);

    /**
     * 页面名字.
     *
     * @return
     */
    public abstract String getThisActivityName();

    /**
     * 是否有内部fragment.
     *
     * @return
     */
    public abstract boolean hasInnerFragment();

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

}
