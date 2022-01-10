package com.example.assign2

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.assign2.databinding.FragmentCommunityBinding
import okhttp3.Dispatcher
import java.net.URL

class Community : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding:FragmentCommunityBinding
    var communityFeedList: ArrayList<Any> = ArrayList() // 나중에 가져오기

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCommunityBinding.inflate(layoutInflater)

        binding.CommunityRecyclerView.adapter = CommunityFeedAdapter(requireContext(),"0",communityFeedList)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

//        val url1 = "https://user-images.githubusercontent.com/64190044/148637441-19bb993e-4946-4985-ba88-82deb356a8fe.png"
//        Glide.with(this).load(url1).into(binding.tmpImageView)
//        binding.tmpTextView.text = "바뀜"

        return  binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


}