package com.yodo1.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.yodo1.android.sdk.kit.YLog;
import com.yodo1.android.sdk.kit.YToastUtils;

/**
 * 实名制，和防沉迷。。通过实名，进行防沉迷。
 *
 * @author yodo1
 */
public class FragmentAnti extends Fragment implements View.OnClickListener {


    private static final String TAG = "[Anti_Addiction Fragment:] ";
    private View body;
    private Activity mContext;
    private EditText editText;


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

        body = inflater.inflate(R.layout.fragment_anti, container, false);
        editText = body.findViewById(R.id.et_protect_amount);
        body.findViewById(R.id.btn_protect_payment_check).setOnClickListener(this);
        body.findViewById(R.id.btn_protect_playtime_query).setOnClickListener(this);
        body.findViewById(R.id.btn_protect_payment_query).setOnClickListener(this);
        body.findViewById(R.id.btn_protect_query_template).setOnClickListener(this);
        body.findViewById(R.id.btn_protect_playtime_check).setOnClickListener(this);
        body.findViewById(R.id.btn_protect_open_indentifyUser).setOnClickListener(this);
        body.findViewById(R.id.btn_protect_clear).setOnClickListener(this);
        editText = body.findViewById(R.id.et_protect_amount);
        return body;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_protect_playtime_check:
                testInitProtect();
                break;
            case R.id.btn_protect_payment_check:
                //进行可付款金额验证
                Editable text = editText.getText();
                try {
                    double cent = Double.parseDouble(text.toString());
//                    Yodo1AntiAddiction.verifyPurchase(cent, "CNY", new Yodo1VerifyPurchaseCallback() {
//                        @Override
//                        public void onResult(boolean isAllow, String content) {
//                            String s = "verifyPurchase  isAllow:" + isAllow + "  content:" + content;
//                            YLog.i(TAG, s);
//                            ToastUtils.showToast(getActivity(), s);
//                        }
//                    });
                } catch (Exception e) {
                    YToastUtils.showToast(getActivity(), "金额输入有错误");
                }
                break;
            case R.id.btn_protect_playtime_query:
                //查询剩余游戏时间
//                Yodo1AntiAddiction.verifyCertificationInfo(getActivity(), Yodo1AccountHelper.getInstance().getUser().getYid(), new Yodo1CertificationCallback() {
//                    @Override
//                    public void onResult(Yodo1AntiAddictionEvent.EventAction action) {
//                        String s = "event:" + action;
//                        YLog.i(TAG, s);
//                        ToastUtils.showToast(getActivity(), s);
//                    }
//                });
                break;
            case R.id.btn_protect_open_indentifyUser:
                //打开实名制或年龄认证界面
//                Yodo1AntiAddiction.init(getActivity(), Config.APP_KEY, Config.REGION_CODE, new Yodo1AntiAddictionListener() {
//                    @Override
//                    public void onInitFinish(boolean result, String message) {
//                        String s = "onInitFinish  result:" + result + "  msg:" + message;
//                        YLog.i(TAG, s);
//                        ToastUtils.showToast(getActivity(), s);
//                    }
//
//                    @Override
//                    public void onTimeLimitNotify(Yodo1AntiAddictionEvent eventt, String event, String content) {
//                        String s = "onTimeLimitNotify event:" + eventt.getAction() + "  event:" + event + " content:" + content;
//                        YLog.i(TAG, s);
//                        ToastUtils.showToast(getActivity(), s);
//                    }
//
//                    @Override
//                    public void onPlayerDisconnection(String title, String content) {
//                        String s = "onPlayerDisconnection title:" + title + "  content:" + content;
//                        YLog.i(TAG, s);
//                        ToastUtils.showToast(getActivity(), s);
//                    }
//                });

                //TOOD 修改为测试环境
//                YLog.i(TAG, "test env.");
//                Yodo1AntiAddictionNets.getInstance().setEnv(Yodo1AntiAddictionNets.ENV_TEST);
                break;
            case R.id.btn_protect_payment_query:
                //查询剩余可支付金额
//                Yodo1AntiAddiction.online(new Yodo1UserBehaviourCallback() {
//                    @Override
//                    public void onBehaviourResult(boolean result, String content) {
//                        String s = "online  result:" + result + "  content:" + content;
//                        YLog.i(TAG, s);
//                        ToastUtils.showToast(getActivity(), s);
//                    }
//                });
                break;
            case R.id.btn_protect_query_template:
                //查询防沉迷规则
//                Yodo1AntiAddiction.offline(new Yodo1UserBehaviourCallback() {
//                    @Override
//                    public void onBehaviourResult(boolean result, String content) {
//                        String s = "offline  result:" + result + "  content:" + content;
//                        YLog.i(TAG, s);
//                        ToastUtils.showToast(getActivity(), s);
//                    }
//                });
                break;
            case R.id.btn_protect_clear:
                //清空配置
                YLog.i(TAG, "................blank");
                break;
            default:
                break;
        }
    }


    /**
     * 打开实名。
     */
    private void testInitProtect() {
        YLog.i(TAG, "................blank");
    }

}
