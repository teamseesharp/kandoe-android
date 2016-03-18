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
    private KandoeBackendAPI mService;
    private UserAccount mUserAccount;
    private UserAccount currentUser;
    private ViewHolder holder = null;

    public ChatAdapter(Context context, int resource, ArrayList<ChatMessage> data,KandoeBackendAPI service, UserAccount userAccount) {
        super(context, resource, data);
        chatMessages = data;
        mService = service;
        currentUser = userAccount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        ChatMessage message = chatMessages.get(position);

        if (view == null) {
                 LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
                 view = inflater.inflate(R.layout.messagereveice, parent, false);
                 holder.date = (TextView) view.findViewById(R.id.txtChatDate);
                 holder.message = (TextView) view.findViewById(R.id.txtMessage);
                 holder.sender = (TextView) view.findViewById(R.id.txtChatName);

            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.date.setText(Utilities.dateFormatterWithHour(message.getTimestamp()));
        holder.message.setText(message.getText());

        //TODO DEES MOET HIER ANDERS PAKT DIE DAT NIET
        Call<UserAccount> accountCall = mService.getUserById(message.getMessengerId());
        accountCall.enqueue(new Callback<UserAccount>() {
            @Override
            public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                if (response.isSuccess()) {
                    mUserAccount = response.body();
                    holder.sender.setText(mUserAccount.getName());
                    System.out.println("GET USER SUCCES");
                } else {
                    System.out.println("GET USER ONREPS FAIL");
                }
            }

            @Override
            public void onFailure(Call<UserAccount> call, Throwable t) {
                System.out.println("GET USER ONREPS FAIL");
            }
        });

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
}
