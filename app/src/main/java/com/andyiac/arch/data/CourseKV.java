package com.andyiac.arch.data;

/**
 * for repos details
 *
 * @author andyiac
 * @date 15-9-20
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */

public class CourseKV {
    private String key;
    private String value;

    public CourseKV(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
