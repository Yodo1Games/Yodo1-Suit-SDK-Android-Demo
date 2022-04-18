package com.yodo1.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.yodo1.android.sdk.open.Yodo1Analytics;


/**
 * Analytics Part.
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
        body.findViewById(R.id.appsflyer).setOnClickListener(this);
        return body;
    }

    @Override
    public void onClick(View v) {
        String ets = editText.getText().toString();
        switch (v.getId()) {
            case R.id.eventname:
                Yodo1Analytics.onCustomEvent(mContext, ets);
//                Yodo1Analytics.onCustomEvent(ets,jsonData);

//                Yodo1Analytics.onMissionBegion(missionId);
//                Yodo1Analytics.onMissionCompleted(missionid);
//                Yodo1Analytics.onMissionFailed(missionid,cause);
//                Yodo1Analytics.onReward(gamecoin,trigger,reason);
//                Yodo1Analytics.onUseItem(itemid,useNum,gamecoin);
                break;
            case R.id.appsflyer:
                Yodo1Analytics.onCustomEventAppsflyer("test", null);
                break;
            default:
        }
    }
}
