package com.example.tprojet.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "favoriteproduct")
public class FavoriteProduct implements Serializable {
    @PrimaryKey
    @NonNull
    private String recordId;
    private String imageUrl;
    private String productName;
    private String recallNature;
    private String recallReason;
    private String lienVersLaFicheRappel; // Add this line


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRecallNature() {
        return recallNature;
    }

    public void setRecallNature(String recallNature) {
        this.recallNature = recallNature;
    }

    public String getRecallReason() {
        return recallReason;
    }

    public void setRecallReason(String recallReason) {
        this.recallReason = recallReason;
    }

    public String getProductUrl() {
        return lienVersLaFicheRappel;
    }


    public void setProductUrl(String lienVersLaFicheRappel) {
        this.lienVersLaFicheRappel = lienVersLaFicheRappel;
    }

    @NonNull
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(@NonNull String recordId) {
        this.recordId = recordId;
    }



    public String getLienVersLaFicheRappel() {
        return lienVersLaFicheRappel; // Add this line
    }

    public void setLienVersLaFicheRappel(String lienVersLaFicheRappel) { // Add this method
        this.lienVersLaFicheRappel = lienVersLaFicheRappel;
    }


}