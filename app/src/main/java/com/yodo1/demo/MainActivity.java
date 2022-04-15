package com.yodo1.demo;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.yodo1.android.sdk.open.Yodo1Game;

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

        //1,init SDK
        Yodo1Game.initSDK(this, Config.APP_KEY, Config.REGION_CODE);
        //2,setDebug if need.
        Yodo1Game.setDebug(true);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int i = mainPagrIds.indexOf(id);
        vp.setCurrentItem(i, false);
    }
}
