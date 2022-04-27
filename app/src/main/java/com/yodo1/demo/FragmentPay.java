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

import com.yodo1.android.sdk.callback.Yodo1PurchaseListener;
import com.yodo1.android.sdk.constants.PayType;
import com.yodo1.android.sdk.helper.ProductData;
import com.yodo1.android.sdk.helper.Yodo1ProductFactory;
import com.yodo1.android.sdk.kit.YLog;
import com.yodo1.android.sdk.kit.YToastUtils;
import com.yodo1.android.sdk.open.Yodo1Purchase;

import java.util.ArrayList;
import java.util.List;

/**
 * pay module
 *
 * @author yodo1
 */
public class FragmentPay extends Fragment implements View.OnClickListener {

    private Activity mContext;
    private View body;
    private static final String TAG = "[FragmentPay] ";
    private EditText editText;
    private String lastSuccessOrderId = "O20200602135532D2LLUKQ";
    private TextView content;

    private Yodo1PurchaseListener payListener = new Yodo1PurchaseListener() {
        @Override
        public void purchased(int code, String orderId, ProductData productData, PayType payType) {
            String msg = "purchased, code = " + code + ", orderId = " + orderId;
            YLog.e(TAG + msg);

            if (code == Yodo1PurchaseListener.ERROR_CODE_SUCCESS) {
                Toast.makeText(mContext, "Pay Success,send Goods", Toast.LENGTH_SHORT).show();
                lastSuccessOrderId = orderId;
            } else if (code == Yodo1PurchaseListener.ERROR_CODE_FAIELD) {
                Toast.makeText(mContext, "Pay Failed", Toast.LENGTH_LONG).show();
            } else if (code == Yodo1PurchaseListener.ERROR_CODE_MISS_ORDER) {
                Toast.makeText(mContext, "Pay Miss Order", Toast.LENGTH_LONG).show();
            } else if (code == Yodo1PurchaseListener.ERROR_CODE_CANCEL) {
                Toast.makeText(mContext, "Pay Canceled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(mContext, "Code:" + code + " orderId:" + orderId, Toast.LENGTH_LONG).show();
            }

            //send order status after purchase callback.
            if (code == Yodo1PurchaseListener.ERROR_CODE_SUCCESS) {
                Yodo1Purchase.sendGoods(new String[]{orderId});
            } else {
                Yodo1Purchase.sendGoodsFail(new String[]{orderId});
            }
        }

        @Override
        public void queryProductInfo(int code, List<ProductData> products) {
            int size = products == null ? 0 : products.size();
            String msg = "queryProductInfo, code = " + code + ", products.size = " + size + ", list:" + products;
            YLog.e(TAG + msg);
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            if (products != null) {
                for (int i = 0; i < products.size(); i++) {
                    ProductData product = products.get(i);
                    YLog.i(TAG + "queryProductInfo, index: " + i + ", product info: [" + product.toString() + "]");
                }
            }
        }

        @Override
        public void queryMissOrder(int code, List<ProductData> products) {
            int size = products == null ? 0 : products.size();
            String msg = "queryMissOrder, code = " + code + ", products.size = " + size;
            YLog.e(TAG + msg);
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            List<String> orders = new ArrayList<String>();
            if (products != null) {
                for (int i = 0; i < products.size(); i++) {
                    ProductData product = products.get(i);
                    orders.add(product.getOrderId());
                    YLog.e(TAG + "queryMissOrder, index: " + i + ", product info: [" + product.toString() + "]");
                }

                //send order status after missOrder callback.
                Yodo1Purchase.sendGoods((String[]) orders.toArray());
            }
        }

        @Override
        public void restorePurchased(int code, List<ProductData> products) {
            int size = products == null ? 0 : products.size();
            String msg = "restorePurchased, code = " + code + ", products.size = " + size;
            YLog.e(TAG + msg);
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            if (products != null) {
                for (int i = 0; i < products.size(); i++) {
                    ProductData product = products.get(i);
                    YLog.i(TAG + "restorePurchased, index: " + i + ", product info: [" + product.toString() + "]");
                }
            }
        }

        @Override
        public void inAppVerifyPurchased(int code, List<ProductData> products) {

        }

        @Override
        public void querySubscriptions(int code, long serverTime, List<ProductData> subscriptions) {

        }

        @Override
        public void sendGoods(int code, String message) {
            YToastUtils.showToast(mContext, "sendGoods  Code:" + code + " message:" + message);
        }

        @Override
        public void sendGoodsFail(int code, String message) {
            YToastUtils.showToast(mContext, "sendGoodsFail  Code:" + code + " message:" + message);
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

        body = inflater.inflate(R.layout.fragment_pay, container, false);

        editText = body.findViewById(R.id.et_payment_product_id);
        editText.setText("iap_few_coins");
        body.findViewById(R.id.btn_payment_purchase).setOnClickListener(this);
        body.findViewById(R.id.btn_payment_query_products).setOnClickListener(this);
        body.findViewById(R.id.btn_payment_query_subscription_products).setOnClickListener(this);
        body.findViewById(R.id.btn_payment_query_miss_orders).setOnClickListener(this);
        body.findViewById(R.id.btn_payment_restore_purchase).setOnClickListener(this);
        body.findViewById(R.id.btn_payment_sendgoods).setOnClickListener(this);
        body.findViewById(R.id.btn_payment_sendgoodsfail).setOnClickListener(this);
        body.findViewById(R.id.iap_verofu).setOnClickListener(this);
        content = body.findViewById(R.id.content);

        List<ProductData> products = Yodo1ProductFactory.getInstance().getProducts();
        StringBuilder sb = new StringBuilder();
        for (ProductData p : products) {
            sb.append("proId:");
            sb.append(p.getProductId());
            sb.append("，");
            sb.append("isRepeat:");
            sb.append(p.isRepeated());
            sb.append("\n");
        }
        content.setText(sb.toString());
        return body;
    }

    @Override
    public void onClick(View v) {
        com.yodo1.android.sdk.open.Yodo1Purchase.init(payListener);
        switch (v.getId()) {
            case R.id.iap_verofu:
                Yodo1Purchase.inAppVerify(mContext);
                break;
            case R.id.btn_payment_purchase:
                Yodo1Purchase.pay(mContext, editText.getText().toString());
                break;
            case R.id.btn_payment_query_products:
                Yodo1Purchase.queryProducts(mContext);
                break;
            case R.id.btn_payment_sendgoods:
                Yodo1Purchase.sendGoods(new String[]{lastSuccessOrderId});
                break;
            case R.id.btn_payment_sendgoodsfail:
                Yodo1Purchase.sendGoodsFail(new String[]{lastSuccessOrderId});
                break;
            case R.id.btn_payment_query_subscription_products:
                Yodo1Purchase.querySubscriptions(mContext);
                break;
            case R.id.btn_payment_query_miss_orders:
                Yodo1Purchase.queryMissOrder(mContext);
                break;
            case R.id.btn_payment_restore_purchase:
                Yodo1Purchase.restoreProduct(mContext);
                break;
            default:
                break;
        }
    }

}
