package cn.com.cdgame.frpg.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import cn.com.cdgame.frpg.Network
import cn.com.cdgame.frpg.R
import cn.com.cdgame.frpg.mod.ScriptMod
import cn.com.cdgame.frpg.model.Result
import cn.com.cdgame.frpg.model.User
import cn.com.cdgame.frpg.utlis.Utlis
import com.orhanobut.hawk.Hawk
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : Activity() {
    val strings: Array<String> = arrayOf("你好冒险者", "欢迎进入LOF", "服务器连接中，请稍等...")
    val DB_U_ID = "DB_USER_ID"
    val DB_U_PW = "DB_USER_PASSWORD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //播放剧本
        ScriptMod().apply {
            contents = strings
        }.play(object : ScriptMod.Callback {
            override fun onNext(text: String, index: Int) {
                with(stx) { post { animateText(text) } }
            }

            override fun onFinish() {
                login()
            }
        })
    }


    /**
     * 登陆
     *
     */
    fun login() {


        Network.api.login(Hawk.get(DB_U_ID, -1), Hawk.get(DB_U_PW, "DEFAULT_PASSWORD"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Result<User>> {
                    override fun onSubscribe(d: Disposable) {

                    }


                    override fun onNext(value: Result<User>) {
                        when (value.code) {
                            0 -> loginSuccess(value.data)//登陆成功
                            -1 -> randomCode()//未注册  -> 就生成随机码
                            else -> loginError(value.msg, value.code)
                        }
                    }

                    override fun onError(e: Throwable) {
                        loginError(e.message, e.hashCode())
                    }

                    override fun onComplete() {

                    }
                })


    }


    /**
     *
     * 生成一个随机码
     */
    fun randomCode() {

        var password = Hawk.get<String>(DB_U_PW)
        if (password == null) {
            password = Utlis().createRandomCode()
            Hawk.put(DB_U_PW, password)
        }


        with(stx) { post { animateText("随机码：" + Integer.toHexString(password.toInt())) } }

        //TODO--注册流程改动
        stx.setOnClickListener {
            with(stx) { post { animateText("注册中，请等待") } }
            register("二狗君")
        }

    }


    /**
     * 注册
     * (测试方法)
     */
    private fun register(name :String) {
        Network.api.register(name, Hawk.get<String>(DB_U_PW))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Observer<Result<User>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(value: Result<User>) {
                        val user: User? = value.data
                        if (user == null) {

                        }


                        when (value.code) {
                            0 -> {
                                Hawk.put(DB_U_ID, value.data?.id)
                                Hawk.put(DB_U_PW, value.data?.userPassword)
                                login()
                            }

                            else -> loginError(value.msg, value.code)
                        }
                    }

                    override fun onError(e: Throwable) {
                        loginError(e.message, e.hashCode())
                    }

                    override fun onComplete() {

                    }
                })

    }


    /**
     * 登陆失败
     */
    private fun loginError(msg: String?, code: Int) {
        var resultMsg: String = msg + "--错误代码:" + code.toShort()
        Logger.d(resultMsg)
        //Toast.makeText(this, resultMsg, Toast.LENGTH_SHORT).show()
        with(stx) { post { animateText(resultMsg) } }
    }

    /**
     * 登陆成功
     */
    private fun loginSuccess(user: User?) {


        with(stx) { post { animateText("欢迎回来${user?.userName}") } }

        Handler().postDelayed({
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            this@LoginActivity.finish()
        }, 1800)
    }
}
