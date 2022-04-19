package com.yodo1.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.yodo1.android.sdk.Yodo1Builder;
import com.yodo1.android.sdk.kit.YSdkUtils;
import com.yodo1.android.sdk.open.Yodo1Game;
import com.yodo1.android.sdk.open.Yodo1GameUtils;

public class FragmentMain extends Fragment {

    private Activity mContext;
    private View body;
    private TextView infos;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        body = inflater.inflate(R.layout.fragment_main, container, false);

        infos = body.findViewById(R.id.info_content);
        return body;
    }
    
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && infos != null) {
            infos.setText(updateInfos());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (infos != null) {
            infos.setText(updateInfos());
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (infos != null) {
            infos.setText(updateInfos());
        }
    }

    private String updateInfos() {
        StringBuilder sb = new StringBuilder();
        StringBuilderPrinter ps = new StringBuilderPrinter(sb);
        ps.println(getString(R.string.app_package_name) + " " + mContext.getPackageName());
        ps.println(getString(R.string.app_version) + " " + Yodo1GameUtils.getVersionName());
        ps.println(getString(R.string.app_store_code) + " " + Yodo1Builder.getInstance().getChannelCode());
        ps.println(getString(R.string.yodo1_app_key) + " " + Yodo1Builder.getInstance().getGameAppkey());
        ps.println(getString(R.string.sdk_version) + " " + Yodo1Game.getSDKVersion());
        ps.println(getString(R.string.sdk_type) + " " + YSdkUtils.getSdkType(mContext));
        ps.println("\n");
        ps.println("deviceId: " + Yodo1GameUtils.getDeviceId(mContext));
        ps.println("UserId: " + Yodo1GameUtils.getUserId());
        ps.println("defaultTerms: " + Yodo1GameUtils.getTermsLink());
        ps.println("defaultPrivacy: " + Yodo1GameUtils.getPolicyLink());
        return sb.toString();
    }
}