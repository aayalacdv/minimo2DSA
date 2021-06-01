package com.example.minimo2.responses;

public class Medalla {
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Medalla{" +
                "imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
