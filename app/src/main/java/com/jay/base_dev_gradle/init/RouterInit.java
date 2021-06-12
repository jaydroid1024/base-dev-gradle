package com.jay.base_dev_gradle.init;

import android.content.res.Configuration;
import android.util.Log;

import com.sankuai.erp.component.appinit.api.SimpleAppInit;
import com.sankuai.erp.component.appinit.common.AppInit;

/**
 * @author jaydroid
 * @version 1.0
 * @date 5/31/21
 */
@AppInit(priority = 40, description = "初始化路由")
public class RouterInit extends SimpleAppInit {
    @Override
    public void onCreate() {
        // SimpleAppInit 中包含了 mApplication 和 mIsDebug 属性，可以直接在子类中使用
//        Router.initialize(mIsDebug);

        Log.d("RouterInit", "" + mApplication.getPackageName());


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("RouterInit", "onConfigurationChanged" + mApplication.getPackageName());

    }
}
