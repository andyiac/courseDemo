package com.andyiac.arch.module;

import com.andyiac.arch.ui.SettingsActivity;
import com.andyiac.arch.ui.fragment.SettingsFragment;

import dagger.Module;


@Module(
        complete = false,
        overrides = true,
        injects = {
                SettingsActivity.class,
                SettingsFragment.class
        }
)
public class SettingsModule {
}
