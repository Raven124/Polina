package com.example.gif_app.DataBase

import android.app.*
import androidx.room.Room

class DataBaseApplication : Application() {
    private var database: GifDataBase? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, GifDataBase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    fun getDatabase(): GifDataBase? {
        return GifDataBase.Companion.getDatabase(applicationContext)
    }

    companion object {
        var instance: DataBaseApplication? = null
    }
}