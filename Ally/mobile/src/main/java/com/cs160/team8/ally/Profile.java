package com.cs160.team8.ally;

import android.graphics.Bitmap;

import java.io.Serializable;


public class Profile implements Serializable {
    public ProfileInfo profileInfo;
    Bitmap photo;
    int age;

    public Profile(String name, String relationship, Bitmap photo, int age) {
        this.profileInfo = new ProfileInfo(name, relationship, age);
        this.age = age;
        this.photo = photo;
    }

    public ProfileInfo getProfileInfo() {
        return profileInfo;
    }

    public void setProfileInfo(ProfileInfo profileInfo) {
        this.profileInfo = profileInfo;
    }


    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
