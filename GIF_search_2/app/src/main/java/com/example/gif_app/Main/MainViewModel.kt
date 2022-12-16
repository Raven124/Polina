package com.example.gif_app.Main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gif_app.Object.Datum
import com.example.gif_app.API.RetrofitCaller
import com.example.gif_app.API.RetrofitItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    var retrofitCaller = RetrofitItem.retrofit?.create(RetrofitCaller::class.java)
    private val API_KEY = "lkrJDzDTVXJtbt8lPVphAMKC05nGDLji"
    private var limit = "50"
    private var rating = "pg-13"
    private var offset = "0"

    val currentGifsMutableFlow = MutableSharedFlow<List<Datum>?>(replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST, extraBufferCapacity = 1)
    val progressBarStatusFlow = MutableSharedFlow<VisibilityState>(replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST, extraBufferCapacity = 1)

    fun downloadGifs(query: String){
        viewModelScope.launch(Dispatchers.IO) {
            progressBarStatusFlow.emit(VisibilityState.VISIBLE)
            val response = retrofitCaller?.getSearchPhotos(API_KEY, query, limit, offset, rating)?.execute()
            currentGifsMutableFlow.emit(response?.body()?.data)
            progressBarStatusFlow.emit(VisibilityState.GONE)
        }
    }

    sealed class VisibilityState {
        object GONE : VisibilityState()
        object VISIBLE : VisibilityState()
        object EMPTY : VisibilityState()
    }
}