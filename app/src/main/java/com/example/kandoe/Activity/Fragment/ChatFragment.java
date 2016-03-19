package com.example.kandoe.Activity.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kandoe.Controller.Adapters.ChatAdapter;
import com.example.kandoe.Controller.ChatController;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;

import java.io.Serializable;

/**
 *
 */
public class ChatFragment extends Fragment {
    private static final String ARG_PARAM1 = "Service";
    private static final String ARG_PARAM2 = "Sessions";
    private static final String ARG_PARAM3 = "Profile";
    private static final String ARG_PARAM4 = "isReview";

    private KandoeBackendAPI mService;
    private Session mSession;
    private UserAccount mUserAccount;
    private ChatController chatController;
    private EditText txtMessage;
    private ImageButton btnSend;
    private ListView listView;
    private ChatAdapter chatAdapter;

    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance(KandoeBackendAPI param1, Session param2, UserAccount userAccount) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
        args.putSerializable(ARG_PARAM2, param2);
        args.putSerializable(ARG_PARAM3, userAccount);
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
        }
        chatController = new ChatController(mSession, mService, mUserAccount,this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        btnSend = (ImageButton) view.findViewById(R.id.btnSend);
        txtMessage = (EditText) view.findViewById(R.id.txtMsg);

        if(mSession.isFinished()){
            btnSend.setVisibility(View.INVISIBLE);
            txtMessage.setVisibility(View.INVISIBLE);

            TextView divider = (TextView) view.findViewById(R.id.divider);
            divider.setVisibility(View.INVISIBLE);
        }

        listView = (ListView) view.findViewById(R.id.lvMessages);

        chatAdapter = new ChatAdapter(getContext(),android.R.layout.simple_list_item_1, chatController.getChatMessages(),mUserAccount);
        chatController.initAdapter(chatAdapter);
        listView.setAdapter(chatAdapter);

        chatController.getMessages();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageContent = txtMessage.getText().toString();
                if (messageContent.length() > 3) chatController.sendMessage(txtMessage);
            }
        });

        return view;
    }



    public void scrollMyListViewToBottom() {
        listView.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                listView.smoothScrollToPosition(chatAdapter.getCount() - 1);
            }
        });
    }
}
