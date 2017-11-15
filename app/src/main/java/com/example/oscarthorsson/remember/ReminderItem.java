package com.example.oscarthorsson.remember;

import java.time.LocalDate;

/**
 * Created by Oscar Thorsson on 2017-11-13.
 */

class ReminderItem {
    private String name;
    boolean isChecked;

    public ReminderItem(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public String toString() {
        return name + " (" + (isChecked?"checked":"not checked")+ ")";
    }
}
