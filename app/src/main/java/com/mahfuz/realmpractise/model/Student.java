package com.mahfuz.realmpractise.model;

import io.realm.RealmObject;

/**
 * Created by mahfuz on 1/12/18.
 */

public class Student extends RealmObject {

    private String name;
    private String mark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
