package cn.com.cdgame.frpg

import android.app.Activity
import android.os.Bundle
import cn.com.cdgame.frpg.utlis.ScriptHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    var strings: Array<String> = arrayOf("欢迎回来冒险者", "我们为你准备了一些新东西","比如新的武器和盔甲","还有你最喜欢的炎陵", "一起来看看吧", "你好", "啪", "end")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stx.setOnClickListener {
           ScriptHelper.script(strings,stx)
        }
        stx.length()
    }




}
