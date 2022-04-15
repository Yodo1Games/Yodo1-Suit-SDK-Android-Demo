package com.yodo1.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;


/**
 *
 */
public class FragmentAnalytics extends Fragment implements View.OnClickListener {

    private View body;
    private EditText editText;
    private Activity mContext;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context == null) {
            mContext = getActivity();
        } else {
            mContext = (Activity) context;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        body = inflater.inflate(R.layout.fragment_analytics, container, false);
        editText = body.findViewById(R.id.et_activation_code);
        body.findViewById(R.id.eventname).setOnClickListener(this);
        return body;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.eventname:
                break;
            default:
        }
    }
}
