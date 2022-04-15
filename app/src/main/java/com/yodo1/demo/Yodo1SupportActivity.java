package com.yodo1.demo;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.appcompat.app.AppCompatActivity;

import com.yodo1.android.sdk.kit.YLog;
import com.yodo1.android.sdk.open.Yodo1Game;
import com.yodo1.bridge.api.Yodo1BridgeUtils;

/**
 * Created by houjingsheng on 2017/4/24.
 */
public class Yodo1SupportActivity extends AppCompatActivity {

    public final String TAG = "Yodo1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        YLog.d(TAG, " onCreate ...Yodo1 Suit Version:" + Yodo1Game.getSDKVersion());
        Yodo1BridgeUtils.gamesdk().onActivityCreate(this);
        Yodo1BridgeUtils.onActivityCreated(this, savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Yodo1BridgeUtils.gamesdk().onActivityStart(this);
        Yodo1BridgeUtils.onActivityStarted(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Yodo1BridgeUtils.gamesdk().onActivityResume(this);
        Yodo1BridgeUtils.onActivityResumed(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Yodo1BridgeUtils.gamesdk().onActivityRestart(this);
        Yodo1BridgeUtils.onActivityRestart(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Yodo1BridgeUtils.gamesdk().onConfigurationChanged(this, newConfig);
        Yodo1BridgeUtils.onActivityConfigurationChanged(this, newConfig);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Yodo1BridgeUtils.gamesdk().onActivityPause(this);
        Yodo1BridgeUtils.onActivityPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Yodo1BridgeUtils.gamesdk().onActivityStop(this);
        Yodo1BridgeUtils.onActivityStopped(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Yodo1BridgeUtils.gamesdk().onActivityDestroy(this);
        Yodo1BridgeUtils.onActivityDestroyed(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Yodo1BridgeUtils.gamesdk().onActivityNewIntent(this, intent);
        Yodo1BridgeUtils.onActivityNewIntent(this, intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Yodo1BridgeUtils.gamesdk().onActivityResult(this, requestCode, resultCode, data);
        Yodo1BridgeUtils.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Yodo1BridgeUtils.gamesdk().onActivityRequestPermissionsResult(this, requestCode, permissions, grantResults);
        Yodo1BridgeUtils.onActivityRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Yodo1BridgeUtils.gamesdk().onSaveInstanceState(this, outState, outPersistentState);
        Yodo1BridgeUtils.onActivitySaveInstanceState(this, outState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Yodo1BridgeUtils.gamesdk().onBackPressed(this);
        Yodo1BridgeUtils.onActivityBackPress(this);
    }
}