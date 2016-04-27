package com.cs160.team8.ally;

import android.graphics.Bitmap;

import com.orm.SugarRecord;

/**
 * Created by Joel on 4/13/16.
 */
public class Visitor extends SugarRecord {
    String name;
    String relationship;
    Bitmap photo;
    Patient patient;
    int age;

    // Empty constructor required by SugarORM
    public Visitor() { }

    public Visitor(String name, Patient patient, String relationship, Bitmap photo, int age) {
        this.name = name;
        this.patient = patient;
        this.relationship = relationship;
        this.photo = photo;
        this.age = age;
    }

    public String firstName() {
        return name.split(" ")[0];
    }
}
