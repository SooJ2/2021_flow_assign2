package com.example.assign2

import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {

    //인코딩 해주기
    @FormUrlEncoded
    //보낼 곳
    @POST("/app_login/")
    fun requestLogin(
        //input 정보
        @Field("userid") userid:String,
        @Field("userpw") userpw:String
    ) : Call<User> //output 정의



    @POST("admin/auth/user/add")
    fun registerUser(
        @Field("username") username:String,
        @Field("password") password:String,
        @Field("password_confirmation") password_confirmation:String
    )
//    @POST("userinfo")
//    fun registerUserInfo(
//        @Field("user_id") user_id:String,
//        @Field("password") password:String
//    )

    @GET("feed")
    fun requestFeedsByUserId(
        @Query("uploader") uploader:String
    ) : Call<List<Feed>>

    @GET("eatenfood")
    fun requestEatsByFeedId(
        @Query("eater") eater:String
    ) : Call<List<EatenFood>>

    @GET("comment")
    fun requestCommentByFeedId(
        @Query("comment_writer") comment_writer:String
    ) : Call<List<Comment>>


    @GET("user/{user}")
    fun getUser( @Path("user") id: String): Call<User>

    @GET("follow/{follow}")
    fun getFollow( @Path("follow") id: String): Call<Follow>

    @GET("feed/{feed}")
    fun getFeed( @Path("feed") id: String): Call<Feed>

    @GET("food/{food}")
    fun getFood( @Path("food") id: String): Call<Food>

    @GET("eatenfood/{eatenfood}")
    fun getEatenFood( @Path("eatenfood") id: String): Call<EatenFood>

    @GET("comment/{comment}")
    fun getComment( @Path("comment") id: String): Call<Comment>

}