package com.leftraru.melichallenge.repository.retrofit.model;

import java.util.List;

public class SearchResult {

    List<ItemsModel> results;
    String query;

    public List<ItemsModel> getResults() {
        return results;
    }

    public String getQuery() {
        return query;
    }
}
