package com.example.assign2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.properties.Delegates

class MyFeedAdapter(val context: Context,userId: Int): RecyclerView.Adapter<MyFeedAdapter.myFeedViewHolder>() {
    var datas = mutableListOf<OneDayData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFeedAdapter.myFeedViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.oneday_feeds, parent, false)
        return myFeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyFeedAdapter.Holder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    inner class myFeedViewHolder(private val binding: ) : RecyclerView.ViewHolder(view) {
        private val txtDate: TextView = itemView.findViewById(R.id.eatdate)
//        private val photos: List<ImageView> = itemView.findViewById(R.id.inner_recyclerview)
        fun bind(item:OneDayData) {
//            txtDate.text = item.Date
//            for (i in 0 .. photos.lastIndex) {
//                Glide.with(itemView).load(item.photos[i]).into(photos[i])
//            }

        }
    }


    override fun onBindViewHolder(holder: myFeedViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}