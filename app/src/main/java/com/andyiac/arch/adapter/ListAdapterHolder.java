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
import com.andyiac.arch.model.Course;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class ListAdapterHolder extends RecyclerView.Adapter<ListAdapterHolder.ViewHolder> {

    OnItemClickListener mItemClickListener;
    private FragmentActivity mActivity;
    private List<Course> mCourses;
    private Picasso picasso;
    private Map<String, String> universityMap;

    public ListAdapterHolder(FragmentActivity mActivity, List<Course> courses, Picasso picasso, Map<String, String> universityMap) {
        this.mActivity = mActivity;
        this.mCourses = courses;
        this.picasso = picasso;
        this.universityMap = universityMap;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.course_single_list_item, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvUniversityName.setText(universityMap.get(mCourses.get(position).getPartnerIds().get(0).toString()));
        holder.tvName.setText(mCourses.get(position).getName().trim());
        holder.tvWorkload.setText(mCourses.get(position).getWorkload());
        picasso.load(mCourses.get(position).getPhotoUrl()).into(holder.ivIcon);
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Course course, String university);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName, tvUniversityName, tvWorkload;
        ImageView ivIcon;

        public ViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.course_name);
            tvUniversityName = (TextView) view.findViewById(R.id.course_university_name);
            tvWorkload = (TextView) view.findViewById(R.id.course_workload);
            ivIcon = (ImageView) view.findViewById(R.id.course_icon);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition(), mCourses.get(getPosition()),
                        universityMap.get(mCourses.get(getPosition()).getPartnerIds().get(0).toString()));
            }
        }

    }

}
