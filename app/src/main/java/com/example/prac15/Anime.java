package com.example.prac15;

import com.google.gson.annotations.SerializedName;

public class Anime {
    @SerializedName("id_Manga")
    private int id;
    @SerializedName("manga_Name")
    private String name;
    @SerializedName("manga_Detail")
    private String detail;

    @SerializedName("manga_Img")
    private String img;

    @SerializedName("student_Fio")
    private String fio;

    @SerializedName("student_Score")
    private int score;

    public Anime(int id, String name, String detail, String img, String fio, int score) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.img = img;
        this.fio = fio;
        this.score = score;
    }

    public Anime(String name, String detail, String img, String fio, int score) {
        this.name = name;
        this.detail = detail;
        this.img = img;
        this.fio = fio;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
