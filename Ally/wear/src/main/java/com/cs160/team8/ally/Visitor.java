package com.cs160.team8.ally;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by Joel on 4/13/16.
 */
public class Visitor implements Serializable {
    private static final long serialVersionUID = 888L;

    String name;
    String relationship;
    // The id of the associated Patient
    long patient;
    int age;
    byte[] photo;

    // Empty constructor required by SugarORM
    public Visitor() { }

    public Visitor(String name, long patient, String relationship, Bitmap photo,
                   int age) {
        this.name = name;
        this.patient = patient;
        this.relationship = relationship;
        this.age = age;

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
        this.photo = stream.toByteArray();
    }

    public String firstName() {
        return name.split(" ")[0];
    }

    public Bitmap getImage() {
        return BitmapFactory.decodeByteArray(photo, 0, photo.length);
    }
}
