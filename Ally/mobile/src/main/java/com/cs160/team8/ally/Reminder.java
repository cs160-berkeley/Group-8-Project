package com.cs160.team8.ally;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Joel on 4/27/16.
 */
public class Reminder extends SugarRecord {
    Patient patient;
    String title;
    int hour;
    int minute;
    List<Integer> days;
    boolean forCaregiver;
    boolean forPatient;
    boolean active;

    // Empty constructor required by SugarORM
    public Reminder() { }

    public Reminder(Patient patient, String title, int hour, int minute, List<Integer> days, boolean forCaregiver,
                    boolean forPatient, boolean active) {
        this.patient = patient;
        this.title = title;
        this.hour = hour;
        this.minute = minute;
        this.days = days;
        this.forCaregiver = forCaregiver;
        this.forPatient = forPatient;
        this.active = active;
    }
}
