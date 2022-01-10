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
        innerUrl1.add("https://user-images.githubusercontent.com/64190044/148642694-df63295e-9e26-456f-b2dd-59ecc3f92aca.png")
        innerUrl1.add("https://user-images.githubusercontent.com/64190044/148642701-1848582d-ecf1-426d-ba9c-6272c93a671d.png")
        innerUrl1.add("https://user-images.githubusercontent.com/64190044/148642706-7fb9f9fa-058b-4543-ad3a-8b29ea277089.png")
        innerUrl1.add("https://user-images.githubusercontent.com/64190044/148642708-5a4e707a-8f93-4b9e-9436-ef5b98153e1d.png")

        innerUrl2.add("https://user-images.githubusercontent.com/64190044/148642706-7fb9f9fa-058b-4543-ad3a-8b29ea277089.png")
        innerUrl2.add("https://user-images.githubusercontent.com/64190044/148642708-5a4e707a-8f93-4b9e-9436-ef5b98153e1d.png")
        innerUrl2.add("https://user-images.githubusercontent.com/64190044/148642694-df63295e-9e26-456f-b2dd-59ecc3f92aca.png")
        innerUrl2.add("https://user-images.githubusercontent.com/64190044/148642701-1848582d-ecf1-426d-ba9c-6272c93a671d.png")

        innerUrl3.add("https://user-images.githubusercontent.com/64190044/148642694-df63295e-9e26-456f-b2dd-59ecc3f92aca.png")
        innerUrl3.add("https://user-images.githubusercontent.com/64190044/148642701-1848582d-ecf1-426d-ba9c-6272c93a671d.png")
        innerUrl3.add("https://user-images.githubusercontent.com/64190044/148642706-7fb9f9fa-058b-4543-ad3a-8b29ea277089.png")
        innerUrl3.add("https://user-images.githubusercontent.com/64190044/148642708-5a4e707a-8f93-4b9e-9436-ef5b98153e1d.png")

        innerUrl4.add("https://user-images.githubusercontent.com/64190044/148642706-7fb9f9fa-058b-4543-ad3a-8b29ea277089.png")
        innerUrl4.add("https://user-images.githubusercontent.com/64190044/148642708-5a4e707a-8f93-4b9e-9436-ef5b98153e1d.png")
        innerUrl4.add("https://user-images.githubusercontent.com/64190044/148642694-df63295e-9e26-456f-b2dd-59ecc3f92aca.png")
        innerUrl4.add("https://user-images.githubusercontent.com/64190044/148642701-1848582d-ecf1-426d-ba9c-6272c93a671d.png")


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
        binding.feedsRecyclerview.adapter = adapter
        binding.feedsRecyclerview.layoutManager = LinearLayoutManager(activity)

        binding.adddietbtn.setOnClickListener {

            val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout,FeedAdd())
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