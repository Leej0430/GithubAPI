package com.example.githubapi.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//user_url : "https://api.github.com/search/users?q=language:Kotlin"


interface Network{
    @GET("search/users?")
    fun getUserInfo(@Query("q")input: String):retrofit2.Call<UserList>
    @GET("users/{name}")
    fun getDetail(
    @Path("name") input:String):retrofit2.Call<UserDetail>

    companion object{
        fun initRetrofit():Network{
            return Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory
                            .create())
                    .build()
                    .create(Network::class.java)
        }
    }
}


