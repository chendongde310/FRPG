package cn.com.cdgame.frpg.mode

import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.FileCallback

/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/10/26 - 下午4:11
 * 注释：
 *
 */
class Api {

    private val HOST: String = "http://120.78.148.66:8080"

    /**
     * 登陆
     */
    fun login(id: Long, password: String, callback: FileCallback) {
        OkGo.post<Result<User>>("/user/login")
                .params("id", id)
                .params("password", password)
                .execute(callback)


    }


}