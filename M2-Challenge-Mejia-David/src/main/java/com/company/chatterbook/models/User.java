package com.company.chatterbook.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;

    public User(String userName) {
        this.name = userName;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public List<ChatterPost> chatterPosts;

    public List<ChatterPost> getChatterPosts() {
        return chatterPosts;
    }

    public void setChatterPosts(List<ChatterPost> chatterPosts) {
        this.chatterPosts = chatterPosts;
    }
}

