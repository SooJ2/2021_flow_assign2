package com.example.assign2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
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

    var retrofitaa = RetrofitInterface.getInstance()
    var user:KakaoUser = KakaoUser(-1,"null", "1")
    var n=0
    var calories: ArrayList<String> = ArrayList<String>()
    var retrofitInterface = RetrofitInterface.getInstance()

    var feeds : ArrayList<ArrayList<Feed>> = ArrayList<ArrayList<Feed>>()
    var eatenFoodList = MutableLiveData<List<EatenFood>>()
    var calorieList = ArrayList<Int>()
    var foodList = ArrayList<Food>()


    fun getCaloriesFromFeed(feed: Feed)  {
        val eatuser = retrofitInterface.requestEatsByFeedId(1)
        eatuser.enqueue(object : Callback<List<EatenFood>> {
            override fun onResponse(
                call: Call<List<EatenFood>>,
                response: Response<List<EatenFood>>
            ) {
                eatenFoodList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<EatenFood>>, t: Throwable) {
                Log.e("skdh", t.toString())
            }
        })
        eatenFoodList.observe((this), androidx.lifecycle.Observer { myEatenFoodList->
            for (food in myEatenFoodList) {
                retrofitInterface.getFood(food.eaten_food).enqueue(object: Callback<Food> {
                    override fun onResponse(call: Call<Food>, response: Response<Food>) {
//                        Log.d("FOOD", response.code().toString())
                        val tempFood = Food(response.body()!!.id, response.body()!!.name, response.body()!!.calorie)
//                        Log.d("tempFood!!", tempFood.calorie.toString())
                        foodList.add(tempFood)
                        calorieList.add(tempFood.calorie.toInt())
                        n += tempFood.calorie.toInt()
//                        Log.d("n1!!", n.toString())
                    }
                    override fun onFailure(call: Call<Food>, t: Throwable) {
                        Log.e("ERROR", t.message.toString())
                    }

                })
            }
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val email = intent.getStringExtra("email")

        setContentView(R.layout.activity_main)

        val loginuser = retrofitaa.requestKakaoUser(email)
        loginuser.enqueue(object : Callback<List<KakaoUser>> {
            override fun onResponse(
                call: Call<List<KakaoUser>>,
                response: Response<List<KakaoUser>>
            ) {
                if (response.body()?.size==0) {
                    val call:Call<KakaoUser> = retrofitaa.setNewKakaoUser(email, "https://user-images.githubusercontent.com/64190044/148728447-3ab5d279-7bfa-4f03-9418-20d6bb05963c.png")
                    call.enqueue(object : Callback<KakaoUser> {
                        override fun onResponse(call: Call<KakaoUser>, response: Response<KakaoUser>) {
                            val tempuser: KakaoUser = response.body() as KakaoUser
                            user = KakaoUser(tempuser.id, tempuser.email, tempuser.profile_photo)
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
                    user = KakaoUser(temp.id, temp.email, temp.profile_photo)
                    println("${response.body()!![0]} !!!!!")
                    println("$user @@@@@")
                }

            }
            override fun onFailure(call: Call<List<KakaoUser>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"연결이 실패했습니다. 다시 로그인해주세요.", Toast.LENGTH_SHORT).show()
                Log.e("ERROR", t.message.toString())
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.run {this@MainActivity.startActivity(this)}
            }

        })

//        val callFeeds = retrofitInterface.requestFeedsByUserId(1) //user.id로 바꿔야함!!!!
//        callFeeds.enqueue(object : Callback<List<Feed>> {
//            override fun onResponse(call: Call<List<Feed>>, response: Response<List<Feed>>) {
////                if (response.isSuccessful.not()){
////                    Log.e("S", "NOT SUCCESS")
////                    return
////                }
////                response.body()?.let{
////                    if(it.count()!=0){
////                        for(i in 0 until it.count()){
////                            feeds.add(it[i])
////                        }
////                    }
////                    else {
////                        Log.e("D", "DO NOT ACCESS")
////                    }
////                }
//                var m=0
//                if (response.body() != null) {
//                    for(i in 0 until (response.body()!!.size)) {
//                        if (i==0) {
//                            feeds.add(arrayListOf(Feed(response.body()!![i].id, response.body()!![i].uploader, response.body()!![i].feed_photo, response.body()!![i].likes, response.body()!![i].upload_time, response.body()!![i].update_time, response.body()!![i].eat_date, response.body()!![i].diet_explain)))
//                        }
//                        else {
//                            if (response.body()!![i-1].eat_date.equals(response.body()!![i].eat_date)) {
//                                feeds[m].add(Feed(response.body()!![i].id, response.body()!![i].uploader, response.body()!![i].feed_photo, response.body()!![i].likes, response.body()!![i].upload_time, response.body()!![i].update_time, response.body()!![i].eat_date, response.body()!![i].diet_explain))
//                            }
//                            else {
//                                m++
//                                feeds.add(arrayListOf(Feed(response.body()!![i].id, response.body()!![i].uploader, response.body()!![i].feed_photo, response.body()!![i].likes, response.body()!![i].upload_time, response.body()!![i].update_time, response.body()!![i].eat_date, response.body()!![i].diet_explain)))
//                            }
//                        }
//                    }
//                }
//                Log.d("feedsin##", feeds.toString())
//                for (i in 0 until feeds.size) {
//                    var n=0
//                    for (j in 0 until feeds[i].size) {
//                        val eatenfoodcall = retrofitInterface.requestEatsByFeedId(feeds[i][j].id)
//                        eatenfoodcall.enqueue(object : Callback<List<EatenFood>> {
//                            override fun onResponse(
//                                call: Call<List<EatenFood>>,
//                                response: Response<List<EatenFood>>
//                            ) {
//                                var ns: ArrayList<Int> = ArrayList<Int>()
//                                var date: String = ""
//                                for (k in 0 until response.body()!!.size) {
//                                    n += response.body()!![k].eaten_calorie.toInt()
//                                }
//                                //여기서 지속적으로 ns 이용해야
//                                ns.add(n)
//                                Log.d("ns#??", ns.toString() + feeds[i][j].eat_date)
//                            }
//                            override fun onFailure(call: Call<List<EatenFood>>, t: Throwable) {
//                                Log.e("a", "b")
//                            }
//                        })
//                    }
//                }
//            }
//            override fun onFailure(call: Call<List<Feed>>, t: Throwable) {
//                Log.e("error1111", "error")
//            }
//        })
        Log.d("feeds##", feeds.toString())
//        for (i in 0 until (feeds.size)) {
//            for (j in 0 until (feeds[i].size)) {
//                getCaloriesFromFeed(feeds[i][j])
//                Log.d("dddkk", calorieList.toString())
//            }
//        }

//------------------------------------------달력 생성-------------------------------------------------


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
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }


    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        println("+++++ $user")
        return super.onCreateView(name, context, attrs)
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
//                Toast.makeText(this,"INFO: ${result.contents}",Toast.LENGTH_LONG).show()
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
