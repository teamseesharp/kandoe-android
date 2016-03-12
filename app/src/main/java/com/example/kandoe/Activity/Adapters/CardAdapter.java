package com.example.kandoe.Activity.Adapters;

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
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.kandoe.Utilities.DrawableGraphics.BulletColor;
import com.example.kandoe.Model.Card;
import com.example.kandoe.R;

import java.util.ArrayList;

import static com.example.kandoe.R.drawable.listborderitem;


public class CardAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();
    private ArrayList<CheckBox> checks;
    private RadioGroup radioGroup;
    private boolean setup;

    public CardAdapter(Context context, boolean setup, ArrayList data) {
        super(context, R.layout.card, data);
        this.context = context;
        this.layoutResourceId = R.layout.card;
        this.data = data;
        this.checks = new ArrayList<>();
        this.setup = setup;
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
        holder.description.setText(card.getDescription());
        holder.id.setText(String.valueOf(card.getId()));

        //TODO positie meegeven
        holder.number.setText("");

        setBG(holder, card.getId());

        handleSetup(holder,view);

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
                    }

                }
            });
        } else {
            holder.upvote.setVisibility(View.INVISIBLE);
            holder.number.setText("");

           view.setBackgroundResource(R.drawable.listborderitem);

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
}
