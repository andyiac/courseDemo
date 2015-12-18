package com.andyiac.arch.api;

import com.andyiac.arch.data.Courses;
import com.andyiac.arch.data.Universities;

import retrofit.http.GET;
import rx.Observable;

/**
 * RxJava Style
 */
public interface ApiService {

    //https://api.coursera.org/api/courses.v1?fields=photoUrl,workload,description,partnerIds
    @GET("/api/courses.v1?fields=photoUrl,workload,description,partnerIds")
    Observable<Courses> getCourses();

    @GET("/api/partners.v1")
    Observable<Universities> getUniversityData();

}
