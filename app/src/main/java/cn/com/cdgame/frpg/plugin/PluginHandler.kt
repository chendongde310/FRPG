package cn.com.cdgame.frpg.plugin

import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.FileCallback
import com.lzy.okgo.model.Response
import com.qihoo360.replugin.RePlugin
import com.qihoo360.replugin.model.PluginInfo
import java.io.File

/**
 * Author：陈东
 * Time：2017/9/15 - 下午3:06
 * Notes:插件管理
 */


class PluginHandler {
    //    internal val destFileDir = Environment.getExternalStorageDirectory().path + "/plugin" + "/"
    val plugins: HashMap<String, PluginInfo> = HashMap()

    /**
     * 异步下载插件
     *
     * @param mode 插件mode
     */
    fun install(url: String, callback: PluginDownloadCallbak) = OkGo.get<File>(url).execute(object : FileCallback() {
        override fun onSuccess(response: Response<File>) {
            val info: PluginInfo = RePlugin.install(response.body().path)
            plugins.put(info.name, info)
            callback.onSuccess(info)
        }

    })


    object declaration {
        val pluginHandler = PluginHandler()
    }

    companion object {
        fun get(): PluginHandler = declaration.pluginHandler
    }


    interface PluginDownloadCallbak {
        fun onSuccess(info: PluginInfo)
    }


}
