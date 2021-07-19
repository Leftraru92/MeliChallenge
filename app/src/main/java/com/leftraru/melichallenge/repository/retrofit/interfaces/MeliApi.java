package com.leftraru.melichallenge.repository.retrofit.interfaces;
import com.leftraru.melichallenge.repository.retrofit.model.ItemDescModel;
import com.leftraru.melichallenge.repository.retrofit.model.ItemModel;
import com.leftraru.melichallenge.repository.retrofit.model.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MeliApi {

    @GET("/sites/MLA/search")
    Call<SearchResult> getItems(@Query("q") String query);

    @GET("/items/{itemId}")
    Call<ItemModel> getItem(@Path(value = "itemId", encoded = true) String itemId);

    @GET("/items/{itemId}/description")
    Call<ItemDescModel> getItemDescription(@Path(value = "itemId", encoded = true) String itemId);

    @GET("/sites/MLA/categories")
    Call<SearchResult> getCategories();

}
