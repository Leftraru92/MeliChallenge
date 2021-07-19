package com.leftraru.melichallenge.repository.database.queries;

import com.leftraru.melichallenge.repository.database.DbClient;
import com.leftraru.melichallenge.repository.database.model.ItemEntity;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class PreviousSearchesRepo {

    public void insert(String searhKey){
        new Thread(() -> {

            ItemEntity itemEntity = new ItemEntity();
            itemEntity.searchKey = searhKey;
            DbClient.getInstance().getAppDatabase().itemDao().insertAll(itemEntity);
        }).start();
    }

    public MutableLiveData<List<ItemEntity>> getPreviousSearches() {
        final MutableLiveData<List<ItemEntity>> previousSearches = new MutableLiveData<>();
        new Thread(() -> 
                previousSearches.postValue(DbClient.getInstance().getAppDatabase().itemDao().getAll())).start();
        return previousSearches;
    }
}
