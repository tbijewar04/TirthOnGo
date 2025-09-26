package com.tirthongo.model;
import java.util.List;

public class DestinationModel {
    private String startDestination;
    private String endDestination;
    private String description;
    private List<String> galleryImageUrl;
    private String mainImageUrl;
    private List<String> daysDescription;
    private String pricePerAdult;
    private String bookAmount;
    private String guideName;
    private String adminDocId;

    public String getAdminDocId() {
        return adminDocId;
    }
    public void setAdminDocId(String adminDocId) {
        this.adminDocId = adminDocId;
    }
    public String getStartDestination() {
        return startDestination;
    }
    public void setStartDestination(String startDestination) {
        this.startDestination = startDestination;
    }
    public String getEndDestination() {
        return endDestination;
    }
    public void setEndDestination(String endDestination) {
        this.endDestination = endDestination;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<String> getGalleryImageUrl() {
        return galleryImageUrl;
    }
    public void setGalleryImageUrl(List<String> galleryImageUrl) {
        this.galleryImageUrl = galleryImageUrl;
    }
    public String getMainImageUrl() {
        return mainImageUrl;
    }
    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }
    public List<String> getDaysDescription() {
        return daysDescription;
    }
    public void setDaysDescription(List<String> daysDescription) {
        this.daysDescription = daysDescription;
    }
    public String getPricePerAdult() {
        return pricePerAdult;
    }
    public void setPricePerAdult(String pricePerAdult) {
        this.pricePerAdult = pricePerAdult;
    }
    public String getBookAmount() {
        return bookAmount;
    }
    public void setBookAmount(String bookAmount) {
        this.bookAmount = bookAmount;
    }
    public String getGuideName() {
        return guideName;
    }
    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

}
