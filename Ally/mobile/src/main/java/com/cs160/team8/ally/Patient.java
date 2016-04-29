package com.cs160.team8.ally;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.orm.SugarRecord;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by Joel on 4/26/16.
 */
public class Patient extends SugarRecord {
    String name;
    byte[] photo;
    int locationRadius;

    // Empty constructor required by SugarORM
    public Patient() { }

    public Patient(String name, Bitmap photo, int locationRadius) {
        this.name = name;
        this.locationRadius = locationRadius;

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
        this.photo = stream.toByteArray();
    }

    List<Visitor> getVisitors() {
        String[] id = new String[] { String.valueOf(getId()) };
        return Visitor.find(Visitor.class, "patient = ?", id);
    }

    List<Reminder> getReminders() {
        String[] id = new String[] { String.valueOf(getId()) };
        return Reminder.find(Reminder.class, "patient = ?", id);
    }

    public Bitmap getImage() {
        return BitmapFactory.decodeByteArray(photo, 0, photo.length);
    }

    public String firstName() {
        return name.split(" ")[0];
    }

    public String lastName() {
        return name.split(" ")[1];
    }

    public String abbreviatedName() {
        String firstName = firstName();
        String lastName = lastName();
        return String.format("%s %s.", firstName, lastName.substring(0, 1));
    }
}
