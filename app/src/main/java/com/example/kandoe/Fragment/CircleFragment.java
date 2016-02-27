package com.example.kandoe.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kandoe.Adpaters.CardAdapter;
import com.example.kandoe.Controller.CircleSessionController;
import com.example.kandoe.R;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CircleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CircleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CircleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CircleSessionController controller;

    private OnFragmentInteractionListener mListener;


    public CircleFragment() {
        // Required empty public constructor
    }


    public static CircleFragment newInstance(String param1, String param2) {
        CircleFragment fragment = new CircleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //TODO Add session parameter
        controller = new CircleSessionController(getContext());


    }

    private void initItems() {
        DisplayMetrics displayMetrics = controller.getContext().getResources().getDisplayMetrics();

        int height = displayMetrics.heightPixels;


        //ExpandableListViewAdapter listViewAdapter = new ExpandableListViewAdapter();

        // listView.setAdapter(listViewAdapter);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_circlesession, container, false);


        controller.createLadder(container);

        ListView listView = new ListView(getContext());

    /*    ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_2, android.R.id.text1, new LinkedList()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(controller.getCards().get(position).getText());
                text2.setText(controller.getCards().get(position).getDescription());
                return view;
            }
        };*/

        CardAdapter cardAdapter= new CardAdapter(getContext(), android.R.layout.simple_list_item_2, controller.getCards());

        controller.setAdapter(cardAdapter);

        listView.setAdapter(cardAdapter);
// ...


        container.addView(listView);

        listView.setPadding(0, (int) controller.getBottomboundLadder(), 0, 0);


        initItems();

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

    //create ladder legs
 /*   private void createLadder(ViewGroup container) {

        int aantaltredes = 4;
        tredes = new ArrayList<>(aantaltredes);

        //left leg
        int clLeft = 200, clTop = 150, clRight = clLeft + 50, clBottom = 800;
        int offset = container.getWidth() - 450;
        View leftleg = new RoundedRectangle(getContext(), clLeft, clTop, clRight, clBottom, Color.rgb(102, 51, 0));

        //right leg
        View rightLeg = new RoundedRectangle(getContext(), clLeft + offset, clTop, clRight + offset, clBottom, Color.rgb(102, 51, 0));


        int ctLeft = 200, ctTop = 225, ctRight = container.getWidth() - 200, ctBottom = ctTop + 15;
        int topOffset = 0;

        //tredes
        for (int i = 0; i < aantaltredes; i++) {
            RoundedRectangle trede = new RoundedRectangle(getContext(), ctLeft, ctTop + topOffset, ctRight, ctBottom + topOffset, Color.rgb(101, 67, 33));
            topOffset += 125;
            tredes.add(trede);
            container.addView(trede);

        }

        container.addView(leftleg);

        container.addView(rightLeg);
    }
*/


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
