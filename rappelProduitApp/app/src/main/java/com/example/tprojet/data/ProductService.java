package com.example.tprojet.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductService {
    @GET("api/explore/v2.0/catalog/datasets/rappelconso0/records")
    Call<ApiResponse> getProducts(@Query("search") String query, @Query("sort") String sort);
}