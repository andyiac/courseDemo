package com.andyiac.arch.module;

import com.andyiac.arch.ui.MainActivity;
import com.andyiac.arch.ui.base.InjectableActivity;
import com.andyiac.arch.ui.fragment.CourseFragment;

import dagger.Module;



@Module(
        complete = false,
        injects = {
                MainActivity.class,
                CourseFragment.class
        }
)
public class HomeModule {
    InjectableActivity activity;

    public HomeModule() {
    }

    public HomeModule(InjectableActivity activity) {
        this.activity = activity;
    }
}
