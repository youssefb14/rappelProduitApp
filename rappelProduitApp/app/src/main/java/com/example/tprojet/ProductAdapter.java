package com.example.tprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tprojet.data.Product;
import com.example.tprojet.data.FavoriteProduct;
import com.example.tprojet.ui.favorite.FavoriteViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private List<Product> filteredProductList;
    private FavoriteViewModel favoriteViewModel;

    public ProductAdapter(FavoriteViewModel favoriteViewModel) {
        this.favoriteViewModel = favoriteViewModel;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        this.filteredProductList = new ArrayList<>(productList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.textProductName.setText(product.getProductName());
        holder.textRecallDetails.setText(product.getRecallNature() + ": " + product.getRecallReason());
        Glide.with(holder.itemView.getContext())
                .load(product.getImageUrl())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.imageProduct);
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textProductName;
        TextView textRecallDetails;
        ImageView imageProduct;
        ImageButton btnMore;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textProductName = itemView.findViewById(R.id.text_product_name);
            textRecallDetails = itemView.findViewById(R.id.text_recall_details);
            imageProduct = itemView.findViewById(R.id.image_product);
            btnMore = itemView.findViewById(R.id.btn_more);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Product product = productList.get(position);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PRODUCT_DETAIL", product);

                    Navigation.findNavController(v).navigate(R.id.action_searchFragment_to_detailFragment, bundle);
                }
            });

            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(v.getContext(), btnMore, Gravity.END);
                    popup.getMenuInflater().inflate(R.menu.context_menu, popup.getMenu());

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.add_to_favorites) {
                                int position = getAdapterPosition();
                                Product product = productList.get(position);

                                FavoriteProduct favoriteProduct = new FavoriteProduct();
                                favoriteProduct.setRecordId(product.getRecordId());
                                favoriteProduct.setProductName(product.getProductName());
                                favoriteProduct.setRecallNature(product.getRecallNature());
                                favoriteProduct.setRecallReason(product.getRecallReason());

                                favoriteViewModel.addFavoriteProduct(favoriteProduct);
                                return true;
                            }
                            return false;
                        }
                    });
                    popup.show();
                }
            });
        }
    }
    //
    public void filter(String text) {
        if (productList == null) {
            return;
        }

        if (text.isEmpty()) {
            filteredProductList = new ArrayList<>(productList);
        } else {
            List<Product> filteredList = new ArrayList<>();
            for (Product product : productList) {
                if (product.getProductName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(product);
                }
            }
            filteredProductList = filteredList;
        }
        notifyDataSetChanged();
    }
}