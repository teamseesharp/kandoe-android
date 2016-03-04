package com.example.kandoe.Activity.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.kandoe.Model.Session;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;
import com.example.kandoe.Activity.Adapters.CardAdapter;
import com.example.kandoe.Controller.CircleSessionController;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.DrawableGraphics.SurfacePanel;

import java.io.Serializable;

import retrofit.http.PATCH;


public class CircleFragment extends Fragment {
    private static final String EXTRA_SERVICE = "Service";
    private static final String EXTRA_SESSION = "Session";


    private CircleSessionController controller;
    private OnFragmentInteractionListener mListener;
    private KandoeBackendAPI service;
    private Session session;

    public CircleFragment() {
    }

    public CircleFragment(KandoeBackendAPI service) {
        this.service = service;
    }


    public static CircleFragment newInstance(KandoeBackendAPI service, Session session) {
        CircleFragment fragment = new CircleFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SERVICE, (Serializable) service);
        args.putSerializable(EXTRA_SESSION, (Serializable) session);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            service = (KandoeBackendAPI) getArguments().getSerializable(EXTRA_SERVICE);
            session = (Session) getArguments().getSerializable(EXTRA_SESSION);

        }

        //TODO Add session parameter
        controller = new CircleSessionController(getContext(), session);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_circlesession, container, false);


        // controller.createLadder(container);
//container.addView(new SurfacePanel(getContext(),controller));


        SurfacePanel panel = (SurfacePanel) view.findViewById(R.id.view);
        panel.setController(controller);
       // panel.invalidate();


        System.out.println(panel.getBottom());

        ListView listView = (ListView) view.findViewById(R.id.lvCards);

        CardAdapter cardAdapter = new CardAdapter(getContext(), false, controller.getCards());

        controller.setAdapter(cardAdapter);

        listView.setAdapter(cardAdapter);





      //  listView.setPadding(0, (int) controller.getBottomboundLadder(), 0, 0);


        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
