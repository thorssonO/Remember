package com.example.oscarthorsson.remember;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

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
    HashMap<String, ReminderList> theHashMap = new HashMap<String, ReminderList>();


    public ExpandableListViewAdapter(Context context, ArrayList<String> dataHeader, HashMap<String, ReminderList> theHashMap){
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
        return this.theHashMap.get(this.dataHeader.get(groupPosition)).items().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        //System.out.println(this.theHashMap.get(this.dataHeader.get(groupPosition)));
        return theHashMap.get(this.dataHeader.get(groupPosition)).items().get(childPosition);
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
        ReminderItem item = (ReminderItem)getChild(groupPosition, childPosition);
        String childText =  item.name();
       TextView text;

       if(convertView == null){

           infalInflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = infalInflater.inflate(R.layout.list_item,null);

        final viewHolder childHolder = new viewHolder();
        childHolder.cd= (CheckBox) convertView.findViewById(R.id.checkBox);
       childHolder.cd.setOnCheckedChangeListener(new OnCheckedChangeListener(){
       @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked){
           ReminderItem itemCheck = (ReminderItem) childHolder.cd.getTag();
           itemCheck.setSelected(button.isChecked());
        }
               });

        convertView.setTag(childHolder);
        childHolder.cd.setTag(item);
    }else{
            convertView = convertView;
            ((viewHolder)convertView.getTag()).cd.setTag(item.name());
        }

        viewHolder holder = (viewHolder) convertView.getTag();
       holder.cd.setChecked (item.isSelected());
        holder.cd.setText (item.name());

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
