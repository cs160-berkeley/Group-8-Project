package com.cs160.team8.ally;

import java.io.Serializable;

/**
 * Created by KunalPatel on 4/18/16.
 */
public class ProfileInfo implements Serializable {

    //Serializable profile info

    // Used to push text profile info to the watch.

    //Will consider using an alternate implementation that only creates ProfileInfo objects right before sending a Profile to the watch.


    private String name, relationship;
    private int age;

    public ProfileInfo (String name, String relationship, int age) {
        this.name = name;
        this.relationship = relationship;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
