package com.yodo1.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.yodo1.android.sdk.callback.Yodo1AccountListener;
import com.yodo1.android.sdk.constants.LoginType;
import com.yodo1.android.sdk.helper.Yodo1AccountHelper;
import com.yodo1.android.sdk.kit.YLog;
import com.yodo1.android.sdk.kit.YToastUtils;
import com.yodo1.android.sdk.open.Yodo1Game;
import com.yodo1.android.sdk.open.Yodo1UserCenter;
import com.yodo1.sdk.adapter.callback.ChannelSDKCallback;
import com.yodo1.sdk.adapter.callback.Yodo1ResultCallback;
import com.yodo1.sdk.adapter.entity.User;

/**
 * login module
 *
 * @author yodo1
 */
public class FragmentLogin extends Fragment implements View.OnClickListener {

    private View body;
    private static final String TAG = "[FragmentLogin];";
    private Activity mContext;
    private TextView tv;
    private EditText loginparam;
    private Yodo1AccountListener listener = new Yodo1AccountListener() {
        @Override
        public void onLogin(LoginType type, Yodo1ResultCallback.ResultCode resultCode, int errorCode) {
            YLog.e(TAG + "onLogin, result = " + resultCode + ", errorCode = " + errorCode + ", type = " + type);
            if (resultCode == Yodo1ResultCallback.ResultCode.Success) {
                Toast.makeText(mContext, "Login Success", Toast.LENGTH_SHORT).show();


                submitUser();
            } else if (resultCode == Yodo1ResultCallback.ResultCode.Failed) {
                Toast.makeText(mContext, "Login Failed", Toast.LENGTH_LONG).show();
            } else if (resultCode == Yodo1ResultCallback.ResultCode.Cancel) {
                Toast.makeText(mContext, "Login Canceled", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onSwitchAccount(LoginType type, Yodo1ResultCallback.ResultCode resultCode, int errorCode) {
            YLog.i(TAG + "onSwitchAccount, result = " + resultCode + ", errorCode = " + errorCode + ", type = " + type);
        }

        @Override
        public void onLogout(Yodo1ResultCallback.ResultCode resultCode, int errorCode) {
            YLog.i(TAG + "onLogout, result = " + resultCode + ", errorCode = " + errorCode);
            Toast.makeText(mContext, "logout Success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRegist(Yodo1ResultCallback.ResultCode resultCode, int errorCode) {
            YLog.i(TAG + "onRegist, result = " + resultCode + ", errorCode = " + errorCode);
        }
    };


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

        body = inflater.inflate(R.layout.fragment_login, container, false);

        body.findViewById(R.id.btn_account_login).setOnClickListener(this);
        body.findViewById(R.id.btn_account_submit_user).setOnClickListener(this);
        body.findViewById(R.id.btn_account_change).setOnClickListener(this);
        body.findViewById(R.id.btn_account_logout).setOnClickListener(this);
        body.findViewById(R.id.btn_exit).setOnClickListener(this);
        body.findViewById(R.id.btn_leaderboard).setOnClickListener(this);
        body.findViewById(R.id.btn_ach).setOnClickListener(this);
        body.findViewById(R.id.btn_community).setOnClickListener(this);
        tv = body.findViewById(R.id.contentView);
        loginparam = body.findViewById(R.id.loginparam);
        Yodo1UserCenter.setListener(listener);
        return body;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_account_login:
                login();
                break;
            case R.id.btn_account_submit_user:
                submitUser();
                break;
            case R.id.btn_account_change:
                changeAccount();
                break;
            case R.id.btn_account_logout:
                logout();
                break;
            case R.id.btn_exit:
                exitGame();
                break;
            case R.id.btn_leaderboard:
                Yodo1UserCenter.openLeaderboards(mContext);
                break;
            case R.id.btn_ach:
                Yodo1UserCenter.achievementsOpen(mContext);
                break;
            case R.id.btn_community:
                Yodo1Game.openCommunity(mContext, new ChannelSDKCallback() {
                    @Override
                    public void onResult(int status, int errorCode, String params) {

                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && tv != null) {
            tv.setText(updateInfos());
        }
    }

    private String updateInfos() {
        StringBuilder sb = new StringBuilder();
        sb.append("isLogin:" + Yodo1UserCenter.isLogin());
        sb.append("\n");
        User user = Yodo1UserCenter.getUser();
        if (user == null) {
            sb.append("user 为空。");
        } else {
            sb.append("age:" + user.getAge());
            sb.append("\n");
            sb.append("From:" + user.getFrom());
            sb.append("\n");
            sb.append("NickName:" + user.getNickName());
            sb.append("\n");
            sb.append("PlayerId:" + user.getPlayerId());
            sb.append("\n");
            sb.append("OpsUid:" + user.getOpsUid());
            sb.append("\n");
            sb.append("OpsToken:" + user.getOpsToken());
            sb.append("\n");
            sb.append("level:" + user.getLevel());
            sb.append("\n");
            sb.append("thirdPartyUid:" + user.getThirdpartyUid());
            sb.append("\n");
            sb.append("thirdPartyToken:" + user.getThirdpartyToken());
            sb.append("\n");
            sb.append("Gender:" + user.getGender());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (tv != null) {
            tv.setText(updateInfos());
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (tv != null) {
            tv.setText(updateInfos());
        }
    }


    /**
     * 登录
     */
    private void login() {
        Yodo1UserCenter.login(mContext);
//        String s = loginparam.getText().toString();
//        Yodo1UserCenter.login(mContext, LoginType.Channel, s);
    }

    /**
     * 提交userInfo
     */
    private void submitUser() {
        User user = Yodo1AccountHelper.getInstance().getUser().getUser();
        user.setPlayerId("Test_PlayId");
        user.setLevel(1);
        user.setNickName("Test_RoleId");
        user.setGameServerId("Test_ServerId");
        Yodo1UserCenter.sumbitUser(mContext, user);
    }

    /**
     * 切换登录
     */
    private void changeAccount() {
        Yodo1UserCenter.changeAccount(mContext);
    }

    /**
     * 登出
     */
    private void logout() {
        YLog.e("isLogin" + Yodo1UserCenter.isLogin());
        if (Yodo1UserCenter.isLogin()) {
            Yodo1UserCenter.logout(mContext);
        }
    }


    /**
     * 退出游戏
     */
    private void exitGame() {
        Yodo1Game.exit(mContext, new ChannelSDKCallback() {
            @Override
            public void onResult(int status, int errorCode, String params) {
                if (status == ChannelSDKCallback.STATUS_SUCCESS) {
                    System.exit(0);
                } else {
                    YToastUtils.showToast(mContext, "cancel.");
                }
            }
        });
    }
}
