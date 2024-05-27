package com.example.tprojet;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.tprojet.data.AppDatabase;
import com.example.tprojet.data.FavoriteProduct;
import com.example.tprojet.data.Product;
import com.example.tprojet.ui.favorite.FavoriteViewModel;

public class DetailFragment extends Fragment {
    private AppDatabase db;
    private FavoriteViewModel favoriteViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(getContext(), AppDatabase.class, "favorite_products").build();
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        Product product = (Product) getArguments().getSerializable("PRODUCT_DETAIL");

        TextView textProductName = view.findViewById(R.id.text_product_name);
        textProductName.setText(product.getProductName());

        TextView textNatureJuridiqueDuRappel = view.findViewById(R.id.text_nature_juridique_du_rappel);
        textNatureJuridiqueDuRappel.setText(product.getRecallNature());

        TextView textMotifDuRappel = view.findViewById(R.id.text_motif_du_rappel);
        textMotifDuRappel.setText(product.getRecallReason());

        //image
        ImageView imageView = view.findViewById(R.id.image_product);
        Glide.with(this).load(product.getImageUrl()).into(imageView);

        Button buttonBack = view.findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });

        Button buttonAddToFavorites = view.findViewById(R.id.button_add_to_favorites);
        buttonAddToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // definir un objet favorite product
                FavoriteProduct favoriteProduct = new FavoriteProduct();
                favoriteProduct.setRecordId(product.getRecordId());
                favoriteProduct.setProductName(product.getProductName());
                favoriteProduct.setRecallNature(product.getRecallNature());
                favoriteProduct.setRecallReason(product.getRecallReason());
                favoriteProduct.setImageUrl(product.getImageUrl());



                favoriteProduct.setProductUrl(product.getProductUrl());


                // ajouter le produit aux favoris
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        favoriteViewModel.addFavoriteProduct(favoriteProduct);
                        Log.d("DetailFragment", "Produit ajouté aux favoris" + favoriteProduct.getRecordId());

                        favoriteViewModel.refreshFavoriteProducts();


                    }
                }).start();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setHomeButtonEnabled(false);
            }
        }
    }
}