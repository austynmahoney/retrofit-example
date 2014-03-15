package com.austynmahoney.retrofitexample.app.model;

public class Repository {

    public String id;
    public User user;
    public String name;
    public String description;

    @Override
    public String toString() {
        return "Repository{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
