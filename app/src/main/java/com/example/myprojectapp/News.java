package com.example.myprojectapp;

public class News {
    private String title;
    private int imageId;
    public News(String title, int imageId){
        this.title = title;
        this.imageId = imageId;
    }
    public String getTitle(){
        return title;
    }
    public int getImageId(){
        return imageId;
    }
}
