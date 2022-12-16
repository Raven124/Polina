package com.example.gif_app.Main

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.gif_app.DataBase.GifDataBase
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gif_app.Object.Datum
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.gif_app.R
import com.example.gif_app.RV_Adapter.GifAdapter
import com.example.gif_app.databinding.MainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Main : AppCompatActivity() {

    private lateinit var binding: MainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private var gifDataBase: GifDataBase? = null
    private var gifAdapter = GifAdapter(mutableListOf()) { gif: Datum? ->
        if(gif==null) {
            return@GifAdapter
        }
        if (isLocalDBShown) {
            onDelete(gif)
            Toast.makeText(this, "Удалено из БД", Toast.LENGTH_SHORT).show()
        } else {
            onInsert(gif)
            Toast.makeText(this, "Добавлено в БД", Toast.LENGTH_SHORT).show()
        }

    }
    private var isLocalDBShown = false
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        sharedPreferences = getSharedPreferences("default", 0)

        gifDataBase = GifDataBase.getDatabase(applicationContext)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridLayoutManager = GridLayoutManager(this, resources.getInteger(R.integer.grid_column_count))
        gridLayoutManager.isItemPrefetchEnabled = true
        gridLayoutManager.initialPrefetchItemCount = 50
        binding.rvGifs.layoutManager = gridLayoutManager
        binding.rvGifs.adapter = gifAdapter

        lifecycleScope.launchWhenResumed {
            mainViewModel.currentGifsMutableFlow.collect() { newGifs ->
                if (newGifs != null) {
                    gifAdapter.setNewItems(newGifs)
                    gifAdapter.notifyItemRangeChanged(0, newGifs.size)
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                mainViewModel.progressBarStatusFlow.collect() { progressViewState ->
                    when(progressViewState){
                        MainViewModel.VisibilityState.EMPTY -> {
                        }
                        MainViewModel.VisibilityState.GONE -> {
                            binding.progressBar.visibility = View.GONE
                        }
                        MainViewModel.VisibilityState.VISIBLE -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

        with(binding) {
            btLoadLocalStorage.setOnClickListener {
                isLocalDBShown = true
                hideKeyboard()
                lifecycleScope.launch(Dispatchers.IO){
                    val gifsFromDataBase = gifDataBase?.gifDao!!.loadAll()
                    mainViewModel.currentGifsMutableFlow.emit(gifsFromDataBase)
                }
            }

            btLoadOnline.setOnClickListener {
                isLocalDBShown = false
                hideKeyboard()
                val query = binding.searchInput.text.toString()
                mainViewModel.downloadGifs(query)
            }
        }
    }

    //Добавление гифки
    private fun onInsert(gif: Datum?) {
        gifDataBase?.gifDao?.insertGif(gif)
    }


    //Удаление сохранненой гифки при нажатии
    private fun onDelete(gif: Datum) {
        gifDataBase?.gifDao?.deleteGif(gif)
        val index = gifAdapter.values.indexOf(gif)
        gifAdapter.values.remove(gif)
        index.let {
            gifAdapter.notifyItemRemoved(it)
        }
    }

}

//extension method to hide keyboard
fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}