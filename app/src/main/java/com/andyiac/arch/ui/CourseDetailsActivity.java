package com.andyiac.arch.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ImageView;

import com.andyiac.arch.R;
import com.andyiac.arch.adapter.CourseDetailsListAdapterHolder;
import com.andyiac.arch.model.Course;
import com.andyiac.arch.module.CourseraDetailsModule;
import com.andyiac.arch.ui.base.InjectableActivity;
import com.andyiac.arch.utils.KeyBoardTools;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * @author andyiac
 * @date 15-11-26
 * @web http://www.andyiac.com/
 */
public class CourseDetailsActivity extends InjectableActivity {

    @Inject
    Picasso picasso;

    private RecyclerView mRecyclerView;

    private CourseDetailsListAdapterHolder adapterHolder;

    private Course mCourse;

    @Override
    public List<Object> getModules() {
        return Arrays.<Object>asList(new CourseraDetailsModule());
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.coursera_activity_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mCourse = intent.getParcelableExtra("course_details");
        String university = intent.getStringExtra("university_name");

        adapterHolder = new CourseDetailsListAdapterHolder(this, mCourse, university);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mCourse.getName());

        initView();

        loadBackground();

    }

    private void loadBackground() {
        ImageView iv = (ImageView) findViewById(R.id.course_details_backdrop);
        picasso.load(mCourse.getPhotoUrl()).into(iv);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_courses_details_recycler_view);
        mRecyclerView.setAdapter(adapterHolder);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
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
