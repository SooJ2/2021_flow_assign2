package com.example.assign2

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assign2.databinding.OndayFeedsImagesBinding

import kotlin.properties.Delegates

class MyFeedInnerAdapter(val context: Context, userId: String, data: ArrayList<String>): RecyclerView.Adapter<MyFeedInnerAdapter.MyFeedInnerViewHolder>() {

    var images = data
    val userId = userId


    inner class MyFeedInnerViewHolder(private val binding: OndayFeedsImagesBinding): RecyclerView.ViewHolder(binding.root){

        fun setImage(url: String){
            println("@@@@@@@@${images.count()}")
            println("#####################: $images")
            Glide.with(context).load(url).into(binding.myFeedImageView)
        }

    }


    override fun getItemCount(): Int {
        return images.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFeedInnerViewHolder {
        val binding = OndayFeedsImagesBinding.inflate(LayoutInflater.from(parent.context))
        binding.myFeedImageView.setOnClickListener {
            var nextFragment = MyFeedDetail()
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
            transaction.addToBackStack("image")
            transaction.commit()        }
        return MyFeedInnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyFeedInnerViewHolder, position: Int) {
        holder.setImage(images[position])
    }

}