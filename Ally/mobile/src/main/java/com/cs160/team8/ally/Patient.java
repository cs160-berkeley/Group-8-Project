package com.cs160.team8.ally;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Joel on 4/26/16.
 */
public class Patient extends SugarRecord {
    String name;
    int age;
    int locationRadius;

    // Empty constructor required by SugarORM
    public Patient() { }

    public Patient(String name, int age, int locationRadius) {
        this.name = name;
        this.age = age;
        this.locationRadius = locationRadius;
    }

    List<Visitor> getVisitors() {
        String[] id = new String[] { String.valueOf(getId()) };
        return Visitor.find(Visitor.class, "patient = ?", id);
    }

    List<Reminder> getReminders() {
        String[] id = new String[] { String.valueOf(getId()) };
        return Reminder.find(Reminder.class, "patient = ?", id);
    }
}
