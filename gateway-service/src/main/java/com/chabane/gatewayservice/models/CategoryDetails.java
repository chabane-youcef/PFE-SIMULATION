package com.chabane.gatewayservice.models;

import com.chabane.gatewayservice.services.Products.Product;

import java.util.ArrayList;
import java.util.Collection;

public class CategoryDetails {
    private String title;
    private String categoryId;
    private String description;
    private String image;
    private Collection<Product> products = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }
}
