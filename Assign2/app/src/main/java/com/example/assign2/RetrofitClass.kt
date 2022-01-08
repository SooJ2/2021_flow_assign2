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

//data class User(
//    @SerializedName("id")
//    val id: String,
//    @SerializedName("")
//    val password: String
//)

data class Userinfo(
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("private")
    val private: Boolean,
    @SerializedName("profile_photo")
    val profile_photo: Image
)
{
    override fun toString(): String {
        return "Userinfo{user_id=$user_id, private=$private, profile_photo=$profile_photo}"
    }
}

data class Follow(
    @SerializedName("following_id")
    val following_id: Int,
    @SerializedName("follower_id")
    val follower_id: Int
)
{
    override fun toString(): String {
        return "Follow{following_id=$following_id, follower_id=$follower_id}"
    }
}

data class Feed(
    @SerializedName("uploader")
    val uploader: Int,
    @SerializedName("feed_photo")
    val feed_photo: Image,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("upload_time")
    val upload_time: DateTimeFormatter,
    @SerializedName("update_time")
    val update_time: DateTimeFormatter,
    @SerializedName("eat_date")
    val eat_date: DatePicker,
    @SerializedName("diet_explain")
    val diet_explain: String
)
{
    override fun toString(): String {
        return "Feed{uploader=$uploader, feed_photo=$feed_photo, likes=$likes, upload_time=$upload_time, update_time=$update_time, eat_date=$eat_date, diet_explain=$diet_explain}"
    }
}

data class Comment(
    @SerializedName("feed")
    val feed: Feed,
    @SerializedName("writer")
    val writer: Int,
    @SerializedName("text")
    val text: String
)
{
    override fun toString(): String {
        return "Comment{feed=$feed, writer=$writer, text=$text}"
    }
}

data class Food(
    @SerializedName("name")
    val name: String,
    @SerializedName("calorie")
    val calorie: Int
)
{
    override fun toString(): String {
        return "Food{name=$name, calorie=$calorie}"
    }
}

data class EatenFood(
    @SerializedName("eater")
    val eater: Int,
    @SerializedName("eaten_food")
    val eaten_food: Food
)
{
    override fun toString(): String {
        return "EatenFood{eater=$eater, eaten_food=$eaten_food}"
    }
}