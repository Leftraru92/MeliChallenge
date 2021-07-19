package com.leftraru.melichallenge.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leftraru.melichallenge.R;
import com.leftraru.melichallenge.adapter.ImageAdapter;
import com.leftraru.melichallenge.repository.retrofit.model.ItemModel;
import com.leftraru.melichallenge.viewmodel.ItemDetailViewModel;
import com.leftraru.melichallenge.viewmodel.SearchViewModel;

import org.json.JSONException;

import java.util.List;

public class ItemDetailFragment extends Fragment {

    private View root;
    private TextView tvName, tvPrice, tvPriceDecimal, tvDescription, tvStock;
    private ViewPager2 viewPager;
    private CardView btQuantity;
    private ItemDetailViewModel itemDetailViewModel;
    private boolean favStatus = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root =  inflater.inflate(R.layout.fragment_item_detail, container, false);
        configView();
        return root;
    }

    private void configView() {
        setHasOptionsMenu(true);

        tvName = root.findViewById(R.id.tvName);
        tvPrice = root.findViewById(R.id.tvPrice);
        tvPriceDecimal = root.findViewById(R.id.tvPriceDecimal);
        tvDescription = root.findViewById(R.id.tvDescription);
        tvStock = root.findViewById(R.id.tvStock);
        viewPager = root.findViewById(R.id.viewPager);
        btQuantity = root.findViewById(R.id.btQuantity);

        btQuantity.setOnClickListener(v -> {});

        itemDetailViewModel = new ViewModelProvider(getActivity()).get(ItemDetailViewModel.class);

        if (getArguments() != null) {
            String itemId = ItemDetailFragmentArgs.fromBundle(getArguments()).getItemId();

            itemDetailViewModel.getDetailResult(itemId).observe(getViewLifecycleOwner(), itemModel -> {

                if (itemModel != null) {
                    setData(itemModel);
                }
            });

            itemDetailViewModel.getItemDescResult(itemId).observe(getViewLifecycleOwner(), descModel -> {

                if (descModel != null && !descModel.getPlain_text().equals("")) {
                    tvDescription.setText(descModel.getPlain_text());
                }
            });
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_fav:
                if (favStatus)
                    item.setIcon(R.drawable.ic_heart_regular);
                else
                    item.setIcon(R.drawable.ic_heart_solid);
                favStatus =  !favStatus;
                return true;
            default:
                return NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(root));
        }
    }

    private void setData(ItemModel itemModel) {
        tvName.setText(itemModel.getTitle());
        tvPrice.setText(itemModel.getPriceFormated());
        tvPriceDecimal.setText(itemModel.getPriceDecimal());
        tvStock.setText(itemModel.getStockFormatted());

        List<ItemModel.Pictures> lImagenes = itemModel.getPrictures();
        ImageAdapter adapter = new ImageAdapter(getContext(), lImagenes);
        viewPager.setAdapter(adapter);
    }
}