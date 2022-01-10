package com.example.assign2

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assign2.databinding.FeedAddFoodItemBinding
import com.example.assign2.databinding.FeedAddFoodSearchBinding
import java.util.*
import kotlin.collections.ArrayList

class FeedAddFoodSearchAdapter(val context: Context, userId: String, foods: ArrayList<Any>): RecyclerView.Adapter<FeedAddFoodSearchAdapter.FeedAddFoodSearchViewHolder>()  {

    inner class FeedAddFoodSearchViewHolder(private val binding: FeedAddFoodSearchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item:ArrayList<Objects>) {
            binding.FeedAddFoodSearchFoodNameTv
            binding.FeedAddFoodSearchFoodNameTv
            binding.FeedAddFoodSearchListLinearLayout.setOnClickListener{

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAddFoodSearchViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FeedAddFoodSearchViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}