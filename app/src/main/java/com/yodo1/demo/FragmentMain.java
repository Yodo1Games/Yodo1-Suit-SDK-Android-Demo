package com.yodo1.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.yodo1.advert.AdapterAdvertBase;
import com.yodo1.advert.factory.Yodo1AdvertAdapterFactory;
import com.yodo1.android.dmp.AdapterAnalyzeBase;
import com.yodo1.android.dmp.Yodo1AnalyticsAdapterFactory;
import com.yodo1.android.sdk.Yodo1Builder;
import com.yodo1.android.sdk.kit.YAppUtils;
import com.yodo1.android.sdk.kit.YEncodeUtil;
import com.yodo1.android.sdk.kit.YSdkUtils;
import com.yodo1.android.sdk.open.Yodo1Game;
import com.yodo1.android.sdk.open.Yodo1GameUtils;
import com.yodo1.bridge.api.Yodo1UserCenter;
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
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("PackageInfo");
                try {
                    builder.setMessage(updateInfos());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
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
        ps.println("app包名:               " + mContext.getPackageName());
        ps.println("app版本:               " + Yodo1GameUtils.getVersionName());
        ps.println("SDK版本:               " + Yodo1Game.getSDKVersion());
        ps.println("appKey:               " + Yodo1Builder.getInstance().getGameAppkey());
        ps.println("channelCode渠道名:     " + Yodo1Builder.getInstance().getChannelCode());
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
        ps.println("Advert sdk loaded:");
        Map<String, AdapterAdvertBase> adapters = Yodo1AdvertAdapterFactory.getInstance().getAdapters();
        for (Map.Entry<String, AdapterAdvertBase> entry : adapters.entrySet()) {
            ps.println("    " + entry.getKey() + "     v" + entry.getValue().getSdkVersion());
        }
        if (adapters.isEmpty()) {
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
        ps.println("appSignature:" + YEncodeUtil.MD5Encode(YEncodeUtil.getSHA(YAppUtils.getSignature(mContext)).replace("-", "")));
        ps.println("");
        ps.println("是否登录：" + Yodo1UserCenter.isLogin());

        Log.e("zzzzz", "update Infos");
        return sb.toString();
    }
}