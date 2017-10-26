package cn.com.cdgame.frpg

import android.app.Activity
import android.os.Bundle
import cn.com.cdgame.frpg.mode.Api
import com.lzy.okgo.OkGo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    val strings: Array<String> = arrayOf("你好冒险者",  "欢迎进入LOF" ,"服务器连接中，请稍等...")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ScriptMod().apply {
            isLoop = false
            contents = strings
        }.play(object : ScriptMod.Callback {
            override fun onNext(text: String, index: Int) {
                with(stx) { post { animateText(text) } }
            }

            override fun onFinish() {
                Api().login()
            }
        })


    }






}























