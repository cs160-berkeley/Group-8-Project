package com.cs160.team8.ally;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Joel on 4/13/16.
 */
public class Profile implements Serializable {
    String name;
    String relationship;
    ProfileInfo profileInfo;
    Bitmap photo;
    int age;

    public Profile(String name, String relationship, Bitmap photo, int age) {
        this.profileInfo = new ProfileInfo(name, relationship, age);
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getRelationship() {
        return relationship;

    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfileInfo getProfileInfo() {
        return profileInfo;
    }

    public void setProfileInfo(ProfileInfo profileInfo) {
        this.profileInfo = profileInfo;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
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
