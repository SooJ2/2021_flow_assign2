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

//어떤 형식으로 output 받을지 결정.
data class Login(
    var email: String,
    var password: String
)
{
    override fun toString(): String {
        return "Userinfo{email=$email, password=$password}"
    }
}

data class User(
    @SerializedName("email")
    val email: String?,
    @SerializedName("is_active")
    val is_active: Boolean,
    @SerializedName("is_admin")
    val is_admin: Boolean,
    @SerializedName("private")
    val private: Boolean,
    @SerializedName("profile_photo")
    val profile_photo: String?
)
{
    override fun toString(): String {
        return "Userinfo{email=$email, private=$private, profile_photo=$profile_photo, is_active=$is_active}"
    }
}

data class KakaoUser(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String?,
    @SerializedName("profile_photo")
    val profile_photo: String?
)
{
    override fun toString(): String {
        return "Kakaouser{id=$id, email=$email, profile_photo=$profile_photo}"
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
    @SerializedName("id")
    val id: Int,
    @SerializedName("uploader")
    val uploader: Int,
    @SerializedName("feed_photo")
    val feed_photo: String,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("upload_time")
    val upload_time: String,
    @SerializedName("update_time")
    val update_time: String,
    @SerializedName("eat_date")
    val eat_date: String,
    @SerializedName("diet_explain")
    val diet_explain: String
)
{
    override fun toString(): String {
        return "Feed{id = $id, uploader=$uploader, feed_photo=$feed_photo, likes=$likes, eat_date=$eat_date, diet_explain=$diet_explain}"
    }
}

//data class Feed(
//    val feed_photo: String,
//    val date: String,
//    val food: List<String>,
//    val memo: String
//)

data class Comment(
    @SerializedName("feed")
    val feed: Int,
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
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("calorie")
    val calorie: String
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
    val eaten_food: Int,
    @SerializedName("eaten_calorie")
    val eaten_calorie: String
)
{
    override fun toString(): String {
        return "EatenFood{eater=$eater, eaten_food=$eaten_food}"
    }
}

