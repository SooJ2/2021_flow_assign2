package com.example.assign2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.assign2.databinding.FragmentMyFeedMoreDetailBinding
import com.example.assign2.databinding.MyFeedMoreDetailCommentBinding

class MyFeedMoreDetailCommentAdapter(private var context: Context, commentList: ArrayList<String>): BaseAdapter(){

    val comList = commentList
    lateinit var binding: MyFeedMoreDetailCommentBinding

    override fun getCount(): Int {
        return comList.count()
    }

    override fun getItem(position: Int): Any {
        return comList[position]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val gridView :View = inflater.inflate(R.layout.my_feed_more_detail_comment,null)

        return gridView

    }
}