package com.anirayoubil.kidsLearning.data.models;

public class Profile {
    private String name;
    private int age;
    public Profile(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
