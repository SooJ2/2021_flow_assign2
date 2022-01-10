package com.example.assign2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assign2.databinding.FragmentFeedAddFoodSearchBinding
import java.util.*
import kotlin.collections.ArrayList


class FeedAddFoodSearch : Fragment() {

    lateinit var binding:FragmentFeedAddFoodSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentFeedAddFoodSearchBinding.inflate(layoutInflater)
        binding.FeedAddFoodSearchRecyclerView.adapter = FeedAddFoodSearchAdapter(requireContext(),"임의로 넣음",ArrayList<Any>())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed_add_food_search, container, false)
    }

}