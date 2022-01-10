package com.example.assign2

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assign2.databinding.FeedAddFoodItemBinding
import java.util.*
import kotlin.collections.ArrayList

class FeedAddFoodAdapter(val context: Context, userId: String, foods: ArrayList<Any>): RecyclerView.Adapter<FeedAddFoodAdapter.FeedAddFoodViewHolder>() {

    val id = userId
    var foodList = foods

    inner class FeedAddFoodViewHolder(private val binding: FeedAddFoodItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item:ArrayList<Objects>) {
            binding.addFeedCalorieTextView.text = " "
            binding.addFeedCalorieTextView.text = " "
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAddFoodViewHolder {
        TODO("Not yet implemented")

    }

    override fun onBindViewHolder(holder: FeedAddFoodViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return foodList.count()
    }
}