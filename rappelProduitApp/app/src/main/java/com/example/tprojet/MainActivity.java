package com.example.tprojet;

import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.tprojet.data.FavoriteProduct;
import com.example.tprojet.ui.favorite.FavoriteViewModel;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FavoriteViewModel favoriteViewModel;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_searchFragment, R.id.navigation_favoriteFragment, R.id.navigation_configuration)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);


        favoriteViewModel.getFavoriteProducts().observe(this, new Observer<List<FavoriteProduct>>() {
            @Override
            public void onChanged(List<FavoriteProduct> favoriteProducts) {

            }
        });
    }
}