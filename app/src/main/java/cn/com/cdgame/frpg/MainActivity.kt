package cn.com.cdgame.frpg

import android.app.Activity
import android.os.Bundle
import cn.com.cdgame.frpg.mode.Result
import cn.com.cdgame.frpg.mode.User
import cn.com.cdgame.frpg.utlis.Utlis
import com.orhanobut.hawk.Hawk
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    val strings: Array<String> = arrayOf("你好冒险者", "欢迎进入LOF", "服务器连接中，请稍等...")
    val DB_U_ID = "DB_USER_ID"
    val DB_U_PW = "DB_USER_PASSWORD"

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
                login()
            }
        })
    }


    /**
     * 登陆
     */
    fun login() {


        Network.api.login(Hawk.get(DB_U_ID, -1), Hawk.get(DB_U_PW, "DEFAULT_PASSWORD"))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(object : Observer<Result<User>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(value: Result<User>) {
                        when (value.code) {
                            0 -> loginSuccess(value.data)
                            -1 -> register()
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
     * 注册
     * 生成一个随机码
     */
    fun register() {
        var password = Hawk.get<String>(DB_U_ID)
        if (password == null) {
            password = Utlis().createRandomCode()
            Hawk.put(DB_U_PW, password)
        }
        with(stx) { post { animateText("随机码：$password") } }
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
    }


}























