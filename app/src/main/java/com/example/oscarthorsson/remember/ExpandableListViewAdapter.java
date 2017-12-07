package com.example.oscarthorsson.remember;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.RemoteException;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by patsy on 2017-11-27.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter
{

    public Activity activity;
    public LayoutInflater inflater;
    //List<ReminderList> reminders;
    private final SparseArray<ReminderList> groups;



    public ExpandableListViewAdapter(Activity act, SparseArray <ReminderList> groups){
        activity = act;
        inflater = act.getLayoutInflater();
        this.groups=groups;

    }

    @Override
    public int getGroupCount(){
        return groups.size();

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).childern.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
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
       final String children = (String) getChild(groupPosition, childPosition);
       TextView text = null;
       if(convertView == null){
            convertView = inflater.inflate(R.layout.list_group,null);
        }
        text = (TextView) convertView.findViewById(R.id.textView1);
        text.setText(children);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,children, Toast.LENGTH_SHORT).show();

            }

        });
        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.list_group,null);

        }
       ReminderList group = (ReminderList) getGroup (groupPosition);
        ((CheckedTextView) convertView). setText(group.toString());
        ((CheckedTextView)convertView). setChecked(isExpanded);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


}
