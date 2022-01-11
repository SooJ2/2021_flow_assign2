package com.example.assign2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_calendar.view.*

class CalendarGridViewAdapter(private var context: Context, dateList: ArrayList<String>): BaseAdapter() {

    var dates = dateList
    var eatdates: ArrayList<String> = arrayListOf()
    var calories: ArrayList<String> = arrayListOf()


    override fun getCount(): Int {
        return dates.size
    }

    override fun getItem(p0: Int): Any {
        return dates[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val date = dates[position]
        Log.d("22222", date)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val calendarView:View = inflater.inflate(R.layout.item_calendar,null)
        calendarView.calendarDayTextView.text = date
        if (eatdates.size != 0) {
            if (eatdates[0].length != 0) {
                for (i in 0 until eatdates.size) {
                    Log.d("wpqkfehofk", eatdates[i].substring(7) + "  " + date + "  " + calories[i])
                    if (eatdates[i].substring(8).equals(date) && calories[i].toInt() < 500) {
                        calendarView.calendarImageView.setImageResource(R.drawable.image_veg)
                    }
                    else if (eatdates[i].substring(8).equals(date) && calories[i].toInt() < 1000 && calories[i].toInt()>=500) {
                        calendarView.calendarImageView.setImageResource(R.drawable.image_egg)
                    }
                    else if (eatdates[i].substring(8).equals(date) && calories[i].toInt() > 1000) {
                        calendarView.calendarImageView.setImageResource(R.drawable.image_meat)
                    }
                }
            }
        }
        return calendarView
    }

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
        Log.d("RNT!!", eatdates.toString() + "   " +calories.toString())

    }

    fun update(dateList: ArrayList<String>){
        dates = dateList
        notifyDataSetChanged()
    }
    fun getInfo(date: String, calorie: String) {
        var a: String = ""
        var b: String = ""
        a += date
        b += calorie
        eatdates.add(a)
        calories.add(b)
        Log.d("RNT??", eatdates.toString() + "   " +calories.toString())

    }
}