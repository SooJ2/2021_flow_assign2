package com.example.assign2


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.assign2.databinding.FragmentCalendarBinding
import java.lang.Exception
import java.util.*
import java.util.Calendar.MONTH
import kotlin.collections.ArrayList


class Calendar : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

    var mCalendar = ArrayList<String>()
    lateinit var month: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCalendarBinding.inflate(layoutInflater)
        val gridView = binding.calendar
        gridView.adapter = CalendarGridViewAdapter(requireContext(),mCalendar)
        val today = GregorianCalendar()

        month = (today.get(2)).toString()
        setCalendarList(today.get(1),today.get(2))
        println("##############MONTH: $month!!")
        println("@@@@@@@@@@@@@@@@@@@@ ${mCalendar}")
        binding.calendarMonth.text= month

        return binding.root
    }

    override fun onDestroyView() {
        binding = FragmentCalendarBinding.inflate(layoutInflater)
        super.onDestroyView()
    }

    fun setCalendarList(year:Int, month: Int){

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