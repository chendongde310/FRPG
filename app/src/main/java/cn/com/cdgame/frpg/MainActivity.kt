package cn.com.cdgame.frpg

import android.app.Activity
import android.os.Bundle
import cn.com.cdgame.frpg.mode.PluginMode
import cn.com.cdgame.frpg.utlis.PluginUtils
import com.qihoo360.replugin.model.PluginInfo


class MainActivity : Activity() {

    val URL = "http://ac-ix5sq7e3.clouddn.com/629f5a60ac857049a301.apk"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val name = "plugin1"
//        PluginUtils.get().StartActivity(this@MainActivity, name, PluginMode.MainActivity)


        PluginHandler.get().install(URL, object : PluginHandler.PluginDownloadCallbak {
            override fun onSuccess(info: PluginInfo) {
                PluginUtils.get().StartActivity(this@MainActivity, info, PluginMode.MainActivity)
            }
        })
    }


}
