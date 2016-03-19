package com.example.kandoe.Controller.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.kandoe.Model.Organisation;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.SubTheme;
import com.example.kandoe.Model.Theme;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.Utilities;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;

/**
 * Created by Thomas on 2016-03-01.
 */
public class SessionAdapter extends BaseExpandableListAdapter {
    private Context context;
    private UserAccount account;
    private ArrayList<Organisation> groups;
    private ArrayList<SubTheme> subThemes;


    private final String TITLE = "Helaas..";
    private String message;
    private boolean invite = false;
    private boolean mIsSessionListFragment;

    public SessionAdapter(Context context, ArrayList<Organisation> groups, ArrayList<SubTheme> subThemes, UserAccount userAccount, boolean isSessionListFragment) {
        this.context = context;
        this.groups = groups;
        this.subThemes = subThemes;
        account = userAccount;

        mIsSessionListFragment = isSessionListFragment;
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
        TextView sessiondescp = (TextView) convertView.findViewById(R.id.txtSessionName);

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

        if (sessiondescp != null) {
            sessiondescp.setText(child.getDescription());
        }




        boolean notYetstarted = checkStartDate(child);
        if (notYetstarted) {
            themetag.setBackgroundResource(R.drawable.orangetag);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    message = "Dju! Deze sessie moet nog beginnen. We zien je graag terug op " + Utilities.dateFormatter(child.getStart()) + ".\n" + "Tot dan!";
                    showAlertDialog(TITLE, message);
                }
            });
        } else themetag.setBackgroundResource(R.drawable.greentag);

        if (child.isFinished()) {
            themetag.setBackgroundResource(R.drawable.redtag);

        }

        if (child.getParticipants().size() >= child.getMaxParticipants()) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    message = "Deze sessie zit al vol!";
                    showAlertDialog(TITLE, message);
                }
            });
        }

        //TODO: IDEM HIER --> OF ALLES KUNNEN DEELNEMEN OF GEEN ENKELE
        if (mIsSessionListFragment) {
            for (UserAccount invitee : child.getInvitees()) {
                if (invitee.getId() != account.getId()) {
                    convertView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            message = "Je bent niet uitgenodigd voor deze sessie en kan dus hier niet aan deelnemen";
                            showAlertDialog(TITLE, message);
                        }
                    });
                }
            }
        }

        return convertView;
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private boolean checkStartDate(Session child) {
        String startDate = child.getStart();

        DateTime dateTime = DateTime.parse(startDate);
        DateTime now = DateTime.now();

        return now.isBefore(dateTime);
    }


    //region Override Methods

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
    //endregion

}