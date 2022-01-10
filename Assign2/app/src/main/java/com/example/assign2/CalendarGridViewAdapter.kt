package com.example.assign2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_calendar.view.*

class CalendarGridViewAdapter(private var context: Context, dateList: ArrayList<String>): BaseAdapter() {

    var dates = dateList

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
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val calendarView:View = inflater.inflate(R.layout.item_calendar,null)
        calendarView.calendarDayTextView.text = date
        return calendarView
    }

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
    }

    fun update(dateList: ArrayList<String>){
        dates = dateList
        notifyDataSetChanged()
    }
}