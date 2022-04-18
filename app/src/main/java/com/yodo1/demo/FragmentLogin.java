package com.yodo1.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.yodo1.android.sdk.callback.Yodo1AccountListener;
import com.yodo1.android.sdk.constants.LoginType;
import com.yodo1.android.sdk.kit.YLog;
import com.yodo1.android.sdk.open.Yodo1UserCenter;
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
    private Yodo1AccountListener listener = new Yodo1AccountListener() {
        @Override
        public void onLogin(LoginType type, Yodo1ResultCallback.ResultCode resultCode, int errorCode) {
            YLog.e(TAG + "onLogin, result = " + resultCode + ", errorCode = " + errorCode + ", type = " + type);
            if (resultCode == Yodo1ResultCallback.ResultCode.Success) {
                Toast.makeText(mContext, "Login Success", Toast.LENGTH_SHORT).show();
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
        body.findViewById(R.id.btn_account_logout).setOnClickListener(this);
        tv = body.findViewById(R.id.contentView);
        Yodo1UserCenter.setListener(listener);
        return body;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_account_login:
                /**
                 * 登录
                 */
                Yodo1UserCenter.login(mContext);
                break;
            case R.id.btn_account_logout:
                /**
                 * 登出
                 */
                if (Yodo1UserCenter.isLogin()) {
                    Yodo1UserCenter.logout(mContext);
                }
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
        User user = Yodo1UserCenter.getUser();
        return user.toString();
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
}
