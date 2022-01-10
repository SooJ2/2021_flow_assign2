package com.example.assign2


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.assign2.databinding.FragmentCalendarBinding
import java.lang.Exception
import java.util.*
import java.util.Calendar.MONTH
import kotlin.collections.ArrayList


class Calendar : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

    var mCalendar = ArrayList<String>()
    var calYear = 0
    var calMonth = 0
    var i = 0
    lateinit var calAdapter: CalendarGridViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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