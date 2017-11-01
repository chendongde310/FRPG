package cn.com.cdgame.frpg.ui

import android.app.Activity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import cn.com.cdgame.frpg.R
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    val strings: Array<String> = arrayOf("属性", "物品", "商城","副本")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        list_view.adapter = ArrayAdapter<String>(this,R.layout.base_text,strings)
        list_view.onItemClickListener = AdapterView.OnItemClickListener { p0, p1, p2, p3 ->
            when(p2){
                0 -> attribute()
                1 -> item()
                2 -> store()
            }
        }



    }

    private fun store() {

        Logger.d("打开商城栏")
    }

    /**
     * 物品
     */
    private fun item() {
        Logger.d("打开物品栏")

    }


    /**
     * 属性
     */
    private fun attribute() {

        Logger.d("打开属性")
    }


}























