package cn.com.cdgame.frpg.mode_kt

import com.qihoo360.replugin.model.PluginInfo


/**
 * 插件模型
 */
class PluginMode(val info: PluginInfo) {
    var url: String = "" //下载地址
    var name: String = info.name  // 插件名：和  meta-data对应
    var ver: Int = info.version  //版本：和  meta-data对应


}