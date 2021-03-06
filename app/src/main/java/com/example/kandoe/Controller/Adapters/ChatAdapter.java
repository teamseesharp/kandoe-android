package com.example.kandoe.Controller.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kandoe.Model.ChatMessage;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.Utilities;

import java.util.ArrayList;

/**
 * Adapter for chatfragment
 */
public class ChatAdapter extends ArrayAdapter {
    private ArrayList<ChatMessage> chatMessages;
    private ArrayList<UserAccount> particpants;

    private UserAccount currentUser;

    public ChatAdapter(Context context, int resource, ArrayList<ChatMessage> data, UserAccount userAccount) {
        super(context, resource, data);
        chatMessages = data;
        currentUser = userAccount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        ViewHolder holder;

        ChatMessage message = chatMessages.get(position);
        UserAccount sender = getMatchingAccount(message.getMessengerId());

        if(sender != null) {
            if (currentUser.getId() != sender.getId()) {
                v = LayoutInflater.from(getContext()).inflate(R.layout.messagereveice, null, false);
                holder = new ViewHolder();
                holder.date = (TextView) v.findViewById(R.id.txtChatDate);
                holder.message = (TextView) v.findViewById(R.id.txtMessage);
                holder.sender = (TextView) v.findViewById(R.id.txtChatName);
                v.setTag(holder);

                holder.sender.setText(sender.getName());

            } else {
                v = LayoutInflater.from(getContext()).inflate(R.layout.messagesend, null, false);
                holder = new ViewHolder();
                holder.date = (TextView) v.findViewById(R.id.txtChatDate);
                holder.message = (TextView) v.findViewById(R.id.txtMessage);
                holder.sender = (TextView) v.findViewById(R.id.txtChatName);
                v.setTag(holder);
            }

            holder.date.setText(Utilities.dateFormatterWithHour(message.getTimestamp()));
            holder.message.setText(message.getText());
        }
        return v;
    }

    public void setParticpants(ArrayList<UserAccount> particpants) {
        this.particpants = particpants;
    }


    private UserAccount getMatchingAccount(int id) {
        for (UserAccount parti : particpants) {
            if (parti.getId() == id) {
                return parti;
            }
        }
        return null;
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
    public int getCount() {
        return chatMessages.size();
    }
}
