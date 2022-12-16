package com.example.gif_app.DataBase

import com.example.gif_app.Object.Datum
import androidx.room.*

@Dao
interface GifDataBaseDAO {
    @Query("SELECT * FROM Datum")
    fun loadAll(): List<Datum>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGif(gif: Datum?)

    @Delete
    fun deleteGif(gif: Datum?)
}