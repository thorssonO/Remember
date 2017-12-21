package com.example.oscarthorsson.remember;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by patsy on 2017-11-27.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    public LayoutInflater iInflater;
    public Context context;
    public ArrayList<String> dataHeader;
    HashMap<String, ReminderList> theHashMap = new HashMap<String, ReminderList>();


    public ExpandableListViewAdapter(Context context, ArrayList<String> dataHeader, HashMap<String, ReminderList> theHashMap){
        System.out.println("hashmap: " + theHashMap);
        System.out.println("dataheader: " + dataHeader);
        //Log-syfte ovan
        this.context = context;
        this.theHashMap= theHashMap;
        this.dataHeader=dataHeader;
    }

    @Override
    public int getGroupCount(){
        return dataHeader.size();
    } //Antal titlar

    @Override
    public int getChildrenCount(int groupPosition) {
        System.out.println(this.dataHeader.get(groupPosition));
        return this.theHashMap.get(this.dataHeader.get(groupPosition)).items().size();
    } //Antal items

    @Override
    public Object getGroup(int groupPosition) {
        return dataHeader.get(groupPosition);
    } //Hämtar titlar

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        System.out.println(this.theHashMap.get(this.dataHeader.get(groupPosition)));
        //log
        return theHashMap.get(this.dataHeader.get(groupPosition)).items().get(childPosition);
    } //Hämtar items

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    } //Hämtar titel id för att positionera dem

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    } //Hämtar item id för att positionera dem

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

        if (convertView == null){

            iInflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = iInflater.inflate(R.layout.list_item,null);

            final ViewHolder childHolder = new ViewHolder();
            childHolder.cd = convertView.findViewById(R.id.check);

            childHolder.cd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton button, boolean isChecked){
                    ReminderItem itemCheck = (ReminderItem)childHolder.cd.getTag();
                    itemCheck.setSelected(button.isChecked());
                }
            });
            convertView.setTag(childHolder);
            childHolder.cd.setTag(item);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.cd.setChecked (item.isSelected());
        holder.cd.setText (item.getItemName());
        return convertView;
    } //beskriver hur items skall visas

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater iInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = iInflater.inflate(R.layout.list_group,null);
        }

        TextView lblListHeader = convertView. findViewById(R.id.header);
        lblListHeader.setTypeface(null, Typeface.NORMAL);
        lblListHeader.setText(headerTitle);
        return convertView;
    } //beskiver hur titlar skall visas

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
