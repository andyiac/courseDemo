package com.andyiac.arch.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.andyiac.arch.R;
import com.andyiac.arch.module.SettingsModule;
import com.andyiac.arch.ui.base.InjectableActivity;
import com.andyiac.arch.utils.KeyBoardTools;
import com.andyiac.arch.utils.L;

import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends InjectableActivity {

    @Override
    public List<Object> getModules() {
        return Arrays.<Object>asList(new SettingsModule());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            KeyBoardTools.actionKey(KeyEvent.KEYCODE_BACK);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
