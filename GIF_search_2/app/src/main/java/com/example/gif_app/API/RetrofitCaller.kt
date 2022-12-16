package com.example.gif_app.API

import retrofit2.http.GET
import com.example.gif_app.Object.API_Response
import retrofit2.Call
import retrofit2.http.Query

interface RetrofitCaller {
    @GET("gifs/trending?")
    fun getRecent(
        @Query("api_key") given_api_key: String?,
        @Query("limit") given_limit: String?,
        @Query("rating") given_rating: String?
    ): Call<API_Response?>?

    @GET("gifs/search?")
    fun getSearchPhotos(
        @Query("api_key") given_api_key: String?,
        @Query("q") keyWord: String?,
        @Query("limit") given_limit: String?,
        @Query("offset") given_offset: String?,
        @Query("rating") given_rating: String?
    ): Call<API_Response?>?
}