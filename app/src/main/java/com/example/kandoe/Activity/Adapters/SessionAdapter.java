package com.example.kandoe.Activity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.kandoe.Model.Organisation;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.SubTheme;
import com.example.kandoe.Model.Theme;
import com.example.kandoe.R;

import java.util.ArrayList;

/**
 * Created by Thomas on 2016-03-01.
 */
public class SessionAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Organisation> groups;

    public SessionAdapter(Context context, ArrayList<Organisation> groups) {
        this.context = context;
        this.groups = groups;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Session> chList = groups.get(groupPosition).getSessions();
        return chList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        Session child = (Session) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.listchild, null);
        }
        TextView themetag = (TextView) convertView.findViewById(R.id.txtThemeName);
        TextView subtheme = (TextView) convertView.findViewById(R.id.txtSubthemeName);
        TextView themedescp =(TextView) convertView.findViewById(R.id.txtDescription);

        Theme currentTheme = null;
        SubTheme currentSubtheme = null;

        for (Theme thema : groups.get(groupPosition).getThemes()) {
            for (SubTheme subthema : thema.getSubthemes()) {
                if (subthema.getId() == child.getSubThemeId()) {
                    currentSubtheme = subthema;
                    currentTheme = thema;
                    break;
                }
            }
        }


        if (currentTheme != null) {
            themetag.setText(currentTheme.getName());
            themedescp.setText(currentTheme.getDescription());
        }
        if (currentSubtheme != null) {
            subtheme.setText(currentSubtheme.getName());
        }


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Session> chList = groups.get(groupPosition).getSessions();
        return chList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Organisation group = (Organisation) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.listgroup, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.group_name);
        tv.setText(group.getName());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
