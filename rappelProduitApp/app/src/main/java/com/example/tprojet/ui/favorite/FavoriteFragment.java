package com.example.tprojet.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tprojet.FavoriteProductAdapter;
import com.example.tprojet.R;
import com.example.tprojet.data.FavoriteProduct;
import com.example.tprojet.ui.favorite.FavoriteViewModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FavoriteFragment extends Fragment {
    private FavoriteViewModel favoriteViewModel;
    private FavoriteProductAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 est le nombre de colonnes
        recyclerView.setHasFixedSize(true);
        setHasOptionsMenu(true);

        adapter = new FavoriteProductAdapter(favoriteViewModel);
        recyclerView.setAdapter(adapter);

        // Observe changes in the favorite products list
        favoriteViewModel.getFavoriteProducts().observe(getViewLifecycleOwner(), new Observer<List<FavoriteProduct>>() {
            @Override
            public void onChanged(List<FavoriteProduct> favoriteProducts) {
                adapter.setFavoriteProducts(favoriteProducts);
            }
        });

        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_filter_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.filter_product_name) {
            //filtre par nom de produit
            Collections.sort(adapter.getFavoriteProducts(), new Comparator<FavoriteProduct>() {
                @Override
                public int compare(FavoriteProduct p1, FavoriteProduct p2) {
                    return p1.getProductName().compareTo(p2.getProductName());
                }
            });
            adapter.notifyDataSetChanged();
            return true;
        } else if (id == R.id.filter_recall_nature) {
            // filtre par nature de rappel
            Collections.sort(adapter.getFavoriteProducts(), new Comparator<FavoriteProduct>() {
                @Override
                public int compare(FavoriteProduct p1, FavoriteProduct p2) {
                    return p1.getRecallNature().compareTo(p2.getRecallNature());
                }
            });
            adapter.notifyDataSetChanged();
            return true;
        } else if (id == R.id.filter_recall_reason) {
            // filtrer par raison de rappel
            Collections.sort(adapter.getFavoriteProducts(), new Comparator<FavoriteProduct>() {
                @Override
                public int compare(FavoriteProduct p1, FavoriteProduct p2) {
                    return p1.getRecallReason().compareTo(p2.getRecallReason());
                }
            });
            adapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }
}