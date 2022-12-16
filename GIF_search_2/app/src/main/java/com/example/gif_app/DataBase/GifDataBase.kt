package com.example.gif_app.DataBase

import com.example.gif_app.Object.Datum
import androidx.room.Database
import androidx.room.TypeConverters
import androidx.room.RoomDatabase
import kotlin.jvm.Volatile
import androidx.room.Room
import android.content.Context

@Database(entities = [Datum::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GifDataBase : RoomDatabase() {
    abstract val gifDao: GifDataBaseDAO

    companion object {
        @Volatile
        private var INSTANCE: GifDataBase? = null
        fun getDatabase(context: Context): GifDataBase? {
            if (INSTANCE == null) {
                synchronized(GifDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            GifDataBase::class.java, "database"
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}