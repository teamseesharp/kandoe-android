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
 * Created by Thomas on 2016-03-15.
 */
public class MessageAdapter extends ArrayAdapter {

    private ArrayList<ChatMessage> messages;
    private UserAccount account;
    private KandoeBackendAPI service;

    public MessageAdapter(Context context, ArrayList<ChatMessage> objects, UserAccount account, KandoeBackendAPI service) {
        super(context, R.layout.messagesend,objects);
        this.account = account;
        this.service = service;
        this.messages = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        ChatMessage message = messages.get(position);

        getSenderAccount(message.getMessengerId());


        if (view == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
           if (message.getMessengerId() == account.getId()){
              view = inflater.inflate(R.layout.messagesend,parent,false);
           }else  view = inflater.inflate(R.layout.messagereveice, parent, false);

            holder = new ViewHolder();
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.date.setText(Utilities.dateFormatterWithHour(message.getTimestamp()));
        holder.message.setText(message.getText());
        //holder.sender.setText(getSenderAccount(message.getMessengerId()));


        return view;
    }

    private String getSenderAccount(int messengerId) {
        Call<UserAccount> accountCall = service.getUserById(messengerId);


        final UserAccount[] account = new UserAccount[1];

        accountCall.enqueue(new Callback<UserAccount>() {
            @Override
            public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                if (response.isSuccess()){
                    account[0] = response.body();
                }
            }

            @Override
            public void onFailure(Call<UserAccount> call, Throwable t) {

            }
        });

        return account[0].getName();

    }

    static class ViewHolder {
        TextView message;
        TextView sender;
        TextView date;
    }
}
