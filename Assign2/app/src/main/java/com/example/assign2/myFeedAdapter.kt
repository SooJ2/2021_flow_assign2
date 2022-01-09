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
import com.example.assign2.databinding.FragmentMyFeedBinding
import com.example.assign2.databinding.OnedayFeedsBinding

class MyFeedAdapter(val context: Context, userId: Int, data: ArrayList<ArrayList<String>>): RecyclerView.Adapter<MyFeedAdapter.myFeedViewHolder>() {
    var datas = data
    val userId = userId

    inner class myFeedViewHolder(private val binding: OnedayFeedsBinding ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item:ArrayList<String>) {
            val adapter = MyFeedInnerAdapter(context, userId, item)
            binding.innerRecyclerview.adapter = adapter
//            val manager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            binding.innerRecyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            binding.myFeedDate.text = "2022.01.08"
        }
    }


    override fun onBindViewHolder(holder: myFeedViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myFeedViewHolder {
        val bindingInner = OnedayFeedsBinding.inflate(LayoutInflater.from(context))
        return myFeedViewHolder(bindingInner)
    }

    override fun getItemCount(): Int {
        return datas.count()
    }
}