package com.example.assign2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.assign2.databinding.FragmentMyFeedDetailBinding


class MyFeedDetail : Fragment() {
    private lateinit var binding: FragmentMyFeedDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMyFeedDetailBinding.inflate(layoutInflater)
        binding.FeedLinearLayout3.setOnClickListener{

//            println("%%%%%%%%%%%%%%%%%%%%%%%clicked")
            var nextFragment = MyFeedMoreDetail()
//            var bundle = Bundle()
//            bundle.putInt("index",index as Int)
//            bundle.putString("name",name)
//            bundle.putString("phone",tmp)
//            bundle.putString("address",address)
//            bundle.putString("detailAddress",detailAddress)
//            bundle.putInt("icon",iconData as Int)

//            nextFragment.arguments = bundle
            val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, nextFragment)
            transaction.addToBackStack("detail")
            transaction.commit()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

}