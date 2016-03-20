package com.example.kandoe.Utilities.API;

import android.util.Log;

import com.example.kandoe.Controller.ChatController;
import com.example.kandoe.Model.ChatMessage;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Thomas on 19-3-2016.
 */
public class ChatPoller {
    private final int REFRESH_RATE = 2;
    private ScheduledExecutorService scheduler;

    private ChatController chatController;

    public ChatPoller(ChatController chatController) {
        this.chatController = chatController;
        scheduler = Executors.newScheduledThreadPool(1);
    }

    public void pollForMessages() {
        Runnable poller = new Runnable() {
            public void run() {

                Call<List<ChatMessage>> call = chatController.getmService().getChatMessagesBySessionId(chatController.getmSession().getId());
                call.enqueue(new Callback<List<ChatMessage>>() {
                    @Override
                    public void onResponse(Call<List<ChatMessage>> call, Response<List<ChatMessage>> response) {
                        if (response.isSuccess()) {
                            chatController.getChatMessages().clear();
                            chatController.getChatMessages().addAll(response.body());
                            chatController.getChatAdapter().notifyDataSetChanged();

                        } else {
                            System.out.println("POLL MSG NOT SUCCESFULL !!");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ChatMessage>> call, Throwable t) {
                        Log.d("POLLER", "onFailure: " + t.getMessage());
                    }
                });
            }
        };

        final ScheduledFuture pollerHandle = scheduler.scheduleWithFixedDelay(poller, 0, REFRESH_RATE, TimeUnit.SECONDS);

        scheduler.schedule(new Runnable() {
            public void run() {
                pollerHandle.cancel(true);
            }
        }, 60 * 60, TimeUnit.SECONDS);
    }
}
