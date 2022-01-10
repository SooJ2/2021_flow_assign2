package com.example.assign2.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.assign2.MainActivity
import com.example.assign2.R
import com.example.assign2.RetrofitInterface
import com.example.assign2.User
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApi
import com.kakao.sdk.user.UserApiClient
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
        setContentView(R.layout.activity_login)
        KakaoSdk.init(this, "b4362eb8b242973e09542fb3da4d41d2")
        kakaobutton.setOnClickListener {
            // 로그인 공통 callback 구성
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.d("1!1!1!", "로그인 실패", error)
                }
                else if (token != null) {
                    Log.d("2@2@2@", "로그인 성공 ${token.accessToken}")
                }
            }
            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인


            if (AuthApiClient.instance.hasToken()) {
                UserApiClient.instance.accessTokenInfo { _, error ->
                    if (error != null) {
                        if (error is KakaoSdkError && error.isInvalidTokenError() == true) {
                            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {
                                UserApiClient.instance.loginWithKakaoTalk(this@LoginActivity, callback = callback)
                            } else {
                                UserApiClient.instance.loginWithKakaoAccount(this@LoginActivity, callback = callback)
                            }
                        }
                        else {
                            Log.d("333", "dd")
                        }
                    }
                    else {
                        UserApiClient.instance.me { user, error ->
                            if (error != null) {
                                Log.d("3#3#3#", "사용자 정보 요청 실패", error)
                                val myToast = Toast.makeText(applicationContext, "계정을 불러오는 데 실패했습니다.", Toast.LENGTH_SHORT)
                                myToast.show()
                            }
                            else if (user != null) {
                                Log.d("4$4$4$", "사용자 정보 요청 성공" +
                                        "\n이메일: ${user.kakaoAccount?.email}" )
                            }
                        }
                    }
                }
                UserApiClient.instance.me { user, error ->
                    if (error != null) {

                    }
                    else if (user != null) {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("email", user.kakaoAccount?.email)
                        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)
                        intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                        intent.run {this@LoginActivity.startActivity(this)}
                    }
                }
            }
            else {
                if (UserApiClient.instance.isKakaoTalkLoginAvailable(this@LoginActivity)) {
                    UserApiClient.instance.loginWithKakaoTalk(this@LoginActivity, callback = callback)
                } else {
                    UserApiClient.instance.loginWithKakaoAccount(this@LoginActivity, callback = callback)
                }
                UserApiClient.instance.me { user, error ->
                    if (error != null) {

                    }
                    else if (user != null) {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("email", user.kakaoAccount?.email)
                        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)
                        intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                        intent.run {this@LoginActivity.startActivity(this)}
                    }
                }
            }
        }
    }
}