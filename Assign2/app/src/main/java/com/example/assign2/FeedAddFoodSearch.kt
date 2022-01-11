package com.example.assign2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assign2.databinding.FragmentFeedAddFoodSearchBinding
import kotlinx.android.synthetic.main.fragment_feed_add_food_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList


class FeedAddFoodSearch : Fragment() {

    lateinit var binding:FragmentFeedAddFoodSearchBinding
    var adapter: FeedAddFoodSearchAdapter? = null
    var foodList = ArrayList<Food>()
    var addFoodList = ArrayList<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentFeedAddFoodSearchBinding.inflate(layoutInflater)
        adapter =  FeedAddFoodSearchAdapter(requireContext(), foodList)
        binding.FeedAddFoodSearchRecyclerView.adapter = adapter
        binding.FeedAddFoodSearchRecyclerView.layoutManager = LinearLayoutManager(activity)

        if (arguments != null){
            val name = arguments?.getString("name").toString()
            binding.FeedAddFoodSearchEditText.setText(name)
        }

        binding.FeedAddFoodSearchButton.setOnClickListener{
            /*retrofit start*/
            println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$")
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.249.18.108:80/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service1 = retrofit.create(RetrofitInterface::class.java)

//            println("**************************${(service1.getFoodByName(FeedAddFoodSearchEditText.text.toString())).toString()}")
            val searchFood = service1.getFoodByName(FeedAddFoodSearchEditText.text.toString())
            searchFood.enqueue(object : Callback<List<Food>>{
                override fun onResponse(call: Call<List<Food>>, response: Response<List<Food>>) {
                    println("########$response")
                    if (response.isSuccessful.not()){
                        Log.e("SEARCH","NOT SUCCESS")
                        return
                    }
                    response.body()?.let{

                        if(it.count() != 0){
                            for(i in 0 until it.count()){
                                foodList.add(it[i])
                            }
                            println("!!!!!!!!!!!!!!!!!!!!!!$foodList")
//                            binding.FeedAddFoodSearchRecyclerView.adapter = FeedAddFoodSearchAdapter(requireContext(),"임의로 넣음", foodList)
                            adapter?.setData(foodList)
                            adapter?.notifyDataSetChanged()
                            foodList = ArrayList<Food>()

                        }
                        else{
                            Toast.makeText(context,"해당 식품 정보를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<List<Food>>, t: Throwable) {
                    println("%^%^%^%^%^%^%^%^%^${service1.getFoodByName("chicken").request().url().toString()}")
                    Log.e("SEARCH","onFailure: ${call.toString()}")
                }

            })

            /*retrofit end*/

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

}