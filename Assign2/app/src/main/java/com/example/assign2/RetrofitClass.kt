package com.example.assign2

import android.media.Image
import android.widget.DatePicker
import android.widget.ImageView
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Time
import java.time.format.DateTimeFormatter

object RetrofitClass {
    private val retrofit = Retrofit.Builder()
        .baseUrl("172.10.5.118")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

data class User(
    @SerializedName("id")
    val userName: String,
    @SerializedName("")
    val password: String
)

data class Userinfo(
    val userId: User,
    val private: Boolean,
    val profilePhoto: Image
)

data class Follow(
    val followingId: User,
    val followerId: User
)

data class Feed(
    val uploader: User,
    val feedPhoto: Image,
    val likes: Int,
    val uploadTime: DateTimeFormatter,
    val updateTime: DateTimeFormatter,
    val eatDate: DatePicker,
    val dietExplain: String
)

data class Comment(
    val feed: Feed,
    val writer: User,
    val text: String
)

data class Food(
    val name: String,
    val calorie: Int
)

data class EatenFood(
    val eater: User,
    val eatenFood: Food
)