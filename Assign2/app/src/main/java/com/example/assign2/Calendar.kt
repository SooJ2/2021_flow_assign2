package com.example.assign2


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.example.assign2.databinding.FragmentCalendarBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class Calendar : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    var mCalendar = ArrayList<String>()
    var calYear = 0
    var calMonth = 0
    var i = 0
    lateinit var calAdapter: CalendarGridViewAdapter

    var feeds : ArrayList<ArrayList<Feed>> = ArrayList<ArrayList<Feed>>()
    var retrofitInterface = RetrofitInterface.getInstance()

//    var n=0
//    var calories: ArrayList<String> = ArrayList<String>()
//    var retrofitInterface = RetrofitInterface.getInstance()
//
//    var eatenFoodList = MutableLiveData<List<EatenFood>>()
//    var calorieList = mutableListOf<Int>()
//    var foodList = mutableListOf<Food>()


//    fun getCaloriesFromFeed(feed: Feed)  {
////        val retrofit = Retrofit.Builder()
////            .baseUrl("http://192.249.18.108:80/") //baseUrl 등록, 반드시 /로 마무리 (protocal(https://) + URL)
////            .addConverterFactory(GsonConverterFactory.create()) //Gson 변환기 등록 Json을 Class
////            .build()
////        val service1 = retrofit.create(RetrofitInterface::class.java) // Retrofit instance로 interface 객체 구현
//        val eatuser = retrofitInterface.requestEatsByFeedId(feed.id)
////        val eatuser = service1.requestEatsByFeedId(feed.id)
//        eatuser.enqueue(object : Callback<List<EatenFood>> {
//            override fun onResponse(
//                call: Call<List<EatenFood>>,
//                response: Response<List<EatenFood>>
//            ) {
//
//                eatenFoodList.postValue(response.body())
////                for (i in 0 until response.body()!!.size) {
////
////                    val getfood = retrofitInterface.getFood(response.body()!![i].eaten_food)
////                    println("getfood의 파라미터는 ${response.body()!![i].eaten_food}")
////                    getfood.enqueue(object : Callback<Food> {
////                        override fun onResponse(call: Call<Food>, response: Response<Food>) {
////                            val tempFood = Food(response.body()!!.id, response.body()!!.name, response.body()!!.calorie)
////                            Log.d("tempFood!!", tempFood.calorie.toString())
////                            foodList.add(tempFood)
////                            calorieList.add(tempFood.calorie.toInt())
////                            n += tempFood.calorie.toInt()
////                            Log.d("n1!!", n.toString())
////                        }
////
////                        override fun onFailure(call: Call<Food>, t: Throwable) {
////                            Log.d("sadfasdf", t.toString())
////                        }
////
////                    })
////                    Log.d("!!!!!?", calorieList.count().toString())
////                }
////                calories.add(n.toString())
////                n=0
//            }
//            override fun onFailure(call: Call<List<EatenFood>>, t: Throwable) {
//                Log.d("skdh", t.toString())
//            }
//        })
//        eatenFoodList.observe((context as MainActivity), androidx.lifecycle.Observer { myEatenFoodList->
//            for (food in myEatenFoodList) {
//                retrofitInterface.getFood(food.eaten_food).enqueue(object: Callback<Food> {
//                    override fun onResponse(call: Call<Food>, response: Response<Food>) {
////                        Log.d("FOOD", response.code().toString())
//                        val tempFood = Food(response.body()!!.id, response.body()!!.name, response.body()!!.calorie)
////                        Log.d("tempFood!!", tempFood.calorie.toString())
//                        foodList.add(tempFood)
//                        calorieList.add(tempFood.calorie.toInt())
//                        n += tempFood.calorie.toInt()
////                        Log.d("n1!!", n.toString())
//                    }
//
//                    override fun onFailure(call: Call<Food>, t: Throwable) {
////                        Log.e("ERROR", t.message.toString())
//                    }
//
//                })
//            }
//        })
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//////////////////////////////////
        binding = FragmentCalendarBinding.inflate(layoutInflater)
        val today = GregorianCalendar()
        calYear = today.get(1)
        calMonth = today.get(2)
        setCalendarList(calYear,calMonth)
        calAdapter = CalendarGridViewAdapter(requireContext(),mCalendar)
        binding.calendarMonth.text = (calMonth+1).toString()
        binding.calendarYear.text = calYear.toString()
        val callFeeds = retrofitInterface.requestFeedsByUserId(1) //user.id로 바꿔야함!!!!
        callFeeds.enqueue(object : Callback<List<Feed>> {
            override fun onResponse(call: Call<List<Feed>>, response: Response<List<Feed>>) {
//                if (response.isSuccessful.not()){
//                    Log.e("S", "NOT SUCCESS")
//                    return
//                }
//                response.body()?.let{
//                    if(it.count()!=0){
//                        for(i in 0 until it.count()){
//                            feeds.add(it[i])
//                        }
//                    }
//                    else {
//                        Log.e("D", "DO NOT ACCESS")
//                    }
//                }
                var m=0
                if (response.body() != null) {
                    for(i in 0 until (response.body()!!.size)) {
                        if (i==0) {
                            feeds.add(arrayListOf(Feed(response.body()!![i].id, response.body()!![i].uploader, response.body()!![i].feed_photo, response.body()!![i].likes, response.body()!![i].upload_time, response.body()!![i].update_time, response.body()!![i].eat_date, response.body()!![i].diet_explain)))
                        }
                        else {
                            if (response.body()!![i-1].eat_date.equals(response.body()!![i].eat_date)) {
                                feeds[m].add(Feed(response.body()!![i].id, response.body()!![i].uploader, response.body()!![i].feed_photo, response.body()!![i].likes, response.body()!![i].upload_time, response.body()!![i].update_time, response.body()!![i].eat_date, response.body()!![i].diet_explain))
                            }
                            else {
                                m++
                                feeds.add(arrayListOf(Feed(response.body()!![i].id, response.body()!![i].uploader, response.body()!![i].feed_photo, response.body()!![i].likes, response.body()!![i].upload_time, response.body()!![i].update_time, response.body()!![i].eat_date, response.body()!![i].diet_explain)))
                            }
                        }
                    }
                }
                Log.d("feedsin##", feeds.toString())
                for (i in 0 until feeds.size) {
                    var n=0
                    for (j in 0 until feeds[i].size) {
                        val eatenfoodcall = retrofitInterface.requestEatsByFeedId(feeds[i][j].id)
                        eatenfoodcall.enqueue(object : Callback<List<EatenFood>> {
                            override fun onResponse(
                                call: Call<List<EatenFood>>,
                                response: Response<List<EatenFood>>
                            ) {
                                var ns: ArrayList<Int> = ArrayList<Int>()
                                var date: String = ""
                                for (k in 0 until response.body()!!.size) {
                                    n += response.body()!![k].eaten_calorie.toInt()
                                }
                                //여기서 지속적으로 ns 이용해야
                                ns.add(n)
                                Log.d("ns#??", ns.toString() + feeds[i][j].eat_date)
                                calAdapter.getInfo(feeds[i][j].eat_date, ns[0].toString())
                                calAdapter.notifyDataSetChanged()
                            }
                            override fun onFailure(call: Call<List<EatenFood>>, t: Throwable) {
                                Log.e("a", "b")
                            }
                        })
                    }
                }
            }
            override fun onFailure(call: Call<List<Feed>>, t: Throwable) {
                Log.e("error1111", "error")
            }
        })
        /////////////////////////////////////
