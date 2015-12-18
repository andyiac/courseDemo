package com.andyiac.arch.model;


/**
 * Created by andyiac on 11/26/15.
 *
 * @web www.andyiac.com
 */

//        {
//        "id": "45",
//        "name": "香港中文大学",
//        "shortName": "cuhk"
//        },

public class Partners {

    private String id;
    private String name;
    private String shortName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
