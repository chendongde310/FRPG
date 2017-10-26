package cn.com.cdgame.frpg

import android.os.Handler

/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/10/26 - 上午11:58
 * 注释：剧本模组
 *
 */
class ScriptMod {
    private var index: Int = 0
    var contents: Array<String> = arrayOf()
    var isLoop: Boolean = false




    /**
     * 开始播放剧本
     */
    fun play(callback: Callback) {
        callback.onNext(contents[index],index)
        Handler().postDelayed(Runnable {
            if (index < contents.size) {
                play(callback)
            } else {
                index = 0
                if (isLoop) {
                    play(callback)
                }else{
                    callback.onFinish()
                }
            }
        }, getTime(contents[index].length).toLong())
        index++
    }


    /**
     * 显示时间，由长度自行决定，最小1.8秒，最长10秒
     */
    private fun getTime(length: Int): Int {
        var time: Int = length * 250
        if (time < 1800) {
            time = 1800
        } else if (time > 10000) {
            time = 10000
        }
        return time
    }

    /**
     * 回调
     */
    interface Callback {
        fun onNext(text: String, index: Int)
        fun onFinish()
    }

}