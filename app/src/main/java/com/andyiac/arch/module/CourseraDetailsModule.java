package com.andyiac.arch.module;

import com.andyiac.arch.ui.CourseDetailsActivity;
import com.andyiac.arch.ui.base.InjectableActivity;
import com.andyiac.arch.ui.fragment.CourseFragment;

import dagger.Module;

/**
 * @author andyiac
 * @web http://www.andyiac.com/
 */

@Module(
        complete = false,
        injects = {
                CourseDetailsActivity.class,
                CourseFragment.class
        }
)
public class CourseraDetailsModule {
    InjectableActivity activity;

    public CourseraDetailsModule() {
    }

    public CourseraDetailsModule(InjectableActivity activity) {
        this.activity = activity;
    }
}
