package com.example.tprojet.ui.configuration;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tprojet.R;
import com.example.tprojet.databinding.FragmentConfigurationBinding;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationFragment extends Fragment {
    private FragmentConfigurationBinding binding;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        configurationViewModel configurationViewModel =
                new ViewModelProvider(this).get(configurationViewModel.class);

        binding = FragmentConfigurationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize the RecyclerView
        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<SelectorItem> myData = new ArrayList<>();


        String[] productCategories = {"Tous les produits", "Catégorie 1", "Catégorie 2"};
        myData.add(new SelectorItem("Catégorie de produit", productCategories, "Tous les produits"));

        String[] dates = {"Aujourd'hui", "Cette semaine", "Ce mois", "Cette année"};
        myData.add(new SelectorItem("Date de début", dates, "Aujourd'hui"));

        String[] compensations = {"Oui", "Non"};
        myData.add(new SelectorItem("Compensation", compensations, "Non"));

        String[] maxResultsOptions = {"10", "8", "6", "5", "4"};
        myData.add(new SelectorItem("Nombre de résultats", maxResultsOptions, "10"));



        SelectorAdapter selectorAdapter = new SelectorAdapter(myData);
        recyclerView.setAdapter(selectorAdapter);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String maxResults = sharedPreferences.getString("max_results", "10");
        String searchCriteria = sharedPreferences.getString("search_criteria", "all");
        String startDate = sharedPreferences.getString("start_date", "");
        String endDate = sharedPreferences.getString("end_date", "");
        String productCategory = sharedPreferences.getString("product_category", "all");
        boolean compensation = sharedPreferences.getBoolean("compensation", false);



        configurationViewModel.getText().observe(getViewLifecycleOwner(), s -> {

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}