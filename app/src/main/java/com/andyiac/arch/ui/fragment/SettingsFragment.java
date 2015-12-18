package com.andyiac.arch.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.andyiac.arch.R;
import com.andyiac.arch.module.Injector;


public class SettingsFragment extends PreferenceFragment {

    private boolean injected = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity();
        addPreferencesFromResource(R.xml.preference);
        findPreference(getString(R.string.pref_build_time)).setSummary("2015年11月25日");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!injected) {
            injected = true;
            Injector injector = (Injector) getActivity();
            injector.inject(this);
        }
    }
}


