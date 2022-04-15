package com.yodo1.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.yodo1.advert.callback.BannerCallback;
import com.yodo1.advert.callback.InterstitialCallback;
import com.yodo1.advert.callback.NativeCallback;
import com.yodo1.advert.callback.VideoCallback;
import com.yodo1.advert.entity.AdErrorCode;
import com.yodo1.advert.open.Yodo1Advert;
import com.yodo1.android.sdk.kit.YLog;
import com.yodo1.android.sdk.onlineconfig.Yodo1OnlineConfig;
import com.yodo1.android.sdk.onlineconfig.Yodo1OnlineConfigListener;

import org.json.JSONObject;

/**
 * 广告Fragmetn.
 *
 * @author yodo1
 */
public class FragmentAdvert extends Fragment implements View.OnClickListener {

    private View body;
    private Activity mContext;
    private TextView infos;

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
        body = inflater.inflate(R.layout.fragment_advert, container, false);

        body.findViewById(R.id.btn_advert_show_interstitial).setOnClickListener(this);
        body.findViewById(R.id.btn_advert_show_video).setOnClickListener(this);
        body.findViewById(R.id.btn_advert_show_banner).setOnClickListener(this);
        body.findViewById(R.id.btn_advert_hide_banner).setOnClickListener(this);
        body.findViewById(R.id.btn_advert_show_native).setOnClickListener(this);
        body.findViewById(R.id.btn_advert_hide_native).setOnClickListener(this);
        body.findViewById(R.id.btn_advert_update).setOnClickListener(this);
        infos = body.findViewById(R.id.edittext_advert_infos);

        return body;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_advert_show_interstitial:
                showInterstitialAd();
                break;
            case R.id.btn_advert_show_video:
                showVideoAd();
                break;
            case R.id.btn_advert_show_banner:
                showBannerAd();
                break;
            case R.id.btn_advert_hide_banner:
                Yodo1Advert.hideBanner(mContext);
                break;
            case R.id.btn_advert_show_native:
                showNativeAd();
                break;
            case R.id.btn_advert_hide_native:
                Yodo1Advert.removeNative(mContext);
                break;
            case R.id.btn_advert_update:
                Toast.makeText(mContext, "调用Config.json更新(20分钟拉取一次,不到时间不拉)", Toast.LENGTH_SHORT).show();
                Yodo1OnlineConfig.getInstance().getOnlineConfig(mContext, new Yodo1OnlineConfigListener() {
                    @Override
                    public void getDataFinish(int code, String onlineConfig) {
                        Toast.makeText(mContext, "Config拉去更新.", Toast.LENGTH_SHORT).show();
                        Log.e("zzzzzzzzz", onlineConfig);
                        if (infos != null) {
                            infos.setText(updateInfos());
                        }
                    }
                });
                break;
            default:
        }
    }

    /**
     * 插屏广告
     */
    private void showInterstitialAd() {
        if (!Yodo1Advert.interstitialIsReady(mContext)) {
            Toast.makeText(mContext, "插屏广告尚未缓存成功", Toast.LENGTH_LONG).show();
        } else {
            Yodo1Advert.showInterstitial(mContext, new InterstitialCallback() {
                @Override
                public void onInterstitialClosed() {
                    Toast.makeText(mContext, "插屏关闭", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onInterstitialShowSucceeded() {
                    Toast.makeText(mContext, "插屏展示成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onInterstitialShowFailed(AdErrorCode errorCode) {
                    YLog.e("Show Interstitial Failed, Error: " + errorCode.name());
                    Toast.makeText(mContext, "插屏展示失败, Error: " + errorCode.name(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onInterstitialClicked() {
                    Toast.makeText(mContext, "插屏点击", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * banner广告
     */
    private void showBannerAd() {
        Yodo1Advert.showBanner(mContext, new BannerCallback() {
            @Override
            public void onBannerClosed() {
                Toast.makeText(mContext, "Banner关闭", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onBannerShow() {
                Toast.makeText(mContext, "Banner播放成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onBannerShowFailed(AdErrorCode errorCode) {
                YLog.e("Show Banner Failed, Error: " + errorCode.name());
                Toast.makeText(mContext, "Banner展示失败，Error: " + errorCode.name(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onBannerClicked() {
                Toast.makeText(mContext, "Banner点击", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * video广告
     */
    private void showVideoAd() {
        if (!Yodo1Advert.videoIsReady(mContext)) {
            Toast.makeText(mContext, "视频广告尚未缓存成功", Toast.LENGTH_LONG).show();
        } else {
            Yodo1Advert.showVideo(mContext, new VideoCallback() {
                @Override
                public void onVideoClosed(boolean isFinished) {
                    if (isFinished) {
                        Toast.makeText(mContext, "视频完整播放", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(mContext, "视频未完整播放", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onVideoShow() {
                    Toast.makeText(mContext, "视频展示成功", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onVideoShowFailed(AdErrorCode errorCode) {
                    YLog.e("Show Video Failed, Error: " + errorCode.name());
                    Toast.makeText(mContext, "视频展示失败，Error: " + errorCode, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onVideoClicked() {
                    Toast.makeText(mContext, "视频点击", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    /**
     * Native广告
     */
    private void showNativeAd() {
        WindowManager manager = mContext.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = 200;

        Yodo1Advert.showNative(mContext, 0, 0, width, height, new NativeCallback() {
            @Override
            public void onNativeClosed() {
                Toast.makeText(mContext, "关闭Native广告", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNativeShow() {
                Toast.makeText(mContext, "展示Native广告", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNativeShowFailed(AdErrorCode errorCode) {
                Toast.makeText(mContext, "展示Native广告失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNativeClicked() {
                Toast.makeText(mContext, "点击Native广告", Toast.LENGTH_LONG).show();
            }
        });
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

    private String updateInfos() {
        JSONObject jsonData = null;
        try {
            jsonData = Yodo1OnlineConfig.getInstance().getJsonData(mContext);
            return "jsonData.toString()";
        } catch (Exception e) {
            return "null";
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (infos != null) {
            infos.setText(updateInfos());
        }
    }
}
