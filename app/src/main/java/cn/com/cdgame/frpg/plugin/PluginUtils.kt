package cn.com.cdgame.frpg.plugin

import android.content.Context
import com.qihoo360.replugin.RePlugin
import com.qihoo360.replugin.model.PluginInfo

/**
 * Created by chen
 * On 2017/9/19
 * 插件化工具集
 */
class PluginUtils private constructor() {




    fun StartActivity(context: Context, info: PluginInfo, classname: String) {
        RePlugin.startActivity(context, RePlugin.createIntent(info.name,
                info.packageName + "." + classname))
    }


    fun StartActivity(context: Context, name: String, classname: String) {
        StartActivity(context, RePlugin.getPluginInfo(name), classname)
    }

    companion object {
        fun get(): PluginUtils = Inner.utils
    }

    object Inner {
        val utils = PluginUtils()
    }


}

