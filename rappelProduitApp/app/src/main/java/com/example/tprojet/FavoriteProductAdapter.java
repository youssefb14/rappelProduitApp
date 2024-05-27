package com.example.tprojet;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tprojet.data.FavoriteProduct;
import com.example.tprojet.ui.favorite.FavoriteViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.view.Gravity;
import android.view.MenuItem;
import android.widget.PopupMenu;
import androidx.navigation.Navigation;
import android.os.Bundle;
import android.widget.Toast;


public class FavoriteProductAdapter extends RecyclerView.Adapter<FavoriteProductAdapter.FavoriteProductViewHolder> {

    private List<FavoriteProduct> favoriteProducts = new ArrayList<>();
    private FavoriteViewModel favoriteViewModel; // Ajoutez cette ligne

    public FavoriteProductAdapter(FavoriteViewModel favoriteViewModel) { // Modifiez le constructeur pour inclure FavoriteViewModel
        this.favoriteViewModel = favoriteViewModel;
    }

    public List<FavoriteProduct> getFavoriteProducts() {
        return this.favoriteProducts;
    }

    @NonNull
    @Override
    public FavoriteProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new FavoriteProductViewHolder(itemView);
    }

    public void filter_product_name() {
        Collections.sort(favoriteProducts, new Comparator<FavoriteProduct>() {
            @Override
            public int compare(FavoriteProduct p1, FavoriteProduct p2) {
                return p1.getProductName().compareTo(p2.getProductName());
            }
        });
        notifyDataSetChanged();
    }

    public FavoriteProduct getFavoriteProduct(int index) {
        return favoriteProducts.get(index);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteProductViewHolder holder, int position) {
        FavoriteProduct currentProduct = favoriteProducts.get(position);
        holder.textViewProductName.setText(currentProduct.getProductName());
        holder.textViewRecallDetails.setText(currentProduct.getRecallNature() + ": " + currentProduct.getRecallReason());
        // Charger l'image du produit avec Glide
        Glide.with(holder.itemView.getContext())
                .load(currentProduct.getImageUrl())
                .placeholder(R.drawable.placeholder_image) // Image de substitution en cas de chargement
                .into(holder.imageViewProduct);
        // TODO: Set the more button functionality
    }

    @Override
    public int getItemCount() {
        return favoriteProducts.size();
    }

    public void setFavoriteProducts(List<FavoriteProduct> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
        notifyDataSetChanged();
    }

    class FavoriteProductViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewProductName;
        private ImageView imageViewProduct;
        private TextView textViewRecallDetails;
        private ImageButton btnMore;

        public FavoriteProductViewHolder(View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.text_product_name);
            imageViewProduct = itemView.findViewById(R.id.image_product);
            textViewRecallDetails = itemView.findViewById(R.id.text_recall_details);
            btnMore = itemView.findViewById(R.id.btn_more);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        FavoriteProduct product = favoriteProducts.get(position);
                        // Naviguez vers DetailFavoriteFragment avec le produit comme argument
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("product", product);
                        Navigation.findNavController(v).navigate(R.id.action_favoriteFragment_to_detailFavoriteFragment, bundle);
                    }
                }
            });

            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(v.getContext(), btnMore, Gravity.END);
                    popup.getMenuInflater().inflate(R.menu.context_menu_favorites, popup.getMenu());

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                FavoriteProduct product = favoriteProducts.get(position);
                                if (item.getItemId() == R.id.delete_product) {
                                    favoriteProducts.remove(position);
                                    notifyItemRemoved(position);
                                    favoriteViewModel.deleteFavoriteProduct(product);
                                    return true;
                                } else if (item.getItemId() == R.id.copy_link) {
                                    // recupérer le lien du produit
                                    String link = product.getLienVersLaFicheRappel();

                                    // copier le lien dans le presse-papiers
                                    ClipboardManager clipboard = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                                    ClipData clip = ClipData.newPlainText("link", link);
                                    clipboard.setPrimaryClip(clip);

                                    Toast.makeText(v.getContext(), "Link copied to clipboard", Toast.LENGTH_SHORT).show();
                                    return true;
                                }
                            }
                            return false;
                        }
                    });
                    popup.show();
                }
            });
        }
    }
}