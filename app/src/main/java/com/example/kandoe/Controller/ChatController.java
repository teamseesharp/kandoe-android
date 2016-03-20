package com.example.kandoe.Controller;

import android.util.Log;
import android.widget.EditText;

import com.example.kandoe.Activity.Fragment.ChatFragment;
import com.example.kandoe.Controller.Adapters.ChatAdapter;
import com.example.kandoe.Model.ChatMessage;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.Utilities.API.ChatPoller;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatController {

    private static final String TAG = "ChatController";
    private Session mSession;
    private KandoeBackendAPI mService;
    private UserAccount mUserAccount;
    private ChatFragment fragment;

    private ChatAdapter chatAdapter;
    private ArrayList<ChatMessage> chatMessages;

    private boolean firstTime = true;

    public ChatController(Session mSession, KandoeBackendAPI mService, UserAccount mUserAccount, ChatFragment chatFragment) {
        this.mSession = mSession;
        this.mService = mService;
        this.mUserAccount = mUserAccount;
        this.chatMessages = new ArrayList<>();

        ChatPoller poller = new ChatPoller(this);
        poller.pollForMessages();
        fragment = chatFragment;
    }

    public void initAdapter(ChatAdapter chatAdapter) {
        this.chatAdapter = chatAdapter;
        chatAdapter.setParticpants(mSession.getParticipants());
    }

    public void getMessages() {
        Call<List<ChatMessage>> call = mService.getChatMessagesBySessionId(mSession.getId());
        call.enqueue(new Callback<List<ChatMessage>>() {
            @Override
            public void onResponse(Call<List<ChatMessage>> call, Response<List<ChatMessage>> response) {
                if (response.isSuccess()) {
                    chatMessages.clear();
                    chatMessages.addAll(response.body());
                    chatAdapter.notifyDataSetChanged();
                    System.out.println("GET MSG GELUKT!!");
                } else {
                    System.out.println("GET MSG ONRESP !!");
                }

                if (firstTime){
                    fragment.scrollMyListViewToBottom();
                    firstTime=false;
                }
            }

            @Override
            public void onFailure(Call<List<ChatMessage>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void sendMessage(final EditText content) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessengerId(mUserAccount.getId());
        chatMessage.setSessionId(mSession.getId());
        chatMessage.setText(content.getText().toString());

        Call<Void> call = mService.postChatMessages(chatMessage);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccess()) {
                    getMessages();
                    System.out.println("MSG GELUKT!!");
                    setEdittextEmpty(content);
                } else {
                    System.out.println("chatmsg onresp FAIL... CODE: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });
    }

    //region Getters & Setters
    private void setEdittextEmpty(EditText edittext) {
        edittext.setText("");
    }

    public ArrayList<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public Session getmSession() {
        return mSession;
    }

    public KandoeBackendAPI getmService() {
        return mService;
    }

    public ChatAdapter getChatAdapter() {
        return chatAdapter;
    }
    //endregion
}



