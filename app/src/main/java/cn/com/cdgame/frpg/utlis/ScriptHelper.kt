package cn.com.cdgame.frpg.utlis

import android.os.Handler
import com.hanks.htextview.scale.ScaleTextView

/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/10/25 - 下午1:10
 * 注释：剧本管理器
 *
 */

object ScriptHelper {

    var index: Int = 0

    fun script(strings: Array<String>,stx:ScaleTextView) {
        stx.animateText(strings[index])
        Handler().postDelayed(Runnable {
            if (index < strings.size) {
                script(strings,stx)
            } else {
                index = 0
            }
        }, getTime(strings[index].length).toLong())
        index++
    }

    fun getTime(length: Int): Int {
        var time: Int = length * 250
        if (time < 1800) {
            time = 1800
        } else if (time > 10000) {
            time = 10000
        }
        return time
    }


}