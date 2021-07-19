package com.leftraru.melichallenge.viewmodel;

import com.leftraru.melichallenge.repository.retrofit.queries.ItemSearchRepo;
import com.leftraru.melichallenge.repository.database.queries.PreviousSearchesRepo;
import com.leftraru.melichallenge.repository.database.model.ItemEntity;
import com.leftraru.melichallenge.repository.retrofit.model.SearchResult;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchViewModel extends ViewModel {

    private final ItemSearchRepo itemSearchRepo;
    private final PreviousSearchesRepo previousSearchesRepo;
    private MutableLiveData<SearchResult> searchResult;
    private final MutableLiveData<String> mSearchText;
    MutableLiveData<List<ItemEntity>> previousSearches;

    public SearchViewModel() {
        this.itemSearchRepo = new ItemSearchRepo();
        this.previousSearchesRepo = new PreviousSearchesRepo();
        searchResult = new MutableLiveData<>();
        mSearchText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mSearchText;
    }
    public void setmSearchText(String searchText) {
        setmSearchText(searchText, true);
    }

    public void setmSearchText(String searchText, boolean isNewSearch) {
        mSearchText.setValue(searchText);
        if (!searchText.isEmpty() && isNewSearch)
            previousSearchesRepo.insert(searchText);
    }

    public LiveData<SearchResult> getSearchResult() {
        if (mSearchText.getValue() != null && !mSearchText.getValue().isEmpty() )
            if(searchResult.getValue() == null || !searchResult.getValue().getQuery().equals(mSearchText.getValue()))
            searchResult = itemSearchRepo.getItems(mSearchText.getValue());
        return searchResult;
    }

    public LiveData<List<ItemEntity>> getPreviousSearches() {
        previousSearches = previousSearchesRepo.getPreviousSearches();

        return previousSearches;
    }
}