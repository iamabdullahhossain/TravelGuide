package com.example.travelguide.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShowPackagesModel implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("image_name")
    @Expose
    private String imageName;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("person")
    @Expose
    private String person;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("food")
    @Expose
    private String food;
    @SerializedName("arrangement")
    @Expose
    private String arrangement;
    @SerializedName("residence")
    @Expose
    private String residence;
    @SerializedName("guides")
    @Expose
    private String guides;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("package_code")
    @Expose
    private String packageCode;
    @SerializedName("admin_code")
    @Expose
    private String adminCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getGuides() {
        return guides;
    }

    public void setGuides(String guides) {
        this.guides = guides;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

}
