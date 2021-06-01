package com.example.minimo2.responses;


import java.util.ArrayList;

public class Usuario {
    private String imgUrl;
    private String name;
    private ArrayList<Medalla> medallaList;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Medalla> getMedallaList() {
        return medallaList;
    }

    public void setMedallaList(ArrayList<Medalla> medallaList) {
        this.medallaList = medallaList;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "imgUrl='" + imgUrl + '\'' +
                ", name='" + name + '\'' +
                ", medallaList=" + medallaList +
                '}';
    }
}
