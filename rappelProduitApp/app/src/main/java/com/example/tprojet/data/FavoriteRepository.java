package com.example.tprojet.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class FavoriteRepository {
    private FavoriteProductDao favoriteProductDao;
    private LiveData<List<FavoriteProduct>> favoriteProducts;
    private MutableLiveData<Void> insertResult = new MutableLiveData<>();

    public FavoriteRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        favoriteProductDao = db.favoriteProductDao();
        favoriteProducts = favoriteProductDao.getFavorites();
    }

    public LiveData<List<FavoriteProduct>> getFavoriteProducts() {
        return favoriteProducts;
    }

    public LiveData<Void> insert(FavoriteProduct favoriteProduct) {
        new insertAsyncTask(favoriteProductDao).execute(favoriteProduct);
        return insertResult;
    }

    public void delete(FavoriteProduct product) {
        new deleteAsyncTask(favoriteProductDao).execute(product);
    }

    private static class deleteAsyncTask extends AsyncTask<FavoriteProduct, Void, Void> {
        private FavoriteProductDao asyncTaskDao;

        deleteAsyncTask(FavoriteProductDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FavoriteProduct... params) {
                asyncTaskDao.deleteFavorite(params[0]);
            return null;
        }
    }

    private class insertAsyncTask extends AsyncTask<FavoriteProduct, Void, Void> {
        private FavoriteProductDao asyncTaskDao;

        insertAsyncTask(FavoriteProductDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FavoriteProduct... params) {
            asyncTaskDao.addFavorite(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            insertResult.setValue(aVoid);
        }
    }

    public LiveData<Boolean> isFavorite(int productId) {
        return favoriteProductDao.isFavorite(String.valueOf(productId));
    }

    public void refreshFavoriteProducts() {
        favoriteProducts = favoriteProductDao.getFavorites();
    }
}