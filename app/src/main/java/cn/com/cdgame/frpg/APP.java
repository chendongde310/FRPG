package cn.com.cdgame.frpg;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.RePluginConfig;

/**
 *
 * Author：陈东
 * Time：2017/9/15 - 下午3:03
 * Notes:
 */

public class APP extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
        isDebug(true);
    }


    /**
     * debug模式设置
     * @param b
     */
    private void isDebug(boolean b) {
        if (b) {
            RePluginConfig config = new RePluginConfig();
            config.setVerifySign(false);
            RePlugin.App.attachBaseContext(this, config);
        }
    }
}
