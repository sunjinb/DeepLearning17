package org.techtown.myapplication;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        KakaoSdk.init(this,"b2476841b6d720daedee5ec92a9f201c");
    }
}
