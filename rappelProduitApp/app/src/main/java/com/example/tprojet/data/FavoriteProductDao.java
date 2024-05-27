package com.example.tprojet.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFavorite(FavoriteProduct product);

    @Query("SELECT * FROM favoriteproduct")
    LiveData<List<FavoriteProduct>> getFavorites();

    @Query("SELECT COUNT(*) > 0 FROM favoriteproduct WHERE recordId = :productId")
    LiveData<Boolean> isFavorite(String productId);

    @Delete
    void deleteFavorite(FavoriteProduct product);

}