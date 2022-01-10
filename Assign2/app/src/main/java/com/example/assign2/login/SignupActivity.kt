package com.example.assign2.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.assign2.R
import com.example.assign2.RetrofitInterface
import com.example.assign2.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.editTextMakeID
import kotlinx.android.synthetic.main.activity_signup.editTextMakePassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.sign

class SignupActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // start login example@@@@@@@@@@@@@@@@@@@@
        setContentView(R.layout.activity_signup)
        var retrofit1 = Retrofit.Builder()
            .baseUrl("http://192.249.18.108:80/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var loginService = retrofit1.create(RetrofitInterface::class.java)
        // end login example@@@@@@@@@@@@@@@@@@@@@@@


        signUpRequestButton.setOnClickListener {
            var textId = editTextMakeID.text.toString()
            var textPw = editTextMakePassword.text.toString()
            var textPwAg = editTextMakePasswordAgain.text.toString()

            if (textPw.equals(textPwAg)) {
                //여기서 게정 만들어서 User에 추가, userinfo에 생성
                    //implement here...
                //아래는 자동으로 로그인시키
                loginService.requestLogin(textId, textPw).enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        var login = response.body()
                        Log.d("!!!", login.toString())
                        var dialog = AlertDialog.Builder(this@SignupActivity)
                        dialog.setTitle("접속 성공!")
                        dialog.setMessage("" + login?.email + "\n" + login?.private + "\n" + login?.profile_photo + "\n" + login?.is_active)
                        dialog.show()
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        var dialog = AlertDialog.Builder(this@SignupActivity)
                        dialog.setTitle("실패!")
                        dialog.setMessage("통신에 실패했습니다.")
                        dialog.show()
                        Log.d("@@@@@", "onFailure ${t.message}")
                    }

                })
            }
            else {
                var dialog = AlertDialog.Builder(this@SignupActivity)
                dialog.setTitle("회원가입 실패!")
                dialog.setMessage("두 비밀번호가 일치하지 않습니다.")
                dialog.show()
            }
        }
    }
}