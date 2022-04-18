package com.yodo1.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.yodo1.android.sdk.kit.YLog;
import com.yodo1.android.sdk.onlineconfig.YOnlineConfigUtils;


/**
 * other infos.
 *
 * @author yodo1
 */
public class FragmentOther extends Fragment implements View.OnClickListener {

    private View body;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        body = inflater.inflate(R.layout.fragment_other, container, false);
        body.findViewById(R.id.getonlineconfig).setOnClickListener(this);
        return body;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.getonlineconfig) {
            String key = YOnlineConfigUtils.getYodo1OnlineConfigParams("key");
            YLog.e(key);
        }
    }
}
