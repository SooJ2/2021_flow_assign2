//package com.example.assign2
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class CalendarAdapter(mCalendarData: List<Any>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    private val HEADER_TYPE = 0;
//    private val EMPTY_TYPE = 1;
//    private val DAY_TYPE = 2;
//
//    private var mCalendar:List<Any> = mCalendarData
//
//
//    inner class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        var headerTextView: TextView = view.findViewById(R.id.calendarHeaderTextView)
//        fun bind(position: Int){
//            headerTextView.text = mCalendar[position].toString()
//        }
//
//    }
//    inner class EmptyViewHolder(view: View): RecyclerView.ViewHolder(view) {
//
//    }
//    inner class DayViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        var dayTextView: TextView = view.findViewById(R.id.calendarDayTextView)
//        fun bind(position: Int){
//            dayTextView.text = mCalendar[position].toString()
//        }
//
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        val item = mCalendar.get(position)
//
//        if(item is Long)
//            return HEADER_TYPE
//        else if (item is String)
//            return EMPTY_TYPE
//        else
//            return DAY_TYPE
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        if(viewType == HEADER_TYPE){
//           return HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_header,parent,false))
//        }
//        else if(viewType == EMPTY_TYPE){
//            return EmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_day_empty,parent,false))
//        }
//        else{
//            return DayViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_day,parent,false))
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val viewType = getItemViewType(position)
//
//        if (viewType == HEADER_TYPE){
//            val item = mCalendar.get(position)
//            if(item is Long){
//                (holder as HeaderViewHolder).bind(position)
//            }
//        }
//        else if(viewType == EMPTY_TYPE){
//
//        }
//        else if(viewType == DAY_TYPE){
//            val item = mCalendar.get(position)
//            if(item is Long){
//                (holder as DayViewHolder).bind(position)
//            }
//        }
//
//    }
//
//    override fun getItemCount(): Int {
//        if(mCalendar != null){
//            return mCalendar.size
//        }
//
//        return 0
//    }
//
//
//}