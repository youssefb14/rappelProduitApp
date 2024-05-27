package com.example.tprojet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.tprojet.data.FavoriteProduct;
import com.example.tprojet.ui.favorite.FavoriteViewModel;

public class DetailFavoriteFragment extends Fragment {

    private FavoriteViewModel favoriteViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_detail, container, false);

        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);

        FavoriteProduct product = (FavoriteProduct) getArguments().getSerializable("product");

        TextView textProductName = view.findViewById(R.id.text_product_name);
        textProductName.setText(product.getProductName());

        TextView textNatureJuridiqueDuRappel = view.findViewById(R.id.text_nature_juridique_du_rappel);
        textNatureJuridiqueDuRappel.setText(product.getRecallNature());

        TextView textMotifDuRappel = view.findViewById(R.id.text_motif_du_rappel);
        textMotifDuRappel.setText(product.getRecallReason());

        ImageView imageView = view.findViewById(R.id.image_product);
        Glide.with(this).load(product.getImageUrl()).into(imageView);

        TextView textLienVersLaFicheRappel = view.findViewById(R.id.text_lien_vers_la_fiche_rappel);
        textLienVersLaFicheRappel.setText(product.getProductUrl());


        Button buttonBack = view.findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });

        Button buttonFavorite = view.findViewById(R.id.button_favorite);




        buttonFavorite.setText("Supprimer des favoris");
        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteViewModel.deleteFavoriteProduct(product);
            }
        });

        return view;
    }
}
