package com.example.kandoe.Controller.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kandoe.Model.ChatMessage;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;
import com.example.kandoe.Utilities.Utilities;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Thomas on 2016-03-16.
 */
public class ChatAdapter extends ArrayAdapter {


    private ArrayList<ChatMessage> chatMessages;
    private KandoeBackendAPI service;



    public ChatAdapter(Context context, int resource, ArrayList<ChatMessage> data) {
        super(context, resource, data);
        chatMessages = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        ChatMessage message = chatMessages.get(position);

        //getSenderAccount(message.getMessengerId());


        if (view == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();

            view = inflater.inflate(R.layout.messagereveice, parent, false);




            holder = new ViewHolder();
            view.setTag(holder);

            holder.date = (TextView) view.findViewById(R.id.txtDate);
            holder.message = (TextView) view.findViewById(R.id.txtMessage);
            holder.sender = (TextView) view.findViewById(R.id.txtName);

        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.date.setText(Utilities.dateFormatterWithHour(message.getTimestamp()));
        holder.message.setText(message.getText());
        //holder.sender.setText(getSenderAccount(message.getMessengerId()));


        return view;

    }

    static class ViewHolder {
        TextView message;
        TextView sender;
        TextView date;
    }




    public ArrayList<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(ArrayList<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

    }

    private String getSenderAccount(int messengerId) {

        Call<UserAccount> accountCall = service.getUserById(messengerId);


        final UserAccount[] account = new UserAccount[1];

        accountCall.enqueue(new Callback<UserAccount>() {
            @Override
            public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                if (response.isSuccess()) {
                    account[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<UserAccount> call, Throwable t) {

            }
        });

        return account[0].getName();

    }

    public void setService(KandoeBackendAPI service) {
        this.service = service;
    }
}
