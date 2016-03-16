package com.example.kandoe.Controller;

import android.util.Log;
import android.widget.Toast;

import com.example.kandoe.Controller.Adapters.ChatAdapter;
import com.example.kandoe.Model.ChatMessage;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import okhttp3.ResponseBody;
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
        chatAdapter.setService(mService);

    }

    public void getMessages(){
        Call<List<ChatMessage>> call = mService.getChatMessagesBySessionId(mSession.getId());

        call.enqueue(new Callback<List<ChatMessage>>() {
            @Override
            public void onResponse(Call<List<ChatMessage>> call, Response<List<ChatMessage>> response) {
                if (response.isSuccess()) {
                    chatMessages.addAll(response.body());
                    chatAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<ChatMessage>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        chatAdapter.notifyDataSetChanged();
    }

    public void sentMessage(String content){

        //ChatMessage chatMessage = new ChatMessage(mUserAccount.getId(),mSession.getId(),content);

        //TODO WEGLATEN ID EN TIMESTAMP
        ChatMessage chatMessage = new ChatMessage(1,mUserAccount.getId(),mSession.getId(),content,"2016-03-16T00:00:00");


       Call<ResponseBody> call = mService.chat(chatMessage);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccess()){
                    getMessages();
                    System.out.println(response);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }


    public ArrayList<ChatMessage> getChatMessages() {
        return chatMessages;
    }

}
