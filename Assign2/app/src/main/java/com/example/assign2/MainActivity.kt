package com.example.assign2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

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

        // retrofit start
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.249.18.108:80/") //baseUrl 등록, 반드시 /로 마무리 (protocal(https://) + URL)
            .addConverterFactory(GsonConverterFactory.create()) //Gson 변환기 등록 Json을 Class
            .build()
        val service1 = retrofit.create(RetrofitInterface::class.java) // Retrofit instance로 interface 객체 구현

        service1.getUser("1").enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                println("인터넷 연결 실패  ")
                Log.d("MAINACTIVITY","onFailure ${t.message}") // 인터넷 연결 실
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
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
        })
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
