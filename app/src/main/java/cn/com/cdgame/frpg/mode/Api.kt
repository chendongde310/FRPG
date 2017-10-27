package cn.com.cdgame.frpg.mode

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * 作者：陈东  —  www.renwey.com
 * 日期：2017/10/26 - 下午4:11
 * 注释：
 *
 */
interface Api {

    /**
     * 登陆
     */
    @POST("/user/login")
    @FormUrlEncoded
    fun login(@Field("id") id: Long, @Field("password") password: String): Observable<Result<User>>


}



