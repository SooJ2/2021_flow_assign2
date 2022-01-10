package com.example.assign2


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    var feeds : ArrayList<ArrayList<Feed>> = ArrayList<ArrayList<Feed>>()
    var mCalendar = ArrayList<String>()
    var calYear = 0
    var calMonth = 0
    var i = 0
    lateinit var calAdapter: CalendarGridViewAdapter
    var n=0
    var calories: ArrayList<ArrayList<String>> = ArrayList<ArrayList<String>>()

    fun getCaloriesFromFeed(feed: Feed)  {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.249.18.108:80/") //baseUrl 등록, 반드시 /로 마무리 (protocal(https://) + URL)
            .addConverterFactory(GsonConverterFactory.create()) //Gson 변환기 등록 Json을 Class
            .build()
        val service1 = retrofit.create(RetrofitInterface::class.java) // Retrofit instance로 interface 객체 구현
        val eatuser = service1.requestEatsByFeedId(feed.id)
        eatuser.enqueue(object : Callback<List<EatenFood>> {
            override fun onResponse(
                call: Call<List<EatenFood>>,
                response: Response<List<EatenFood>>
            ) {
                for (i in 0 until response.body()!!.size) {
                    n += response.body()!![i].eaten_food.calorie
                }
            }
            override fun onFailure(call: Call<List<EatenFood>>, t: Throwable) {
                println("나오지마..")
            }
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofitforFeed = Retrofit.Builder()
            .baseUrl("http://192.249.18.108:80/") //baseUrl 등록, 반드시 /로 마무리 (protocal(https://) + URL)
            .addConverterFactory(GsonConverterFactory.create()) //Gson 변환기 등록 Json을 Class
            .build()
        val serviceforFeed = retrofitforFeed.create(RetrofitInterface::class.java) // Retrofit instance로 interface 객체 구현
        val callFeeds = serviceforFeed.requestFeedsByUserId(2) //user.id로 바꿔야함!!!!
        callFeeds.enqueue(object : Callback<List<Feed>> {
            override fun onResponse(call: Call<List<Feed>>, response: Response<List<Feed>>) {
                //response.body(): (List<Feed>)
                var m=0
                if (response.body() != null) {
                    for(i in 0 until (response.body()!!.size)) {
                        if (i==0) {
                            feeds.add(arrayListOf(Feed(response.body()!![i].id, response.body()!![i].uploader, response.body()!![i].feed_photo, response.body()!![i].likes, response.body()!![i].upload_time, response.body()!![i].update_time, response.body()!![i].eat_date, response.body()!![i].diet_explain)))
                        }
                        else {
                            if (response.body()!![i-1].eat_date == response.body()!![i].eat_date) {
                                feeds[m].add(Feed(response.body()!![i].id, response.body()!![i].uploader, response.body()!![i].feed_photo, response.body()!![i].likes, response.body()!![i].upload_time, response.body()!![i].update_time, response.body()!![i].eat_date, response.body()!![i].diet_explain))
                            }
                            else {
                                m++
                                feeds.add(arrayListOf(Feed(response.body()!![i].id, response.body()!![i].uploader, response.body()!![i].feed_photo, response.body()!![i].likes, response.body()!![i].upload_time, response.body()!![i].update_time, response.body()!![i].eat_date, response.body()!![i].diet_explain)))
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Feed>>, t: Throwable) {
                Log.d("error1111", "error")
            }
        })
        for (i in 0 until (feeds.size)) {
            for (j in 0 until (feeds[i].size)) {
                getCaloriesFromFeed(feeds[i][j])
            }
            calories.add(arrayListOf(feeds[i][0].eat_date.toString(), n.toString()))
            n=0
        }


        binding = FragmentCalendarBinding.inflate(layoutInflater)
        val today = GregorianCalendar()
        calYear = today.get(1)
        calMonth = today.get(2)
        setCalendarList(calYear,calMonth)
        calAdapter = CalendarGridViewAdapter(requireContext(),mCalendar)
        binding.calendarMonth.text = (calMonth+1).toString()
        binding.calendarYear.text = calYear.toString()
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