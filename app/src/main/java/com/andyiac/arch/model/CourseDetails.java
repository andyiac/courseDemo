package com.andyiac.arch.model;


import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by andyiac on 15-11-24.
 *
 * @web http://www.andyiac.com/
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

public class CourseDetails implements Parcelable {

    private String id;
    private String description;
    private String name;
    private String slug;
    private String courseType;
    private String workload;
    private String photoUrl;
    private String universityName;


    protected CourseDetails(Parcel in) {
        id = in.readString();
        description = in.readString();
        name = in.readString();
        slug = in.readString();
        courseType = in.readString();
        workload = in.readString();
        photoUrl = in.readString();
        universityName = in.readString();
    }

    public static final Creator<CourseDetails> CREATOR = new Creator<CourseDetails>() {
        @Override
        public CourseDetails createFromParcel(Parcel in) {
            return new CourseDetails(in);
        }

        @Override
        public CourseDetails[] newArray(int size) {
            return new CourseDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(description);
        dest.writeString(name);
        dest.writeString(slug);
        dest.writeString(courseType);
        dest.writeString(workload);
        dest.writeString(photoUrl);
        dest.writeString(universityName);
    }
}
