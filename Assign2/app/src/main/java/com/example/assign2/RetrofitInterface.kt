package com.example.assign2

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitInterface {

    //인코딩 해주기
//    @FormUrlEncoded
//    //보낼 곳
//    @POST("/app_login/")
//    fun requestLogin(
//        //input 정보
//        @Field("userid") userid:String,
//        @Field("userpw") userpw:String
//    ) : Call<User> //output 정의
    companion object {
        var retrofitService: RetrofitInterface? = null
        private val gson = GsonBuilder().setLenient().create()

        fun getInstance() : RetrofitInterface {
            if (retrofitService == null) {
                val clientBuilder = OkHttpClient.Builder()
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                clientBuilder.addInterceptor(loggingInterceptor)


                val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.249.18.108:80/") // 서버 주소
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(clientBuilder.build())
                    .build()
                retrofitService = retrofit.create(RetrofitInterface::class.java)
            }
            return retrofitService!!
        }
    }


    @FormUrlEncoded
    @POST("kakaouser/")
    fun setNewKakaoUser(
        @Field("email") email: String?,
        @Field("profile_photo") profile_photo: String?
    ) : Call<KakaoUser>

    @GET("getKakaoUserByEmail/{email}")
    fun requestKakaoUser(
        @Path("email") email: String?
    ) : Call<List<KakaoUser>>

//    @POST("userinfo")
//    fun registerUserInfo(
//        @Field("user_id") user_id:String,
//        @Field("password") password:String
//    )

    @GET("getFeedById/{uploader}")
    fun requestFeedsByUserId(
        @Path("uploader") uploader:Int
    ) : Call<List<Feed>>

    @GET("getEatenFoodById/{eater}")
    fun requestEatsByFeedId(
        @Path("eater") eater:Int
    ) : Call<List<EatenFood>>

    @GET("comment")
    fun requestFoodByFoodId(
        @Query("comment_writer") comment_writer:String
    ) : Call<List<Comment>>


//    @GET("user/{user}")
//    fun getUser( @Path("user") id: String): Call<User>

    @GET("follow/{follow}")
    fun getFollow( @Path("follow") id: String): Call<Follow>

    @GET("feed/{feed}")
    fun getFeed( @Path("feed") id: String): Call<Feed>

    @GET("food/{food}")
    fun getFood( @Path("food") id: Int): Call<Food>

    @GET("getFoodByName/{name}")
//    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    fun getFoodByName( @Path("name") name: String): Call<List<Food>> //

    @GET("eatenfood/{eatenfood}")
    fun getEatenFood( @Path("eatenfood") id: String): Call<EatenFood>

    @GET("comment/{comment}")
    fun getComment( @Path("comment") id: String): Call<Comment>

}