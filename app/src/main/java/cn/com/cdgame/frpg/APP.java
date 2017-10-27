package cn.com.cdgame.frpg;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.RePluginApplication;
import com.qihoo360.replugin.RePluginConfig;

/**
 * @Author：陈东
 * Time：2017/9/15 - 下午3:03
 * Notes:
 */

public class APP extends RePluginApplication {


    private static String SIGN = "0BA33A77B19E3EFDA51F548D5467E9384AC40252";

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        Hawk.init(this).build();
    }


    /**
     * debug模式设置
     *
     */
    @Override
    protected RePluginConfig createConfig() {
        RePluginConfig c = new RePluginConfig();
        c.setVerifySign(!BuildConfig.DEBUG);
        if(!BuildConfig.DEBUG){
            RePlugin.addCertSignature(SIGN);
        }
        return c;
    }
}
