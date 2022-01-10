package com.example.assign2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.assign2.login.LoginActivity
import com.google.android.material.tabs.TabLayout
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var calendar:Calendar
    lateinit var myFeed:MyFeed
    lateinit var community:Community

    var user:KakaoUser = KakaoUser("null", "1")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val email: String? = intent.getStringExtra("email")
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.249.18.108:80/") //baseUrl 등록, 반드시 /로 마무리 (protocal(https://) + URL)
            .addConverterFactory(GsonConverterFactory.create()) //Gson 변환기 등록 Json을 Class
            .build()
        val service1 = retrofit.create(RetrofitInterface::class.java) // Retrofit instance로 interface 객체 구현

        val loginuser = service1.requestKakaoUser(email)
        loginuser.enqueue(object : Callback<List<KakaoUser>> {
            override fun onResponse(
                call: Call<List<KakaoUser>>,
                response: Response<List<KakaoUser>>
            ) {
                if (response.body()?.size==0) {
                    val call:Call<KakaoUser> = service1.setNewKakaoUser(email, "https://user-images.githubusercontent.com/64190044/148728447-3ab5d279-7bfa-4f03-9418-20d6bb05963c.png")
                    call.enqueue(object : Callback<KakaoUser> {
                        override fun onResponse(call: Call<KakaoUser>, response: Response<KakaoUser>) {
                            val tempuser: KakaoUser = response.body() as KakaoUser
                            user = KakaoUser(tempuser.email, tempuser.profile_photo)
                            println("!!!!!" + tempuser)
                            if(response.isSuccessful.not()) {
                                println("404 에러..?")
                                Log.d("MAINACTIVITY","onResponse: 실패")
                                return
                            }
                            response.body()?.let {
                                val result = response.body()
                                println("성공: ${result.toString()}")
                                Log.d("MAINACTIVITY","onResponse: 성공, 결과\n${result.toString()}")
                            }
                        }
                        override fun onFailure(call: Call<KakaoUser>, t: Throwable) {
                            println("인터넷 연결 실패  ")
                            Log.d("MAINACTIVITY","onFailure ${t.message}") // 인터넷 연결 실
                            Toast.makeText(this@MainActivity,"연결이 실패했습니다. 다시 로그인해주세요.", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@MainActivity, LoginActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            intent.run {this@MainActivity.startActivity(this)}
                        }
                    })
                }
                else {
                    val temp = response.body()!![0]
                    user = KakaoUser(temp.email, temp.profile_photo)
                    println("${response.body()!![0]} !!!!!")
                    println("$user @@@@@")
                }

            }
            override fun onFailure(call: Call<List<KakaoUser>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"연결이 실패했습니다. 다시 로그인해주세요.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.run {this@MainActivity.startActivity(this)}
            }

        })

//        println("$user #####")
//        if (user.email.equals("null")) {
//            Toast.makeText(this,"연결이 실패했습니다. 다시 로그인해주세요.", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, LoginActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            intent.run {this@MainActivity.startActivity(this)}
//        }



        calendar = Calendar()
        myFeed = MyFeed()
        community = Community()

        supportFragmentManager.beginTransaction().add(R.id.frameLayout,calendar).commit()


        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> {
                        replaceView(calendar)
                    }
                    1 -> {
                        replaceView(myFeed)
                    }
                    2 -> {
                        replaceView(community)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

//        // retrofit start
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://192.249.18.108:80/") //baseUrl 등록, 반드시 /로 마무리 (protocal(https://) + URL)
//            .addConverterFactory(GsonConverterFactory.create()) //Gson 변환기 등록 Json을 Class
//            .build()
//        val service1 = retrofit.create(RetrofitInterface::class.java) // Retrofit instance로 interface 객체 구현

        // retrofit end


    }

    private fun replaceView(tab: Fragment) {
        var selectedFragment: Fragment? = null
        selectedFragment = tab
        selectedFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, it).commit()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        println("********************************************")

        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if(result != null) {
            if (result.contents == null) {
                println("********************************************cancel")
                Toast.makeText(this,"QR코드 인증이 취소되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                println("********************************************sucess")
                Toast.makeText(this,"INFO: ${result.contents}",Toast.LENGTH_LONG).show()
                val frg = (supportFragmentManager.findFragmentById(R.id.frameLayout)) as FeedAdd
                val barcode:String  = result.contents
                frg.barcodes = barcode

                }
        }else{
            println("********************************************in else stmt")
            super.onActivityResult(requestCode,resultCode,data)
        }
    }
}
