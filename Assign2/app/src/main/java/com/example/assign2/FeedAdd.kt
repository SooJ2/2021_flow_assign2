package com.example.assign2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assign2.databinding.FragmentFeedAddBinding
import com.google.zxing.integration.android.IntentIntegrator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList


class FeedAdd : Fragment() {

    lateinit var binding: FragmentFeedAddBinding
//    var barcodes: ArrayList<String> = ArrayList<String>()
    var barcodes:String = ""
    var foodList: ArrayList<Any> = ArrayList<Any>() // 임시로
    var userId:String = "userid" // 임시로 해둠

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFeedAddBinding.inflate(layoutInflater)
        binding.FeedAddBarcodeButton.setOnClickListener {
            val integrator = IntentIntegrator(activity)
            integrator.setBeepEnabled(false) // 소리 유무
            integrator.setOrientationLocked(true) // 세로 가로 모드 고정
            integrator.setPrompt("바코드를 인증해주세요") // 원하는 문구 넣어주기
            integrator.initiateScan()
        }
        binding.FeedAddFoodRecyclerView.adapter = FeedAddFoodAdapter(requireContext(),userId,foodList)

        binding.FeedAddSearchButton.setOnClickListener {
            val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout,FeedAddFoodSearch())
            transaction.addToBackStack("add")
            transaction.commit() }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        if(barcodes != ""){

            val retrofit= Retrofit.Builder()
                .baseUrl("https://openapi.foodsafetykorea.go.kr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val barcodeService = retrofit.create(BarcodeService::class.java)
            barcodeService.getFoodByBarcode(barcodes).enqueue(object : Callback<Barcode> {
                override fun onResponse(call: Call<Barcode>, response: Response<Barcode>) {
                    if (response.isSuccessful.not()){
                        Log.e("BARCODE","NOT SUCCESS")
                        return
                    }
                    response.body()?.let{

                        if(it.c005.totalCount != "0"){
                            val tmpName = it.c005.row[0].PRDLST_NM
                            binding.FeedAddFoodNameTextView.setText(it.c005.row[0].PRDLST_NM)
                            /*칼로리 매칭해서 찾아오기!!!!*/
                        }
                        else{
                            Toast.makeText(context,"해당 바코드 정보를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }

                }

                override fun onFailure(call: Call<Barcode>, t: Throwable) {
                    Log.e("BARCODE",call.toString())

                }
            })
        }

    }

}