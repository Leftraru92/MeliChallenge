package com.leftraru.melichallenge.repository.retrofit.queries;

import com.leftraru.melichallenge.repository.retrofit.RetrofitUtility;
import com.leftraru.melichallenge.repository.retrofit.interfaces.MeliApi;
import com.leftraru.melichallenge.repository.retrofit.model.SearchResult;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemSearchRepo {

    public MutableLiveData<SearchResult> getItems(String query) {
        final MutableLiveData<SearchResult> searchResult = new MutableLiveData<>();

        MeliApi meliApi = RetrofitUtility.getRetrofitClient().create(MeliApi.class);
        meliApi.getItems(query).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {

                if(response.isSuccessful() && response.body() != null)
                    searchResult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {

            }
        });

        return searchResult;
    }
}
