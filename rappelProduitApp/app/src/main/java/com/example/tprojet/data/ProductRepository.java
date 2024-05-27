package com.example.tprojet.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ProductRepository {
    private static final String BASE_URL = "https://data.economie.gouv.fr/";

    private ProductService productService;

    public ProductRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        productService = retrofit.create(ProductService.class);
    }

    public ProductService getProductService() {
        return productService;
    }

    public List<Product> fetchProducts(String query) {
        Call<ApiResponse> call;
        if (query.isEmpty()) {
            // If no search query is provided, get the latest product recalls
            call = productService.getProducts(query, "date_de_publication desc");
        } else {
            call = productService.getProducts(query, null);
        }
        ApiResponse apiResponse = null;
        try {
            apiResponse = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Product> products = new ArrayList<>();
        if (apiResponse != null) {
            for (ApiResponse.Record record : apiResponse.getRecords()) {
                Product product = new Product();
                //ajout des infos du record aux produit
                product.setRecordId(record.getRecordDetail().getId()); // Add this line
                product.setProductName(record.getRecordDetail().getFields().getProductName());
                product.setImageUrl(record.getRecordDetail().getFields().getImageUrl());
                product.setRecallNature(record.getRecordDetail().getFields().getRecallNature());
                product.setRecallReason(record.getRecordDetail().getFields().getRecallReason());
                product.setProductUrl(record.getRecordDetail().getFields().getProductUrl());
                products.add(product);
            }
        }

        return products;
    }
}