package com.example.studio.entity;

import jakarta.persistence.*;

@Entity
public class Photo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String slug;
    private double price;
    private String previewUrl;   // public
    private String downloadUrl;  // absolute file path or S3 URL (private)
    private boolean published = true;

    public Photo() {}
    public Photo(String name, String slug, double price, String previewUrl, String downloadUrl, boolean published) {
        this.name = name;
        this.slug = slug;
        this.price = price;
        this.previewUrl = previewUrl;
        this.downloadUrl = downloadUrl;
        this.published = published;
    }

    // getters & setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getSlug(){return slug;}
    public void setSlug(String slug){this.slug = slug;}
    public double getPrice(){return price;}
    public void setPrice(double price){this.price = price;}
    public String getPreviewUrl(){return previewUrl;}
    public void setPreviewUrl(String previewUrl){this.previewUrl = previewUrl;}
    public String getDownloadUrl(){return downloadUrl;}
    public void setDownloadUrl(String downloadUrl){this.downloadUrl = downloadUrl;}
    public boolean isPublished(){return published;}
    public void setPublished(boolean published){this.published = published;}
}
