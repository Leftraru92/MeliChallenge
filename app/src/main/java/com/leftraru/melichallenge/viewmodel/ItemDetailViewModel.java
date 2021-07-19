package com.leftraru.melichallenge.viewmodel;

import com.leftraru.melichallenge.repository.retrofit.queries.ItemDetailRepo;
import com.leftraru.melichallenge.repository.retrofit.model.ItemDescModel;
import com.leftraru.melichallenge.repository.retrofit.model.ItemModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemDetailViewModel extends ViewModel {

    private final ItemDetailRepo itemDetailRepo;
    private MutableLiveData<ItemModel> detailResult;
    private MutableLiveData<ItemDescModel> descriptionResult;

    public ItemDetailViewModel() {
        this.itemDetailRepo = new ItemDetailRepo();
        detailResult = new MutableLiveData<>();
        descriptionResult = new MutableLiveData<>();
    }

    public LiveData<ItemModel> getDetailResult(String itemId) {
            detailResult = itemDetailRepo.getItem(itemId);
        return detailResult;
    }

    public LiveData<ItemDescModel> getItemDescResult(String itemId) {
        descriptionResult = itemDetailRepo.getItemDesc(itemId);
        return descriptionResult;
    }


}