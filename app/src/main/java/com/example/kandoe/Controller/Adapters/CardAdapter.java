package com.example.kandoe.Controller.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.kandoe.Model.Card;
import com.example.kandoe.Model.Session;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.DrawableGraphics.BulletColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class CardAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Card> data;
    private ArrayList<CheckBox> checks;
    private String chosenCardToUpvote;
    private Session mSession;

    private boolean setup;

    public CardAdapter(Context context, boolean setup, ArrayList<Card> data, Session session) {
        super(context, R.layout.card, data);
        this.context = context;
        this.layoutResourceId = R.layout.card;
        this.data = data;
        this.checks = new ArrayList<>();
        this.setup = setup;
        this.mSession = session;
        sortCards();
    }

    public void sortCards() {
        Collections.sort(data, new Comparator<Card>() {
            @Override
            public int compare(Card lhs, Card rhs) {
                if (lhs.getSessionLevel() > rhs.getSessionLevel()) return 1;
                if (lhs.getSessionLevel() < rhs.getSessionLevel()) return -1;
                if (lhs.getSessionLevel() == rhs.getSessionLevel()) return 0;
                return 0;
            }
        });

        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();

            holder.description = (TextView) view.findViewById(R.id.txtCardTitle);
            holder.upvote = (CheckBox) view.findViewById(R.id.radioButton);
            holder.number = (TextView) view.findViewById(R.id.txtNumberHolder);
            holder.id = (TextView) view.findViewById(R.id.txtId);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Card card = (Card) data.get(position);
        holder.description.setText(card.getText());
        holder.id.setText(String.valueOf(card.getId()));

        holder.number.setText("");

        setBG(holder, card.getId());

        handleSetup(holder, view);

        return view;
    }

    static class ViewHolder {
        TextView description;
        CheckBox upvote;
        TextView number;
        TextView id;
    }


    private void handleSetup(final ViewHolder holder, View view) {
        if (!setup) {

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.upvote.setChecked(true);
                }
            });

            checks.add(holder.upvote);

            holder.upvote.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
                        for (CheckBox box : checks) {
                            if (!box.equals(buttonView)) {
                                box.setChecked(false);
                            }
                        }
                        chosenCardToUpvote = holder.id.getText().toString();
                    }
                }
            });


        } else {
            holder.upvote.setVisibility(View.INVISIBLE);
            holder.number.setText("");

            view.setBackgroundResource(R.drawable.listborderitem);
        }

        if (mSession.isFinished()){
            holder.upvote.setVisibility(View.INVISIBLE);
        }
    }

    private void setBG(ViewHolder holder, int id) {
        // Initialize a new GradientDrawable
        GradientDrawable gd = new GradientDrawable();

        // Specify the shape of drawable
        gd.setShape(GradientDrawable.RECTANGLE);

        // Set the fill color of drawable
        gd.setColor(Color.parseColor(BulletColor.getColor(id).getHexCode())); // make the background transparent


        // Create a 2 pixels width red colored border for drawable
        gd.setStroke(1, Color.GRAY); // border width and color

        // Make the border rounded
        gd.setCornerRadius(100.0f); // border corner radius

        // Finally, apply the GradientDrawable as TextView background
        holder.number.setBackground(gd);
    }

    public ArrayList<CheckBox> getChecks() {
        return checks;
    }

    public String getChosenCardToUpvote() {
        if (chosenCardToUpvote == null) {
            return "";
        } else return chosenCardToUpvote;
    }
}
