package com.example.tprojet.data;

import java.io.Serializable;

public class Product  implements Serializable{
    private String recordId;
    private String productName;
    private String imageUrl;
    private String recallNature;
    private String recallReason;
    private String productUrl;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }




}
