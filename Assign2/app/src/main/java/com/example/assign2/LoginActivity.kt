package com.example.assign2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // start login example@@@@@@@@@@@@@@@@@@@@
        setContentView(R.layout.activity_login)

        var retrofit1 = Retrofit.Builder()
            .baseUrl("http://192.249.18.108:80/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var loginService = retrofit1.create(RetrofitInterface::class.java)

        loginbutton.setOnClickListener {
            var textId = editTextTextID.text.toString()
            var textPw = editTextTextPassword.text.toString()

            loginService.requestLogin(textId, textPw).enqueue(object : Callback<Userinfo> {
                override fun onResponse(call: Call<Userinfo>, response: Response<Userinfo>) {
                    var login = response.body()
                    var dialog = AlertDialog.Builder(this@LoginActivity)
                    dialog.setTitle("접속 성공!")
                    dialog.setMessage("" + login?.user_id + "\n" + login?.private + "\n" + login?.profile_photo + "\n" + login?.password)
                    dialog.show()
                }

                override fun onFailure(call: Call<Userinfo>, t: Throwable) {
                    var dialog = AlertDialog.Builder(this@LoginActivity)
                    dialog.setTitle("실패!")
                    dialog.setMessage("통신에 실패했습니다.")
                    dialog.show()
                }

            })
        }
        // end login example@@@@@@@@@@@@@@@@@@@@@@@
    }
}