package com.example.assign2

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Gallery
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assign2.databinding.FragmentFeedAddBinding
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_feed_add_food_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class FeedAdd : Fragment() {

    lateinit var binding: FragmentFeedAddBinding
//    var barcodes: ArrayList<String> = ArrayList<String>()
    var barcodes:String = ""
    var foodList: ArrayList<Food> = ArrayList<Food>() // 임시로
    var userId:String = "userid" // 임시로 해둠
    lateinit var addAdapter: FeedAddFoodAdapter
    var tempId = -1
    var check = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentFeedAddBinding.inflate(layoutInflater)
        binding.FeedAddImageView.setOnClickListener {
            loadImage()
        }
        binding.FeedAddBarcodeButton.setOnClickListener {
            val integrator = IntentIntegrator(activity)
            integrator.setBeepEnabled(false) // 소리 유무
            integrator.setOrientationLocked(true) // 세로 가로 모드 고정
            integrator.setPrompt("바코드를 인증해주세요") // 원하는 문구 넣어주기
            integrator.initiateScan()
        }
        binding.FeedAddFoodRecyclerView.adapter = FeedAddFoodAdapter(requireContext(),foodList)
        binding.FeedAddFoodRecyclerView.layoutManager = LinearLayoutManager(activity)

        /*여기랑*/
        if ( check){
        check = false
        var bundle = Bundle()
        bundle.putString("name",binding.FeedAddFoodNameTextView.text.toString())
        binding.FeedAddFoodNameTextView.text = null
        val manager = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        val next = FeedAddFoodSearch()
        next.arguments = bundle
        manager.replace(R.id.frameLayout,next)
        manager.addToBackStack("search")
        manager.commit() }
        /*111111*/
        /*여기랑*/

        binding.FeedAddSearchButton.setOnClickListener {
            val transaction = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout,FeedAddFoodSearch())
            transaction.addToBackStack("search")
            transaction.commit()
        }
        /*22222*/

        binding.FeedAddFoodPlusButton.setOnClickListener {
            if (binding.FeedAddFoodNameTextView.text != null && binding.FeedAddFoodCalTextView.text != null && tempId != -1) {
                foodList.add(Food(tempId,binding.FeedAddFoodNameTextView.text.toString(),binding.FeedAddFoodCalTextView.text.toString()))
                println("^^^^^^^^^^^^^$foodList")
                binding.FeedAddFoodNameTextView.setText("")
                binding.FeedAddFoodCalTextView.setText("")

                tempId = -1
                addAdapter.setData(foodList)
                addAdapter.notifyDataSetChanged()

            }

        }
        addAdapter = FeedAddFoodAdapter(requireContext(),foodList)

        binding.FeedAddFoodRecyclerView.adapter = addAdapter
        binding.FeedAddFoodRecyclerView.layoutManager = LinearLayoutManager(activity)

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
                            check = true

                            /*칼로리 매칭해서 찾아오기!!!!*/
//                            var bundle = Bundle()
//                            bundle.putString("name",it.c005.row[0].PRDLST_NM)
//
//                            val manager = (context as AppCompatActivity).supportFragmentManager
//                            val next = FeedAddFoodSearch()
//                            next.arguments = bundle
//
//                            val tmp = manager.beginTransaction()
//                            tmp.addToBackStack("search")
//                            tmp.replace(R.id.frameLayout,next)
//                            tmp.commit()

//                            if ( check){
                            barcodes = ""
                                check = true
                                var bundle = Bundle()
                                bundle.putString("name",binding.FeedAddFoodNameTextView.text.toString())
                                binding.FeedAddFoodNameTextView.text = null
                                val manager = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                                val next = FeedAddFoodSearch()
                                next.arguments = bundle
                                manager.replace(R.id.frameLayout,next)
                                manager.addToBackStack("search")
                                manager.commit()
//
//                            }

                            /*매칭하기 끝*/
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

        if (arguments != null){
            val name = arguments?.getString("name").toString()
            val cal = arguments?.getString("cal").toString()
            tempId = arguments?.getInt("id") as Int
            binding.FeedAddFoodNameTextView.setText(name)
            binding.FeedAddFoodCalTextView.setText(cal)
        }

    }

    private fun loadImage(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent,"Load Picture"), 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                var dataUri = data?.data
                try{
                    var bitmap: Bitmap = MediaStore.Images.Media.getBitmap(this.activity?.contentResolver,dataUri)
                    binding.FeedAddImageView.setImageBitmap(bitmap)
                }catch(e:Exception){
                    Toast.makeText(this.context,"$e",Toast.LENGTH_SHORT).show()
                }
            } else{

            }
        }
    }

}