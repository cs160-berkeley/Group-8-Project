package com.cs160.team8.ally;

import android.graphics.Bitmap;

/**
 * Created by Joel on 4/13/16.
 */
public class Profile {
    String name;
    String relationship;
    Bitmap photo;
    int age;

    public Profile(String name, String relationship, Bitmap photo, int age) {
        this.name = name;
        this.relationship = relationship;
        this.photo = photo;
        this.age = age;
    }
}
