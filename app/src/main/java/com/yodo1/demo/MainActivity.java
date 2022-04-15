package com.yodo1.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.yodo1.advert.open.Yodo1Advert;
import com.yodo1.android.sdk.open.Yodo1Game;
import com.yodo1.android.sdk.kit.YLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunmeng
 */
public class MainActivity extends Yodo1SupportActivity implements View.OnClickListener {


    public List<Fragment> fragments = new ArrayList<Fragment>();
    private List<Integer> mainPagrIds = new ArrayList<Integer>();
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments.clear();
        mainPagrIds.clear();
        //main
        fragments.add(new FragmentMain());
        mainPagrIds.add(R.id.button_main);
        //login
        fragments.add(new FragmentLogin());
        mainPagrIds.add(R.id.button_login);
        //pay
        fragments.add(new FragmentPay());
        mainPagrIds.add(R.id.button_pay);
        //advert
        fragments.add(new FragmentAdvert());
        mainPagrIds.add(R.id.button_advert);
        //anti
        fragments.add(new FragmentAnti());
        mainPagrIds.add(R.id.button_anti);
        //other
        fragments.add(new FragmentOther());
        mainPagrIds.add(R.id.button_other);

        vp = findViewById(R.id.viewpager);
        vp.setOffscreenPageLimit(fragments.size());
        Fragment_Adapter fragmentAdapter = new Fragment_Adapter(this, fragments, mainPagrIds);
        vp.setAdapter(fragmentAdapter);
        for (Integer in : mainPagrIds) {
            View button = findViewById(in);
            button.setOnClickListener(this);
        }

        //开启Log
        Yodo1Game.initSDK(this, Config.APP_KEY, Config.REGION_CODE);
        Yodo1Advert.initSDK(this, Config.APP_KEY);

        //测试环境
//        Yodo1OPSBuilder.getInstance().setServerHost(0, "https://api-ucap-test.yodo1.com/uc_ap");
//        Yodo1OPSBuilder.getInstance().setServerHost(1, "https://api-payment-test.yodo1.com/payment");
//        Yodo1OPSBuilder.getInstance().setServerHost(6, "https://api-olconfig-test.yodo1.com");
//        Yodo1OnlineConfig.getInstance().setBuildEnvironment("https://api-olconfig-test.yodo1.com/config/getDataV2");


//预生产环境
//        Yodo1OPSBuilder.getInstance().setServerHost(1, "https://payment-stg.yodo1api.com/payment");
//        Yodo1OPSBuilder.getInstance().setServerHost(0, "https://uc-ap-stg.yodo1api.com/uc_ap");
//        Yodo1OPSBuilder.getInstance().setServerHost(6, "https://anti-stg.yodo1api.com");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int i = mainPagrIds.indexOf(id);
        vp.setCurrentItem(i, false);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        YLog.e("requestCode:" + requestCode + " permssion:" + permissions);
    }
}
