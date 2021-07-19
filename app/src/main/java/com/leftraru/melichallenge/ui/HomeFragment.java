package com.leftraru.melichallenge.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;
import com.leftraru.melichallenge.R;
import com.leftraru.melichallenge.viewmodel.ItemDetailViewModel;
import com.leftraru.melichallenge.viewmodel.SearchViewModel;


public class HomeFragment extends Fragment {

    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        configView();
        return root;
    }

    private void configView() {

        setHasOptionsMenu(true);
        TextInputEditText tiet = ((Toolbar) getActivity().findViewById(R.id.toolbar)).findViewById(R.id.tietSearch);
        tiet.setOnClickListener(v -> Navigation.findNavController(root).navigate(R.id.action_nav_home_to_nav_search));

        tiet.setText(getResources().getString(R.string.search_hint));

        getActivity().getViewModelStore().clear();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
    }


}