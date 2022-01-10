package com.example.assign2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var calendar:Calendar
    lateinit var myFeed:MyFeed
    lateinit var community:Community

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        calendar = Calendar()
        myFeed = MyFeed()
        community = Community()

        supportFragmentManager.beginTransaction().add(R.id.frameLayout,calendar).commit()


        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> {
                        replaceView(calendar)
                    }
                    1 -> {
                        replaceView(myFeed)
                    }
                    2 -> {
                        replaceView(community)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    private fun replaceView(tab: Fragment) {
        var selectedFragment: Fragment? = null
        selectedFragment = tab
        selectedFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, it).commit()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        println("********************************************")

        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if(result != null) {
            if (result.contents == null) {
                println("********************************************cancel")
                Toast.makeText(this,"QR코드 인증이 취소되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                println("********************************************sucess")
                Toast.makeText(this,"INFO: ${result.contents}",Toast.LENGTH_LONG).show()
                val frg = (supportFragmentManager.findFragmentById(R.id.frameLayout)) as FeedAdd
                val barcode:String  = result.contents
                frg.barcodes = barcode

                }
        }else{
            println("********************************************in else stmt")
            super.onActivityResult(requestCode,resultCode,data)
        }
    }
}
