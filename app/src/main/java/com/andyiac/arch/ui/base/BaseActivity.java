package com.andyiac.arch.ui.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.andyiac.arch.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;


public abstract class BaseActivity extends AppCompatActivity {


    SystemBarTintManager tintManager;

    protected abstract int provideContentViewId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(provideContentViewId());

        if (Build.VERSION.SDK_INT <= 19) {
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.primary_dark));
        }

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusColor(int color) {
        if (Build.VERSION.SDK_INT > 19) {
            getWindow().setStatusBarColor(color);
        } else {
            tintManager.setStatusBarTintColor(color);
        }
    }
}
