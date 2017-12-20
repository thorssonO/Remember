package com.example.oscarthorsson.remember;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

/**
 * Created by Oscar Thorsson on 2017-11-13.
 */

public class ReminderItem {
    private String name;
    boolean isChecked;
    boolean Selected=true;

    public ReminderItem(String name) {
        super();
        this.name = name;
    }

    public String getItemName() {
        return name;
    }

    public boolean isSelected(){
        return Selected;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }

    public void setSelected(boolean selected){
        this.Selected= Selected;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void onClickCheck(){
        // metod som vid tryck på checkKnapp hämtar data från databas och ändrar 0 till 1 på itemStatus tabellen.
        // Svårt att göra utan databas dock så får vänta tills dess...Jag (Clara) gör det då!
    }

    public String toString() {
        return name + " (" + (isChecked?"checked":"not checked")+ ")";
    }
}
