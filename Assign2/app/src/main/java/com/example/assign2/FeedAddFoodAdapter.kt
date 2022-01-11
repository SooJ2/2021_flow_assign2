package com.example.assign2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assign2.databinding.FeedAddFoodItemBinding
import com.example.assign2.databinding.FragmentFeedAddBinding
import java.util.*
import kotlin.collections.ArrayList

class FeedAddFoodAdapter(val context: Context,foods: ArrayList<Food>): RecyclerView.Adapter<FeedAddFoodAdapter.FeedAddFoodViewHolder>() {

    var foodList = foods
    lateinit var binding: FeedAddFoodItemBinding

    inner class FeedAddFoodViewHolder(private val binding: FeedAddFoodItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:Food) {
            binding.addFeedFoodNameTextView.text = item.name
            binding.addFeedCalorieTextView.text = item.calorie
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAddFoodViewHolder {
        binding = FeedAddFoodItemBinding.inflate(LayoutInflater.from(context))
        return FeedAddFoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedAddFoodViewHolder, position: Int) {
        holder.bind(foodList[position])
        binding.addFeedCancelButton.setOnClickListener {
            if (foodList.size == 0) {
                foodList = ArrayList<Food>()

            } else {
                foodList.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        println("members######## $foodList")
        return foodList.count()
    }

    fun setData(foods: ArrayList<Food>){
        foodList = foods
    }
}