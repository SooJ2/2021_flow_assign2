package com.example.assign2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.recyclerview.widget.RecyclerView
import com.example.assign2.databinding.FeedAddFoodItemBinding
import com.example.assign2.databinding.FeedAddFoodSearchBinding
import java.util.*
import kotlin.collections.ArrayList

class FeedAddFoodSearchAdapter(val context: Context, foods: ArrayList<Food>): RecyclerView.Adapter<FeedAddFoodSearchAdapter.FeedAddFoodSearchViewHolder>()  {

    var foodList = foods

    inner class FeedAddFoodSearchViewHolder(private val binding: FeedAddFoodSearchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Food) {
            binding.FeedAddFoodSearchFoodNameTv.text = item.name
            binding.FeedAddFoodSearchCalTv.text = item.calorie

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAddFoodSearchViewHolder {
        val binding = FeedAddFoodSearchBinding.inflate(LayoutInflater.from(parent.context))
        return FeedAddFoodSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedAddFoodSearchViewHolder, position: Int) {
        holder.bind(foodList[position])

        holder.itemView.setOnClickListener {
//            (context as AppCompatActivity).supportFragmentManager.popBackStackImmediate()

            var bundle = Bundle()
            bundle.putInt("id",foodList[position].id)
            bundle.putString("name",foodList[position].name)
            bundle.putString("cal",foodList[position].calorie)


//            nextFragment.arguments = bundle
            val manager = (context as AppCompatActivity).supportFragmentManager
            manager.popBackStack("search",POP_BACK_STACK_INCLUSIVE)
            val fragment:FeedAdd = manager.findFragmentByTag("ADD") as FeedAdd
//            fragment.binding.FeedAddFoodNameTextView.setText(foodList[position].name)
//            fragment.binding.FeedAddFoodCalTextView.setText(foodList[position].calorie.toString())
            fragment.arguments = bundle
            val tmp = manager.beginTransaction()
            tmp.addToBackStack("add")
            tmp.replace(R.id.frameLayout,fragment,"ADD")
            tmp.commit()


        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun setData(foods: ArrayList<Food>){
        foodList = foods
    }
}