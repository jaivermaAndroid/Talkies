package com.talkies.model;

public class SearchViewList {

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    String searchTitle;

    public SearchViewList(String searchTitle) {
        this.searchTitle = searchTitle;
    }
}
