package com.sekulicd.emasters.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nsikak  Thompson on 3/11/2017.
 */

public class RepositoryList {

    @SerializedName("items")
    @Expose
    private List<Repository> items = null;

    public List<Repository> getItems() {
        return items;
    }

    public void setItems(List<Repository> items) {
        this.items = items;
    }
}