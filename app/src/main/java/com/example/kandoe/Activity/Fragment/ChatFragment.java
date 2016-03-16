package com.example.kandoe.Activity.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.kandoe.Controller.Adapters.ChatAdapter;
import com.example.kandoe.Controller.Adapters.MessageAdapter;
import com.example.kandoe.Controller.ChatController;
import com.example.kandoe.Model.ChatMessage;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {


    private static final String ARG_PARAM1 = "Service";
    private static final String ARG_PARAM2 = "Sessions";
    private static final String ARG_PARAM3 = "Profile";
    private static final String ARG_PARAM4 = "isReview";


    private KandoeBackendAPI mService;
    private Session mSession;
    private ArrayList<ChatMessage> chatMessages;
    private MessageAdapter messageAdapter;
    private UserAccount mUserAccount;

    private ChatController chatController;

    private EditText txtMessage;
    private ImageButton btnSend;

    private boolean mIsReview;


    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance(KandoeBackendAPI param1, Session param2, UserAccount userAccount, boolean isReview) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
        args.putSerializable(ARG_PARAM2, param2);
        args.putSerializable(ARG_PARAM3, userAccount);
        args.putSerializable(ARG_PARAM4, isReview);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mService = (KandoeBackendAPI) getArguments().getSerializable(ARG_PARAM1);
            mSession = (Session) getArguments().getSerializable(ARG_PARAM2);
            mUserAccount = (UserAccount) getArguments().getSerializable(ARG_PARAM3);
            mIsReview = getArguments().getBoolean(ARG_PARAM4);
        }
        chatController = new ChatController(mSession, mService, mUserAccount);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        btnSend = (ImageButton) view.findViewById(R.id.btnSend);
        txtMessage = (EditText) view.findViewById(R.id.txtMsg);
        ListView listView = (ListView) view.findViewById(R.id.lvMessages);

        ChatAdapter chatAdapter = new ChatAdapter(getContext(),android.R.layout.simple_list_item_1, chatController.getChatMessages());
        chatController.initAdapter(chatAdapter);
        listView.setAdapter(chatAdapter);

        chatController.getMessages();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageContent = txtMessage.getText().toString();
                if (messageContent.length() > 3) chatController.sentMessage(messageContent);
            }
        });

        return view;
    }

}
