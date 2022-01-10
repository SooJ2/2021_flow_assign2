package com.example.assign2

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assign2.databinding.FeedAddFoodItemBinding

class CommunityFeedAdapter(val context: Context, userId: String, data: ArrayList<Any>) : RecyclerView.Adapter<CommunityFeedAdapter.CommunityFeedViewHolder>() {

    inner class CommunityFeedViewHolder (private val binding: FeedAddFoodItemBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityFeedViewHolder {
        TODO("Not yet implemented")

    }

    override fun onBindViewHolder(holder: CommunityFeedViewHolder, position: Int) {
        TODO("Not yet implemented")

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}