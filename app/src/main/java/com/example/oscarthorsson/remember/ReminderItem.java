package com.example.oscarthorsson.remember;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

/**
 * Created by Oscar Thorsson on 2017-11-13.
 */

class ReminderItem {
    private String name;
    boolean isChecked;
    boolean Selected=true;

    public ReminderItem(String name //boolean isChecked
    ) {
        super();
        this.name = name;
        //this.isChecked = isChecked;
    }

    public String getItemName() {
        return name;
    }

    public boolean isSelected(){
        return Selected;
    }

    public void setSelected(boolean selected){
        this.Selected= Selected;
    }

    public boolean isChecked() {
        /*String query = "select item_Status";
        Statement ItemS= null;
        ResultSet rs = ItemS.executeQuery(query);

        while (rs.next())
        {
            int itemStatus=rs.getInt(item_Status);
            if(itemStatus==1){
                isChecked=true;
            }
        }

        */
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
