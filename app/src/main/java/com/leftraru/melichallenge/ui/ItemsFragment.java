package com.leftraru.melichallenge.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputEditText;
import com.leftraru.melichallenge.R;
import com.leftraru.melichallenge.adapter.ItemAdapter;
import com.leftraru.melichallenge.viewmodel.SearchViewModel;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


public class ItemsFragment extends Fragment implements ItemAdapter.ItemListener {

    private SearchViewModel searchViewModel;
    private View root;
    private ItemAdapter itemAdapter;
    private ProgressBar pbItems;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_items, container, false);
        configView();
        return root;
    }

    private void configView() {

        setHasOptionsMenu(true);
        TextInputEditText tiet = ((Toolbar) getActivity().findViewById(R.id.toolbar)).findViewById(R.id.tietSearch);
        tiet.setOnClickListener(v -> Navigation.findNavController(root).navigate(R.id.action_nav_items_to_nav_search));

        pbItems = root.findViewById(R.id.pbItems);
        RecyclerView rvItems = root.findViewById(R.id.rvItems);
        itemAdapter = new ItemAdapter(getContext());
        rvItems.setAdapter(itemAdapter);

        searchViewModel = new ViewModelProvider(getActivity()).get(SearchViewModel.class);

        searchViewModel.getText().observe(getViewLifecycleOwner(), s -> {
            if(!s.isEmpty())
                pbItems.setVisibility(View.VISIBLE);
            tiet.setText(s);
        });

        searchViewModel.getSearchResult().observe(getViewLifecycleOwner(), searchResult -> {

            if (searchResult != null && !searchResult.getResults().isEmpty()) {
                itemAdapter.addItems(searchResult.getResults(), this);
                itemAdapter.notifyDataSetChanged();
                pbItems.setVisibility(View.GONE);
            }
        });

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
    }


    @Override
    public void onItemClick(String itemId) {
        Bundle bundle = new Bundle();
        bundle.putString("itemId", itemId);
        Navigation.findNavController(root).navigate(R.id.action_nav_items_to_nav_item_detail, bundle);
    }
}