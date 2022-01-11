package com.example.assign2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assign2.databinding.FeedAddFoodItemBinding
import com.example.assign2.databinding.FeedAddFoodSearchBinding
import java.util.*
import kotlin.collections.ArrayList

class FeedAddFoodSearchAdapter(val context: Context, userId: String, foods: ArrayList<Food>): RecyclerView.Adapter<FeedAddFoodSearchAdapter.FeedAddFoodSearchViewHolder>()  {

    var foodList = foods

    inner class FeedAddFoodSearchViewHolder(private val binding: FeedAddFoodSearchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Food) {
            binding.FeedAddFoodSearchFoodNameTv.text = item.name
            binding.FeedAddFoodSearchCalTv.text = item.calorie
            binding.FeedAddFoodSearchListLinearLayout.setOnClickListener{

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAddFoodSearchViewHolder {
//        println("&&&&&&&&&&&&&&&&$foodList")
        val binding = FeedAddFoodSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FeedAddFoodSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedAddFoodSearchViewHolder, position: Int) {
        holder.bind(foodList[position])
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun setData(foods: ArrayList<Food>){
        foodList = foods
    }
}