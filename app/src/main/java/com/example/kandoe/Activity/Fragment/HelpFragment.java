package com.example.kandoe.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kandoe.R;

/**
 * Creates helpfragment
 */
public class HelpFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_help,container,false);
        TextView textView = (TextView) v.findViewById(R.id.help_kandoe);
        textView.setMovementMethod(new ScrollingMovementMethod());

        return v;
    }
}
