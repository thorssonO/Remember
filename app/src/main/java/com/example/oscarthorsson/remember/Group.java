package com.example.oscarthorsson.remember;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by patsy on 2017-12-07.
 */

public class Group {

    public String string;
    public final List<String> children = new ArrayList<String>();

    public Group(String string){
        this.string= string;
    }
    public String toString (List<String> children)
    {   FakeReminderStore childrenString = FakeReminderStore.getInstance();
        List<ReminderItem> testChildren = childrenString.getItems();
        return" "+ testChildren;

    }
}
