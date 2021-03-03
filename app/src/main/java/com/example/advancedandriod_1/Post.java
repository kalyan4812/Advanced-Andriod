package com.example.advancedandriod_1;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int userId;
    private int id;
    private String title;
// we are not passing id because it will be autogenreated.
    public Post(int userId,  String title, String text) {
        this.userId = userId;

        this.title = title;
        this.text = text;
    }

    @SerializedName("body")
    private String text;
    public int getUserId() {
        return userId;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getText() {
        return text;
    }
}
