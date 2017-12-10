package com.example.oscarthorsson.remember;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by patsy on 2017-11-27.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter
{
    public LayoutInflater infalInflater;
    public Context context;
    public ArrayList<String> dataHeader;
    HashMap<String, List<ReminderList>> theHashMap = new HashMap<String, List<ReminderList>>();


    public ExpandableListViewAdapter(Context context, ArrayList<String> dataHeader, HashMap<String, List<ReminderList>> theHashMap){
        this.context = context;
        this.theHashMap= theHashMap;
        this.dataHeader=dataHeader;

    }

    @Override
    public int getGroupCount(){
        return dataHeader.size();

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.theHashMap.get(this.dataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {


        return this.theHashMap.get(this.dataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public void onGroupCollapsed (int groupPosition){
        super.onGroupCollapsed(groupPosition);
    }
    @Override
    public void onGroupExpanded(int groupPosition){
        super.onGroupExpanded(groupPosition);
    }
    @Override
    public View getChildView(int groupPosition, final int childPosition,boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
       TextView text;
       if(convertView == null){

           infalInflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = infalInflater.inflate(R.layout.list_item,null);
        }
        text = convertView.findViewById(R.id.lblListItem);
        text.setText(childText);
        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
    String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group,null);

        }
       TextView lblListHeader = convertView. findViewById(R.id.header);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
