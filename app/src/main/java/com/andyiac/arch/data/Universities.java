package com.andyiac.arch.data;

import com.andyiac.arch.model.Partners;

import java.util.List;

/**
 * Created by andyiac on 11/26/15.
 *
 * @web www.andyiac.com
 */
public class Universities  {

    private List<Partners> elements;
    private String paging;
    private String linked;

    public List<Partners> getElements() {
        return elements;
    }

    public void setElements(List<Partners> elements) {
        this.elements = elements;
    }

    public String getPaging() {
        return paging;
    }

    public void setPaging(String paging) {
        this.paging = paging;
    }

    public String getLinked() {
        return linked;
    }

    public void setLinked(String linked) {
        this.linked = linked;
    }

}
