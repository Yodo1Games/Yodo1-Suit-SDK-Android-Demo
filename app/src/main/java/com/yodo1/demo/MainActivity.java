package com.yodo1.demo;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.yodo1.android.sdk.kit.YDeviceUtils;
import com.yodo1.android.sdk.open.Yodo1Game;

import org.json.JSONException;
import org.json.JSONObject;

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
        //uc
        fragments.add(new FragmentLogin());
        mainPagrIds.add(R.id.button_login);
        //iap
        fragments.add(new FragmentPay());
        mainPagrIds.add(R.id.button_pay);
        //analytics
        fragments.add(new FragmentAnalytics());
        mainPagrIds.add(R.id.button_analytics);

        vp = findViewById(R.id.viewpager);
        vp.setOffscreenPageLimit(fragments.size());
        Fragment_Adapter fragmentAdapter = new Fragment_Adapter(this, fragments, mainPagrIds);
        vp.setAdapter(fragmentAdapter);
        for (Integer in : mainPagrIds) {
            View button = findViewById(in);
            button.setOnClickListener(this);
        }

        JSONObject initConfig = new JSONObject();
        try {
            //value as Your Own Yodo1 GameKey.
            initConfig.put("appKey", "LFo9FuZSz");
            //value as a unique ID.eg:deviceId.
            initConfig.put("appsflyerCustomUserID", YDeviceUtils.getDeviceId(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Yodo1Game.initWithConfig(this, initConfig.toString());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int i = mainPagrIds.indexOf(id);
        vp.setCurrentItem(i, false);
    }
}
