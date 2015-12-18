/*
 * Copyright (C) 2014 VenomVendor <info@VenomVendor.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.andyiac.arch.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andyiac.arch.R;
import com.andyiac.arch.data.CourseKV;
import com.andyiac.arch.model.Course;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CourseDetailsListAdapterHolder extends RecyclerView.Adapter<CourseDetailsListAdapterHolder.ViewHolder> {

    OnItemClickListener mItemClickListener;
    private FragmentActivity mActivity;

    private Course mCourse;
    private List<CourseKV> mCourseList = new ArrayList<>();

    private Picasso picasso;

    public CourseDetailsListAdapterHolder(FragmentActivity mActivity, Course courses, String university) {
        this.mActivity = mActivity;

        this.mCourseList.add(new CourseKV("Course name", courses.getName()));
        this.mCourseList.add(new CourseKV("Course description", courses.getDescription()));
        this.mCourseList.add(new CourseKV("Workload", courses.getWorkload()));
        this.mCourseList.add(new CourseKV("University", university));
        this.mCourseList.add(new CourseKV("Course type", courses.getCourseType()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.course_details_single_list_item, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvName.setText(mCourseList.get(position).getKey().trim());
        holder.tvValue.setText(mCourseList.get(position).getValue());


    }

    @Override
    public int getItemCount() {
        return mCourseList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName, tvValue;
        ImageView ivIcon;

        public ViewHolder(View view) {
            super(view);

            tvName = (TextView) view.findViewById(R.id.course_details_key);
            tvValue = (TextView) view.findViewById(R.id.course_details_value);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }
}
