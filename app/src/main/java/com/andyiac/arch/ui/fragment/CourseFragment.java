package com.andyiac.arch.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andyiac.arch.R;
import com.andyiac.arch.adapter.ListAdapterHolder;
import com.andyiac.arch.api.ApiService;
import com.andyiac.arch.data.Courses;
import com.andyiac.arch.data.Universities;
import com.andyiac.arch.model.Course;
import com.andyiac.arch.ui.CourseDetailsActivity;
import com.andyiac.arch.ui.base.InjectableFragment;
import com.andyiac.arch.ui.misc.DividerItemDecoration;
import com.andyiac.arch.utils.L;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observer;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;


public class CourseFragment extends InjectableFragment {

    @Inject
    ApiService apiService;

    @Inject
    Picasso picasso;


    private FragmentActivity mActivity;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ListAdapterHolder adapter;


    private List<Course> coursesList = new ArrayList<>();

    private Map<String, String> universityMap = new HashMap<>();

    Observer<Universities> observerUniversity = new Observer<Universities>() {
        @Override
        public void onCompleted() {
            L.i("on onCompleted");
            setRefreshing(false);
        }

        @Override
        public void onError(Throwable e) {
            L.e("onError");
            setRefreshing(false);
            Toast.makeText(getActivity(), "服务器开了小差稍后重试", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(Universities universities) {
            for (int i = 0; i < universities.getElements().size(); i++) {
                universityMap.put(universities.getElements().get(i).getId(), universities.getElements().get(i).getName());
            }
            adapter.notifyDataSetChanged();
            fetchCoursesData();
        }
    };


    Observer<Courses> observer = new Observer<Courses>() {
        @Override
        public void onCompleted() {
            L.i("on onCompleted");
            setRefreshing(false);
        }

        @Override
        public void onError(Throwable e) {
            L.e("onError");
            setRefreshing(false);
            Toast.makeText(getActivity(), "服务器开了小差稍后重试", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(Courses courses) {
            coursesList.addAll(courses.getElements());
            adapter.notifyDataSetChanged();
            setRefreshing(false);
        }
    };
    private int lastVisibleItem;
    private LinearLayoutManager mLinearLayoutManager;

    public CourseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchDataLogic();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.courses_fragment, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        float paddingStart = getActivity().getResources().getDimension(R.dimen.divider_padding_start);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, paddingStart, false));
        adapter.SetOnItemClickListener(new ListAdapterHolder.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position, Course course, String university) {

                Intent intent = new Intent();
                intent.setClass(mActivity, CourseDetailsActivity.class);
                intent.putExtra("course_details", course);
                intent.putExtra("university_name",university);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        mActivity = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    private void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        //设置卷内的颜色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                coursesList.clear();
                adapter.notifyDataSetChanged();

                fetchDataLogic();
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_courses_recycler_view);
        adapter = new ListAdapterHolder(mActivity, coursesList, picasso, universityMap);

        //下拉刷新
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    setRefreshing(true);
                    fetchDataLogic();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }


    private void fetchDataLogic() {

        if (universityMap.size() > 0) {
            fetchCoursesData();
        } else {
            fetchUniversityData();
        }
    }

    private void fetchUniversityData() {
        setRefreshing(true);
        AppObservable.bindFragment(this, apiService.getUniversityData())
                .map(new Func1<Universities, Universities>() {
                    @Override
                    public Universities call(Universities universities) {
                        return universities;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerUniversity);
    }

    private void fetchCoursesData() {
        setRefreshing(true);
        AppObservable.bindFragment(this, apiService.getCourses())
                .map(new Func1<Courses, Courses>() {
                    @Override
                    public Courses call(Courses courses) {
                        return courses;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    public void setRefreshing(boolean refreshing) {
        if (swipeRefreshLayout == null) {
            return;
        }
        if (!refreshing) {
            swipeRefreshLayout.setRefreshing(false);
        } else {
            swipeRefreshLayout.setRefreshing(true);
        }
    }
}