//        val retrofitforFeed = Retrofit.Builder()
//            .baseUrl("http://192.249.18.108:80/") //baseUrl 등록, 반드시 /로 마무리 (protocal(https://) + URL)
//            .addConverterFactory(GsonConverterFactory.create()) //Gson 변환기 등록 Json을 Class
//            .build()
//        val serviceforFeed = retrofitforFeed.create(RetrofitInterface::class.java) // Retrofit instance로 interface 객체 구현
//        val callFeeds = retrofitInterface.requestFeedsByUserId(1) //user.id로 바꿔야함!!!!
//        callFeeds.enqueue(object : Callback<List<Feed>> {
//            override fun onResponse(call: Call<List<Feed>>, response: Response<List<Feed>>) {
//                //response.body(): (List<Feed>)
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
//            }
//
//            override fun onFailure(call: Call<List<Feed>>, t: Throwable) {
////                Log.d("error1111", "error")
//            }
//        })
//        Log.d("feeds!", feeds.toString())
//        for (i in 0 until (feeds.size)) {
//            for (j in 0 until (feeds[i].size)) {
//                getCaloriesFromFeed(feeds[i][j])
//                Log.d("dddkk", calorieList.toString())
//            }
//        }

//        if(eatenFoodList.count() == 0) {
//            Log.d("!!!!!?", "COUNT = 0")
//        }
//
//        if (eatenFoodList.count() != 0) {
//            Log.d("!!!!!?", n.toString())
//        }


        calAdapter.notifyDataSetChanged()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val gridView = binding.calendar
        gridView.adapter = calAdapter


        binding.calendarPrev.setOnClickListener {
            i -= 1
            val curCalendar = GregorianCalendar(calYear,calMonth+i,1,0,0,0)
            val tmpCalYear = curCalendar.get(1)
            val tmpCalMonth = curCalendar.get(2)
            setCalendarList(tmpCalYear,tmpCalMonth)
            calAdapter.update(mCalendar)
            binding.calendarMonth.text = (tmpCalMonth+1).toString()
            binding.calendarYear.text = tmpCalYear.toString()
        }

        binding.calendarNext.setOnClickListener {
            i += 1
            val curCalendar = GregorianCalendar(calYear,calMonth+i,1,0,0,0)
            val tmpCalYear = curCalendar.get(1)
            val tmpCalMonth =curCalendar.get(2)
            setCalendarList(tmpCalYear,tmpCalMonth)
            calAdapter.update(mCalendar)
            binding.calendarMonth.text = (tmpCalMonth+1).toString()
            binding.calendarYear.text = tmpCalYear.toString()
        }

        return binding.root
    }

    override fun onDestroyView() {
        binding = FragmentCalendarBinding.inflate(layoutInflater)
        super.onDestroyView()
    }

    fun setCalendarList(year:Int, month: Int){
        mCalendar = ArrayList<String>()
        val cal = GregorianCalendar(year,month,1)
        try{
            val dayOfWeek = cal.get(GregorianCalendar.DAY_OF_WEEK) - 1; //해당 월에 시작하는 요일 -1 을 하면 빈칸을 구할 수 있겠죠 ?
            val max = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); // 해당 월에 마지막 요일

            for (j in 0 until dayOfWeek) {
                mCalendar.add("");  //비어있는 일자 타입
            }
            for (j in 1..max) {
                mCalendar.add(j.toString()); //일자 타입
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



}