package com.example.tprojet.ui.favorite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tprojet.data.FavoriteProduct;
import com.example.tprojet.data.FavoriteRepository;

import java.util.List;


public class FavoriteViewModel extends AndroidViewModel {
    private final FavoriteRepository repository;
    private LiveData<List<FavoriteProduct>> favoriteProducts;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        repository = new FavoriteRepository(application);
        favoriteProducts = repository.getFavoriteProducts();
    }

    public LiveData<List<FavoriteProduct>> getFavoriteProducts() {
        return favoriteProducts;
    }

    public void refreshFavoriteProducts() {

        repository.refreshFavoriteProducts();
    }

    public LiveData<Void> insertFavoriteProduct(FavoriteProduct favoriteProduct) {
        return repository.insert(favoriteProduct);
    }

    public void deleteFavoriteProduct(FavoriteProduct product) {
        repository.delete(product);
    }

    public void addFavoriteProduct(FavoriteProduct product) {
        insertFavoriteProduct(product);
    }
}