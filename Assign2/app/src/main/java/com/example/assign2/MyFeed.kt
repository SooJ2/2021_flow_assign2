package com.example.assign2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assign2.databinding.FragmentMyFeedBinding
import kotlin.properties.Delegates

class MyFeed : Fragment() {

    var fragmentView : View? = null
    private lateinit var binding: FragmentMyFeedBinding
    private lateinit var adapter: MyFeedAdapter
    var userId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        fragmentView = LayoutInflater.from(activity).inflate(R.layout.fragment_my_feed, container, false)
//        return fragmentView
        adapter = MyFeedAdapter(requireContext(),userId)
        binding = FragmentMyFeedBinding.inflate(layoutInflater)
        binding.feedsRecyclerview.adapter = adapter
        binding.feedsRecyclerview.layoutManager = LinearLayoutManager(activity)

        return binding.root
    }
    inner class MyFeedFragmentRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

    }

}