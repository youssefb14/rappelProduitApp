package com.example.tprojet.ui.search;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tprojet.R;
import com.example.tprojet.data.Product;
import com.example.tprojet.ProductAdapter;
import com.example.tprojet.data.ProductRepository;
import com.example.tprojet.ui.favorite.FavoriteViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ProgressBar progressBar;
    private ExecutorService executorService;
    private SearchView searchView;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // initialisation des vues
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);
        searchView = view.findViewById(R.id.search_view);

        // configuration de l'adaptateur et du RecyclerView
        FavoriteViewModel favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productAdapter = new ProductAdapter(favoriteViewModel);
        recyclerView.setAdapter(productAdapter);

        // initialisation de l'ExecutorService
        executorService = Executors.newSingleThreadExecutor();


        // initialisation des données des produits
        getProductData("");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String maxResults = sharedPreferences.getString("max_results", "10");
        String searchCriteria = sharedPreferences.getString("search_criteria", "all");

        // searchView listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getProductData(query);
                productAdapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productAdapter.filter(newText);
                return false;
            }
        });

        return view;
    }

    private void getProductData(String query) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                ProductRepository productRepository = new ProductRepository();
                final List<Product> productList = productRepository.fetchProducts(query);

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            if (productList != null) {
                                // mettre à jour l'adaptateur avec les données des produits
                                productAdapter.setProductList(productList);
                            } else {
                                // gérer les erreurs de réponse
                                Toast.makeText(getContext(), "Erreur: impossible de récupérer les données des produits", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (executorService != null) {
            executorService.shutdown();
        }
    }


}