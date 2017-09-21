package cn.com.cdgame.frpg.mode

import com.qihoo360.replugin.model.PluginInfo

/**
 * Created by chen
 * On 2017/9/19
 * 插件mode
 */
class PluginMode(private val pluginUrl: String) {
    companion object baseClassName {
        //基本的类名
        val MainActivity = "MainActivity"

    }

    val url: String = pluginUrl
    var info: PluginInfo? = null

}
