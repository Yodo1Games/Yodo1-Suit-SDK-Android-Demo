package com.yodo1.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.yodo1.android.sdk.kit.YLog;
import com.yodo1.android.sdk.kit.YToastUtils;
import com.yodo1.android.sdk.open.Yodo1Game;
import com.yodo1.android.sdk.unity.UnityYodo1SDK;
import com.yodo1.bridge.api.Yodo1Analytics;
import com.yodo1.bridge.api.Yodo1GameUtils;
import com.yodo1.bridge.api.Yodo1Purchase;

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
        body.findViewById(R.id.useragreement).setOnClickListener(this);
        body.findViewById(R.id.moregame).setOnClickListener(this);
        body.findViewById(R.id.opemcommunity).setOnClickListener(this);
        body.findViewById(R.id.openWeb).setOnClickListener(this);
        body.findViewById(R.id.openbbs).setOnClickListener(this);
        body.findViewById(R.id.showsavestore).setOnClickListener(this);
        body.findViewById(R.id.iap_verofu).setOnClickListener(this);
        body.findViewById(R.id.vivo).setOnClickListener(this);
        body.findViewById(R.id.bridge).setOnClickListener(this);
        body.findViewById(R.id.feedback).setOnClickListener(this);
        return body;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_activation_code:
                verifyActivationCode();
                break;
            case R.id.iap_verofu:
                Yodo1Purchase.verifyPurchases("a", "b");
                break;
            case R.id.btn_share:
                share();
                break;
            case R.id.useragreement:
                Yodo1Game.showUserConsent(null);
                break;
            case R.id.moregame:
//                UnityYodo1Utils.moreGame();
                Yodo1Game.moreGame(mContext);
                break;
            case R.id.opemcommunity:
//                UnityYodo1Utils.openCommunity();
                Yodo1Game.openCommunity(mContext, null);
                break;
            case R.id.showsavestore:
                String gameCenter = Yodo1GameUtils.getNativeRuntime("gameCenter");
                String deeplink = Yodo1GameUtils.getNativeRuntime("deeplink");
                String appsflyer_id = Yodo1GameUtils.getNativeRuntime("appsflyer_id");
                String attribution_id = Yodo1GameUtils.getNativeRuntime("attribution_id");
                YToastUtils.showToast(mContext, "gameCenter:" + gameCenter + " deeplink:" + deeplink + " appsflyer_id:" + appsflyer_id + " attribution_id:" + attribution_id);
                break;
            case R.id.vivo:
//                Intent intent = new Intent();
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("vivogame://game.vivo.com/openjump?j_type=1&pkgName=com.yodo1.rodeo.VIVO&t_from=Privilege_com.yodo1.rodeo.VIVO"));
//                startActivity(intent);

                Yodo1GameUtils.openWebPage("vivogame://game.vivo.com/openjump?j_type=1&pkgName=com.yodo1.rodeo.VIVO&t_from=Privilege_com.yodo1.rodeo.VIVO");
                break;
            case R.id.openWeb:
                HashMap<String, String> maps = new HashMap<>();
//                maps.put("hideActionBar", "true");
//                maps.put("isCloseTouchOutSide","true");
                maps.put("isDialog", "true");
                String termsLink = Yodo1GameUtils.getTermsLink();
//                String policyLink = UnityYodo1Utils.getPolicyLink();
                Yodo1GameUtils.openWebPage(termsLink, new JSONObject(maps).toString());
                break;
            case R.id.openbbs:
                Yodo1Game.openBBS(mContext);
                break;
            case R.id.feedback:
                Yodo1Game.openFeedback(mContext);
                break;
            case R.id.bridge:
                YToastUtils.showToast(mContext, "brigeBtn Clicked.");
                String ni = Yodo1Analytics.getStringParams("ni", "women");
                YLog.e(ni);
                break;
            default:
        }
    }


    /**
     * 分享
     */
    private void share() {
//        String SDCARD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
//        String path = SDCARD_ROOT + "/a123.jpg";
////        String path = SDCARD_ROOT + "/a234.jpg";
//        ChannelShareInfo info = new ChannelShareInfo();
//
//        info.setImgPath(path);
//        info.setIconName("sharelogo.png");
//        info.setQrShareLogo("sharelogo.png");
//        info.setQrText("长按二维码");
//        info.setNeedCompositeImg(true);
//        info.setShareUrl("https://docs.yodo1.com/#/");
//        info.setIsSharePic(true);
//
//        Yodo1ShareLocal.share(mContext, info, new Yodo1ShareCallback() {
//            @Override
//            public void onResult(int status, String msg, Yodo1SNSType type) {
//
//            }
//        });

        String jsonStr = "{\"snsType\":127,\"title\":\"\",\"desc\":\"\",\"image\":\"/storage/emulated/0/DCIM/Camera/-43135a7f6d949bbd.jpg\",\"url\":\"https://play.google.com/store/apps/details?id=com.yodo1.rodeo.safari\",\"qrLogo\":\"-43135a7f6d949bbd.jpg\",\"qrText\":\"\\u957f\\u6309\\u8bc6\\u522b\\u4e8c\\u7ef4\\u7801\\n\\u53e6\\u7c7b\\u8dd1\\u9177\\u8650\\u5fc3\\u6218\",\"qrTextX\":0,\"qrImageX\":20,\"gameLogo\":\"gameLogo_en.png\",\"gameLogoX\":0,\"composite\":true}";
        UnityYodo1SDK sdk = new UnityYodo1SDK();
        sdk.share("Demo", "share", jsonStr);

        jsonStr = "com.facebook.katana";
//        boolean avilible = ShareAdapterAndroidSystem.isAvilible(getActivity(), jsonStr);
        YToastUtils.showToast(getActivity(), "是否已经安装" + jsonStr + " : ");
    }

    /**
     * 校验激活码
     */
    private void verifyActivationCode() {
        Yodo1GameUtils.verifyActivationcode(activiCode.getText().toString(), "a", "b");
    }
}
