package com.yodo1.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.yodo1.android.dmp.AdapterAnalyzeBase;
import com.yodo1.android.dmp.Yodo1AnalyticsAdapterFactory;
import com.yodo1.android.sdk.Yodo1Builder;
import com.yodo1.android.sdk.kit.YSdkUtils;
import com.yodo1.android.sdk.open.Yodo1Game;
import com.yodo1.android.sdk.open.Yodo1GameUtils;
import com.yodo1.android.sdk.open.Yodo1UserCenter;
import com.yodo1.sdk.adapter.ChannelAdapterBase;
import com.yodo1.sdk.adapter.ChannelAdapterFactory;

import java.util.Map;

public class FragmentMain extends Fragment implements View.OnClickListener {

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
        infos.setOnClickListener(this);
        return body;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_content:
                break;
            default:
        }
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
        ps.println("App包名:               " + mContext.getPackageName());
        ps.println("App版本:               " + Yodo1GameUtils.getVersionName());
        ps.println("SDK版本:               " + Yodo1Game.getSDKVersion());
        ps.println("appKey:               " + Yodo1Builder.getInstance().getGameAppkey());
        ps.println("regionCode:           " + Yodo1Builder.getInstance().getRegionCode());
        ps.println("channelCode:          " + Yodo1Builder.getInstance().getChannelCode());
        ps.println("SDKType类型:           " + YSdkUtils.getSdkType(mContext));
        ps.println("GameType类型:          " + YSdkUtils.getSdkMode());
        ps.println("Channel sdk loaded:");
        Map<String, ChannelAdapterBase> allChannel = ChannelAdapterFactory.getInstance().getAllChannelAdapter();
        for (Map.Entry<String, ChannelAdapterBase> entry : allChannel.entrySet()) {
            ps.println("    " + entry.getKey() + "     v" + entry.getValue().getSDKVersion());
        }
        if (allChannel.isEmpty()) {
            ps.println("    null");
        }
        ps.println("Analytics sdk loaded:");
        Map<String, AdapterAnalyzeBase> adapters1 = Yodo1AnalyticsAdapterFactory.getInstance().getAdapters();
        for (Map.Entry<String, AdapterAnalyzeBase> entry : adapters1.entrySet()) {
            ps.println("    " + entry.getKey() + "     v" + entry.getValue().getSdkVersion());
        }
        if (adapters1.isEmpty()) {
            ps.println("    null");
        }
        ps.println("deviceId:" + Yodo1GameUtils.getDeviceId(mContext));
        ps.println("UserId:" + Yodo1GameUtils.getUserId());
        ps.println("defaultTerms:" + Yodo1GameUtils.getTermsLink());
        ps.println("defaultPrivacy:" + Yodo1GameUtils.getPolicyLink());
//        ps.println("appSignature:" + YAppUtils.getSignature(mContext));
        ps.println("");
        ps.println("是否登录：" + Yodo1UserCenter.isLogin());

        Log.e("zzzzz", "update Infos");
        return sb.toString();
    }
}