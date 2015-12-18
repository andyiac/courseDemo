package com.andyiac.arch.data;

import com.andyiac.arch.model.Course;

import java.util.List;

/**
 * Created by andyiac on 11/25/15.
 * @web www.andyiac.com
 */
public class Courses {

    private List<Course> elements;
    private Paging paging;
    private String linked;


    public List<Course> getElements() {
        return elements;
    }

    public void setElements(List<Course> elements) {
        this.elements = elements;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public String getLinked() {
        return linked;
    }

    public void setLinked(String linked) {
        this.linked = linked;
    }

    class Paging{
        private String next;
        private int total;

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}
