package com.yodo1.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.yodo1.android.sdk.callback.Yodo1UserContentCallback;
import com.yodo1.android.sdk.callback.Yodo1VerifyCodeCallback;
import com.yodo1.android.sdk.kit.YLog;
import com.yodo1.android.sdk.kit.YToastUtils;
import com.yodo1.android.sdk.onlineconfig.YOnlineConfigUtils;
import com.yodo1.android.sdk.open.Yodo1Game;
import com.yodo1.android.sdk.open.Yodo1GameUtils;
import com.yodo1.sdk.adapter.callback.Yodo1ResultCallback;
import com.yodo1.sdk.share.callback.Yodo1ShareCallback;
import com.yodo1.sdk.share.constants.Yodo1SNSType;
import com.yodo1.sdk.share.entry.ChannelShareInfo;

import org.json.JSONObject;

import java.util.HashMap;


/**
 * other infos.
 *
 * @author yodo1
 */
public class FragmentOther extends Fragment implements View.OnClickListener {

    private View body;
    private EditText activiCode;
    private Activity mContext;

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

        body = inflater.inflate(R.layout.fragment_other, container, false);

        activiCode = body.findViewById(R.id.et_activation_code);
        body.findViewById(R.id.btn_activation_code).setOnClickListener(this);
        body.findViewById(R.id.btn_share).setOnClickListener(this);
        body.findViewById(R.id.userprivacy).setOnClickListener(this);
        body.findViewById(R.id.useragreement).setOnClickListener(this);
        body.findViewById(R.id.userprivacyterms).setOnClickListener(this);
        body.findViewById(R.id.openWeb).setOnClickListener(this);
        body.findViewById(R.id.showsavestore).setOnClickListener(this);
        body.findViewById(R.id.getonlineconfig).setOnClickListener(this);
        return body;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_activation_code:
                verifyActivationCode();
                break;
            case R.id.btn_share:
                share();
                break;
            case R.id.userprivacy:
                Yodo1Game.showUserConsent(new Yodo1UserContentCallback() {
                    @Override
                    public void onResult(boolean open_switch, int age, int limitAge, String term, String term_version, String privacy, String privacy_version) {
                        YLog.e("showUserConsent onResult", "" + open_switch + age + limitAge + term);
                    }
                });
                break;
            case R.id.useragreement:
                String termsLink = Yodo1GameUtils.getTermsLink();
                Yodo1GameUtils.openWebPage(termsLink, null);
                break;
            case R.id.userprivacyterms:
                String policayLink = Yodo1GameUtils.getPolicyLink();
                Yodo1GameUtils.openWebPage(policayLink, null);
                break;
            case R.id.showsavestore:
                String gameCenter = Yodo1GameUtils.getNativeRuntime("gameCenter");
                String deeplink = Yodo1GameUtils.getNativeRuntime("deeplink");
                String appsflyer_id = Yodo1GameUtils.getNativeRuntime("appsflyer_id");
                String attribution_id = Yodo1GameUtils.getNativeRuntime("attribution_id");
                YToastUtils.showToast(mContext, "gameCenter:" + gameCenter + " deeplink:" + deeplink + " appsflyer_id:" + appsflyer_id + " attribution_id:" + attribution_id);
                break;
            case R.id.openWeb:
                HashMap<String, String> maps = new HashMap<>();
//                maps.put("hideActionBar", "true");
//                maps.put("isCloseTouchOutSide","true");
                maps.put("isDialog", "true");
                String link = Yodo1GameUtils.getTermsLink();
                Yodo1GameUtils.openWebPage(link, new JSONObject(maps).toString());
                break;
            case R.id.getonlineconfig:
                String key = YOnlineConfigUtils.getYodo1OnlineConfigParams("key");
                YLog.e(key);
                break;
            default:
        }
    }


    /**
     * 分享
     */
    private void share() {
        String SDCARD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
        String path = SDCARD_ROOT + "/a123.jpg";
        ChannelShareInfo info = new ChannelShareInfo();

        info.setImgPath(path);
        info.setIconName("sharelogo.png");
        info.setQrShareLogo("sharelogo.png");
        info.setQrText("长按二维码");
        info.setNeedCompositeImg(true);
        info.setShareUrl("https://docs.yodo1.com/#/");
        info.setIsSharePic(true);

        Yodo1Game.share(mContext, info, new Yodo1ShareCallback() {
            @Override
            public void onResult(int status, String msg, Yodo1SNSType type) {
                YLog.e("share onResult", status + msg);
            }
        });
    }

    /**
     * 校验激活码
     */
    private void verifyActivationCode() {
        Yodo1Game.verifyActivationcode(activiCode.getText().toString(), new Yodo1VerifyCodeCallback() {
            @Override
            public void onResult(Yodo1ResultCallback.ResultCode resultCode, int errorCode, String errorMsg, JSONObject reward, String rewardDes) {
                if (resultCode == Yodo1ResultCallback.ResultCode.Success) {
                    YLog.e("verifyActivationCode Success", errorCode + errorMsg + reward + rewardDes);
                } else {
                    YLog.e("verifyActivationCode No Success", errorCode + errorMsg + reward + rewardDes);
                }
            }
        });
    }
}
