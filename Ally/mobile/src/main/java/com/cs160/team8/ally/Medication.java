package com.cs160.team8.ally;

import java.io.Serializable;

/**
 * Created by KunalPatel on 5/1/16.
 */
public class Medication  implements Serializable{
    public String medicationName;
    public int count;

    public Medication(String medicationName, int count) {
        this.medicationName = medicationName;
        this.count = count;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
