package com.example.oscarthorsson.remember;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by patsy on 2017-11-27.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter
{

    private Context context;
    //List<ReminderList> reminders;
    private List<ReminderList>dataHeader;
    private HashMap<String,List<String>>listHashMap;


    public ExpandableListViewAdapter(Context context, List<ReminderList>dataHeader,HashMap<String,List<String>>listHashMap){
        this.context=context;
        this.dataHeader= dataHeader;
        this.listHashMap=listHashMap;

    }

    @Override
    public int getGroupCount(){
        return dataHeader.size();

    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(dataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return dataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get(dataHeader.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(i);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group,null);
        }
        TextView lblListHeader = view.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String childText = (String)getChild(i,i1);
        if (view == null) {

            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item,null);

        }
        TextView txtListChild = view.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


}
