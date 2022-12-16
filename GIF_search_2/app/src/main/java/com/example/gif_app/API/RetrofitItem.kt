package com.example.gif_app.API

import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitItem {
    private val limit = 50

    companion object {
        private const val BASE_URL = "https://api.giphy.com/v1/"
        @JvmStatic
        var retrofit: Retrofit? = null
            get() {
                if (field == null) {
                    val gson = GsonBuilder()
                        .setLenient()
                        .create()
                    field = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
                    return field
                }
                return field
            }
            private set
        private val retrofitCaller: RetrofitCaller? = null
    }
}