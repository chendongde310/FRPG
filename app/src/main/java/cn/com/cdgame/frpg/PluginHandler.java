package cn.com.cdgame.frpg;

import android.os.Environment;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;

import java.io.File;

/**
 * Author：陈东
 * Time：2017/9/15 - 下午3:06
 * Notes:插件管理
 */


public class PluginHandler {
    static PluginHandler pluginHandler;
    final String destFileDir = Environment.getExternalStorageDirectory().getPath() + "/plugin" + "/";

    public PluginHandler() {
        pluginHandler = this;
    }

    /**
     * 异步下载插件
     *
     * @param url
     * @param callbak
     */
    void DownloadPlugin(String url, FileCallback callbak) {
        OkGo.<File>get(url).execute(callbak);
    }

}
