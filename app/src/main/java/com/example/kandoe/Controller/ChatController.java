package com.example.kandoe.Controller;

import android.util.Log;

import com.example.kandoe.Controller.Adapters.ChatAdapter;
import com.example.kandoe.Model.ChatMessage;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Thomas on 2016-02-26.
 */
public class ChatController {

    private static final String TAG = "ChatController";
    private Session mSession;
    private KandoeBackendAPI mService;
    private UserAccount mUserAccount;
    private boolean succes;

    private ChatAdapter chatAdapter;
    private ArrayList<ChatMessage> chatMessages;


    public ChatController(Session mSession, KandoeBackendAPI mService, UserAccount mUserAccount) {
        this.mSession = mSession;
        this.mService = mService;
        this.mUserAccount = mUserAccount;
        this.chatMessages = new ArrayList<>();
    }

    public void initAdapter(ChatAdapter chatAdapter){
        this.chatAdapter = chatAdapter;
    }

    public void getMessages(){
        Call<List<ChatMessage>> call = mService.getChatMessagesBySessionId(mSession.getId());
        call.enqueue(new Callback<List<ChatMessage>>() {
            @Override
            public void onResponse(Call<List<ChatMessage>> call, Response<List<ChatMessage>> response) {
                if (response.isSuccess()) {
                    chatMessages.addAll(response.body());
                    chatAdapter.notifyDataSetChanged();
                    System.out.println("GET MSG GELUKT!!");
                }else{
                    System.out.println("GET MSG ONRESP !!");
                }
            }

            @Override
            public void onFailure(Call<List<ChatMessage>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        chatAdapter.notifyDataSetChanged();
    }

    public void sendMessage(String content){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessengerId(mUserAccount.getId());
        chatMessage.setSessionId(mSession.getId());
        chatMessage.setText(content);

        Call<Void> call = mService.postChatMessages(chatMessage);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccess()){
                    getMessages();
                    System.out.println("MSG GELUKT!!");
                    succes = true;
                }else{
                    System.out.println("chatmsg onresp FAIL... CODE: " + response.code());
                    succes = false;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                succes = false;
            }
        });
    }


    public ArrayList<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public boolean isSucces() {
        return succes;
    }
}
