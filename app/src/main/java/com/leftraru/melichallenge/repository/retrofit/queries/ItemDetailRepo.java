package com.leftraru.melichallenge.repository.retrofit.queries;

import com.leftraru.melichallenge.repository.retrofit.RetrofitUtility;
import com.leftraru.melichallenge.repository.retrofit.interfaces.MeliApi;
import com.leftraru.melichallenge.repository.retrofit.model.ItemDescModel;
import com.leftraru.melichallenge.repository.retrofit.model.ItemModel;
import com.leftraru.melichallenge.repository.retrofit.model.SearchResult;
import com.leftraru.melichallenge.viewmodel.ItemDetailViewModel;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailRepo {

    public MutableLiveData<ItemModel> getItem(String itemId) {
        final MutableLiveData<ItemModel> detailResult = new MutableLiveData<>();

        MeliApi meliApi = RetrofitUtility.getRetrofitClient().create(MeliApi.class);
        meliApi.getItem(itemId).enqueue(new Callback<ItemModel>() {
            @Override
            public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {

                if(response.isSuccessful() && response.body() != null)
                    detailResult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {

            }
        });

        return detailResult;
    }

    public MutableLiveData<ItemDescModel> getItemDesc(String itemId) {

        final MutableLiveData<ItemDescModel> descriptionResult = new MutableLiveData<>();

        MeliApi meliApi = RetrofitUtility.getRetrofitClient().create(MeliApi.class);
        meliApi.getItemDescription(itemId).enqueue(new Callback<ItemDescModel>() {
            @Override
            public void onResponse(Call<ItemDescModel> call, Response<ItemDescModel> response) {

                if(response.isSuccessful() && response.body() != null)
                    descriptionResult.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ItemDescModel> call, Throwable t) {

            }
        });

        return descriptionResult;
    }
}
