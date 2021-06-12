package com.jay.base_dev_gradle;

import android.app.Application;
import android.util.Log;

import com.sankuai.erp.component.appinit.api.AppInitApiUtils;
import com.sankuai.erp.component.appinit.api.AppInitManager;
import com.sankuai.erp.component.appinit.api.SimpleAppInitCallback;
import com.sankuai.erp.component.appinit.common.AppInitItem;
import com.sankuai.erp.component.appinit.common.ChildInitTable;

import java.util.List;
import java.util.Map;

/**
 * @author jaydroid
 * @version 1.0
 * @date 5/31/21
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        appInit();
    }


    private void appInit() {
        AppInitManager.get().init(this, new SimpleAppInitCallback() {
            /**
             * 开始初始化
             *
             * @param isMainProcess 是否为主进程
             * @param processName   进程名称
             */
            @Override
            public void onInitStart(boolean isMainProcess, String processName) {
                // TODO 在所有的初始化类之前初始化
            }

            /*
             * 是否为 debug 模式
             */
            @Override
            public boolean isDebug() {
                return BuildConfig.DEBUG;
            }

            /**
             * 通过 coordinate 自定义依赖关系映射，键值都是 coordinate。「仅在需要发热补的情况下才自定义，否则返回 null」
             *
             * @return 如果返回的 map 不为空，则会在启动是检测依赖并重新排序
             */
            @Override
            public Map<String, String> getCoordinateAheadOfMap() {
                return null;
            }

            /**
             * 同步初始化完成
             *
             * @param isMainProcess      是否为主进程
             * @param processName        进程名称
             * @param childInitTableList 初始化模块列表
             * @param appInitItemList    初始化列表
             */
            @Override
            public void onInitFinished(boolean isMainProcess, String processName, List<ChildInitTable> childInitTableList, List<AppInitItem> appInitItemList) {
                // 获取运行期初始化日志信息
                String initLogInfo = AppInitApiUtils.getInitOrderAndTimeLog(childInitTableList, appInitItemList);
                Log.d("statisticInitInfo", initLogInfo);
            }
        });
    }
}
