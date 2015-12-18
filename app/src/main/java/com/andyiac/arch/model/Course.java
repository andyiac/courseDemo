package com.andyiac.arch.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by andyiac on 15-11-24.
 *
 * @web http://blog.andyiac.com/
 */

//       {
//        "id": "v1-228",
//        "description": "For anyone who would like to apply their technical skills to creative work ranging from video games to art installations to interactive music, and also for artists who would like to use programming in their artistic practice.",
//        "name": "数字媒体和移动应用的创意程序设计",
//        "slug": "digitalmedia",
//        "partnerIds": [
//        "26"
//        ],
//        "courseType": "v1.session",
//        "workload": "5-10 hours/week",
//        "photoUrl": "https://d3njjcbhbojbot.cloudfront.net/api/utilities/v1/imageproxy/https://d15cw65ipctsrr.cloudfront.net/24/63a093e763b307dc9420e796aeb06a/GoldComputing3.jpg"
//        }

public class Course  implements Parcelable{

    private String id;
    private String description;
    private String name;
    private String slug;
    private List<Integer> partnerIds;
    private String courseType;
    private String workload;
    private String photoUrl;

    protected Course(Parcel in) {
        id = in.readString();
        description = in.readString();
        name = in.readString();
        slug = in.readString();
        courseType = in.readString();
        workload = in.readString();
        photoUrl = in.readString();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(description);
        parcel.writeString(name);
        parcel.writeString(slug);
        parcel.writeString(courseType);
        parcel.writeString(workload);
        parcel.writeString(photoUrl);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<Integer> getPartnerIds() {
        return partnerIds;
    }

    public void setPartnerIds(List<Integer> partnerIds) {
        this.partnerIds = partnerIds;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public static Creator<Course> getCREATOR() {
        return CREATOR;
    }
}
