package com.example.kandoe.Activity.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.kandoe.Activity.MainActivity;
import com.example.kandoe.Model.Organisation;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.SubTheme;
import com.example.kandoe.Model.Theme;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.Utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Thomas on 2016-03-01.
 */
public class SessionAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Organisation> groups;
    private ArrayList<SubTheme> subThemes;

    public SessionAdapter(Context context, ArrayList<Organisation> groups, ArrayList<SubTheme> subThemes) {
        this.context = context;
        this.groups = groups;
        this.subThemes = subThemes;


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


        final Session child = (Session) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.listchild, null);
        }

        TextView themetag = (TextView) convertView.findViewById(R.id.txtThemeName);
        TextView subtheme = (TextView) convertView.findViewById(R.id.txtSubthemeName);
        TextView themedescp = (TextView) convertView.findViewById(R.id.txtDescription);

        Theme currentTheme = null;
        SubTheme currentSubtheme = null;
        ArrayList<Theme> themes = groups.get(groupPosition).getThemes();
        for (SubTheme subthemeTemp : subThemes) {
            if (subthemeTemp.getId() == child.getSubThemeId()) {
                for (Theme themeTemp : themes) {
                    if (subthemeTemp.getThemaId() == themeTemp.getId()) {
                        currentSubtheme = subthemeTemp;
                        currentTheme = themeTemp;
                        break;
                    }
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


        boolean notYetstarted = checkStartDate(child);
        if (notYetstarted) {
            convertView.setAlpha((float) 0.5);
            convertView.setBackgroundColor(Color.LTGRAY);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);
                    // set title
                    alertDialogBuilder.setTitle("Helaas!");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Dju! Deze sessie moet nog beginnen. We zien je graag terug op " + Utilities.dateFormatter(child.getStart()) + ".\nTot dan!")
                            .setCancelable(false)
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();

                }
            });

        }
        return convertView;
    }

    private boolean checkStartDate(Session child) {

        String startDate = Utilities.dateFormatter(child.getStart());
        String endDate = Utilities.dateFormatter(child.getEnd());

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dateStart = null;
        Date dateEnd = null;
        try {
            dateStart = dateFormat.parse(startDate);
            dateEnd = dateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();

        return cal.after(dateStart);


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
