package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class UsFragment extends Fragment {
    private String context;
    private TextView mTV;

    public UsFragment(String context){
        this.context = context;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.us_fragment,container,false);
        mTV = (TextView)view.findViewById(R.id.textView);
        mTV.setText(context);
        return view;
    }

}
