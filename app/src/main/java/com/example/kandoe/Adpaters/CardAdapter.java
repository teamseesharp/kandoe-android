package com.example.kandoe.Adpaters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kandoe.Model.Card;
import com.example.kandoe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoachimDs on 25/02/2016.
 */
public class CardAdapter extends ArrayAdapter{
    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public CardAdapter(Context context, int resource, ArrayList data) {
        super(context, resource, data);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if (view == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) view.findViewById(R.id.txtCardTitle);
            holder.description = (TextView) view.findViewById(R.id.txtCardDescription);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Card card = (Card) data.get(position);
        holder.title.setText(card.getText());
        holder.description.setText(card.getDescription());
        return view;
    }

    static class ViewHolder{
        TextView title;
        TextView description;
    }
}
