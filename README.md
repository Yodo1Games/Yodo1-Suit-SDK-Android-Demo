# Yodo1-Suit-SDK-Android-Demo

## Overview

### Check the [documentation](https://github.com/Yodo1Games/Suit-Document/blob/master/README.md) to get started on integration.

## Demo App
To get started with the demo app, follow the instructions below:

### 1. Change `applicationId,versionName,versionCode` to your game's `build.gradle`

### 2. Change `Your AppKey` value in `MainActivity.java` file with the AppId

``` java
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
```
### 3. Change Your_AF_DevKey, Your_TD_AppId, and your_GooglePlay_PublishKey in `yodo1_games_config.properties` file

``` properties
# Thinking Data
dmp_key_thinkingdata_appId=Your_TD_AppId
# AppsFlyer
appsflyer_dev_key=Your_AF_DevKey
#=========================== Google Play related KEYs ===========================
# Google Play Licensing, Base64-encoded RSA public key to include in your app binary. Remove any spaces.
google_publish_key=your_GooglePlay_PublishKey
```

### 4. Change `your_google_app_id` in AndroidManifest.xml

``` xml
<!-- GooglePlay Account。Replace your_google_app_id of your app-->
        <meta-data
            android:name="com.google.android.gms.games.APP_ID"
            android:value="your_google_app_id" />
```

### 5. Add your SKU items in `assets/yodo1_payinfo.xml` file

```xml
<?xml version="1.0" encoding="UTF-8"?>
<products>
    <product
        productId="[productId]"
        productName="[productName]"
        productDesc="[productDesc]"
        productPrice="[productPrice(0.01)Dollar]"
        priceDisplay="[priceDisplay$1.99]"
        currency="[currency,eg:CNY]"
        isRepeated="[1cosumeType,2no-consumeType,3subsriptionType]"
        coin="[GameCoin3000]"
        fidGooglePlay="[GooglePlay_ProductId,com.yodo1.stampede.offer1]"/>
<!--add more your SKU items.-->
</products>
```

