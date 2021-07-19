package com.leftraru.melichallenge.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.leftraru.melichallenge.R;
import com.leftraru.melichallenge.adapter.SearchAdapter;
import com.leftraru.melichallenge.viewmodel.SearchViewModel;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


public class SearchFragment extends Fragment implements SearchAdapter.SeachListener {

    private SearchViewModel searchViewModel;
    private View root;
    SearchAdapter searchAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_search, container, false);
        configView();

        return root;
    }

    private void configView() {

        setHasOptionsMenu(true);

        searchAdapter = new SearchAdapter(null, this);
        RecyclerView listPrevSearch = root.findViewById(R.id.listPrevSearch);
        listPrevSearch.setAdapter(searchAdapter);

        searchViewModel =
                new ViewModelProvider(getActivity()).get(SearchViewModel.class);

        //searchViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        searchViewModel.getPreviousSearches().observe(getViewLifecycleOwner(), itemEntities ->
                searchAdapter.setItems(itemEntities));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        //muestra el boton expandido
        searchView.setIconified(false);
        //no deja colapsar el botón
        searchView.setOnCloseListener(() -> true);

        searchView.setQuery(searchViewModel.getText().getValue(), false);

        //Escucho las modificaciones y el enviar
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setTextAndNavigate(query, true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText))
                    searchViewModel.setmSearchText("");
                return false;
            }
        });

        /*searchViewModel.getSearchText().observe(getViewLifecycleOwner(), s -> {
            searchView.setQuery(s, false);
        });*/
    }

    private void setTextAndNavigate(String query, boolean isNewSearch) {
        searchViewModel.setmSearchText(query, isNewSearch);
        Navigation.findNavController(root).navigate(R.id.action_nav_search_to_nav_items);
    }


    @Override
    public void onSearchClick(String query) {
        setTextAndNavigate(query, false);
    }
}