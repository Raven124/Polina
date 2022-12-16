package com.example.gif_app.DataBase

import androidx.room.TypeConverter
import com.example.gif_app.Object.Analytics
import com.example.gif_app.Object.Images
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

internal object Converters {
    @JvmStatic
    @TypeConverter
    fun stringFromImages(images: Images?): String {
        val gson = Gson()
        return gson.toJson(images)
    }

    @JvmStatic
    @TypeConverter
    fun imagesFromString(string: String?): Images {
        val images = object : TypeToken<Images?>() {}.type
        return Gson().fromJson(string, images)
    }

    @JvmStatic
    @TypeConverter
    fun stringFromAnalytics(analytics: Analytics?): String {
        val gson = Gson()
        return gson.toJson(analytics)
    }

    @JvmStatic
    @TypeConverter
    fun analyticsFromString(string: String?): Analytics {
        val analytics = object : TypeToken<Analytics?>() {}.type
        return Gson().fromJson(string, analytics)
    }
}