package cn.com.cdgame.frpg

import cn.com.cdgame.frpg.mode.Api
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/10/27 - 下午3:07
 * 注释：
 *
 */

object Network{

    private val HOST: String = "http://120.78.148.66:8080/"

    var retrofit = Retrofit.Builder()
            .baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

     val api: Api = retrofit.create(Api::class.java)


}