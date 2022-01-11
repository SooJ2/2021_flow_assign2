package com.example.assign2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assign2.databinding.FragmentMyFeedBinding
import kotlin.properties.Delegates

class MyFeed : Fragment() {

    var fragmentView : View? = null
    private lateinit var binding: FragmentMyFeedBinding
    private lateinit var adapter: MyFeedAdapter
    var userId:String = "user1" //수정해야
    var datas = ArrayList<ArrayList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*tmp dummy datas*/
        val innerUrl1= ArrayList<String>()
        val innerUrl2= ArrayList<String>()
        val innerUrl3= ArrayList<String>()
        val innerUrl4= ArrayList<String>()
        val outerUrl = ArrayList<ArrayList<String>>()
        innerUrl1.add("https://user-images.githubusercontent.com/64190044/148931545-13ff2bf4-808c-4b6f-8bfa-91b6cd4597bc.jpeg")
        innerUrl1.add("https://user-images.githubusercontent.com/64190044/148931626-da679248-0f1b-406c-a731-c2a6f93a5d44.jpeg")
        innerUrl1.add("https://user-images.githubusercontent.com/64190044/148931632-41e35718-c087-421c-9ab0-d2fff2ee8447.jpeg")
        innerUrl1.add("https://user-images.githubusercontent.com/64190044/148931548-6e62fcb0-5049-4be8-a38d-44dde51fc289.jpeg")

        innerUrl2.add("https://user-images.githubusercontent.com/64190044/148931551-05f89acf-26db-4ddf-a1b9-620df2fdcdea.jpeg")
        innerUrl2.add("https://user-images.githubusercontent.com/64190044/148931555-fd39edfc-1d93-486d-9bea-bc25dbfab755.jpeg")
        innerUrl2.add("https://user-images.githubusercontent.com/64190044/148931559-32760cf2-29a4-48d0-97ea-6b85b3978ca8.jpeg")
        innerUrl2.add("https://user-images.githubusercontent.com/64190044/148931641-2c8f79cc-d275-437b-9a96-da002bf591fd.jpeg")

        innerUrl3.add("https://user-images.githubusercontent.com/64190044/148931516-7de01d49-877f-4e4c-914c-66af89c3e514.jpeg")
        innerUrl3.add("https://user-images.githubusercontent.com/64190044/148931524-9dfa483c-1838-42eb-b9c5-0a73dd921163.jpeg")
        innerUrl3.add("https://user-images.githubusercontent.com/64190044/148931528-8f055339-1f14-4257-a049-c63fd2a9f1b6.jpeg")
        innerUrl3.add("https://user-images.githubusercontent.com/64190044/148931533-fd2b69aa-8167-41d2-96d3-25de88f2551b.jpeg")

        innerUrl4.add("https://user-images.githubusercontent.com/64190044/148931535-52ce5f83-6160-4315-886b-7f525ea3dc1a.jpeg")
        innerUrl4.add("https://user-images.githubusercontent.com/64190044/148931540-7049b62c-78ba-4c2e-8edf-196f69158103.jpeg")
        innerUrl4.add("https://user-images.githubusercontent.com/64190044/148931542-b7d23012-ea31-4c2d-95c2-bf60ce7a7ae2.jpeg")
        innerUrl4.add("https://user-images.githubusercontent.com/64190044/148931543-1587571d-0fb6-46e2-936f-48774e4b34e7.jpeg")



        outerUrl.add(innerUrl1)
        outerUrl.add(innerUrl2)
        outerUrl.add(innerUrl3)
        outerUrl.add(innerUrl4)

        datas = outerUrl
        /*end dummy data*/




    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        adapter = MyFeedAdapter(requireContext(),userId,datas)
        binding = FragmentMyFeedBinding.inflate(layoutInflater)
        binding.myFeedUserId.setText((activity as MainActivity).user.email)

        binding.feedsRecyclerview.adapter = adapter
        binding.feedsRecyclerview.layoutManager = LinearLayoutManager(activity)

        binding.adddietbtn.setOnClickListener {

            val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout,FeedAdd(),"ADD")
            transaction.addToBackStack("myfeed")
            transaction.commit()
        }

        return binding.root
    }
    override fun onDestroyView() {
        binding = FragmentMyFeedBinding.inflate(layoutInflater)
        super.onDestroyView()
    }


}