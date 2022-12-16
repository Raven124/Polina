package com.example.gif_app.RV_Adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.gif_app.Object.Datum
import com.example.gif_app.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

class GifAdapter(var values: MutableList<Datum?>, private val function: (Datum?) -> Unit) :
    RecyclerView.Adapter<GifAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url: String? = values[position]
            ?.images
            ?.fixedHeightSmall
            ?.url
        Glide
            .with(holder.image.context)
            .asGif()
            .load(url)
            .into(holder.image)
        holder.image.setOnClickListener {
            function.invoke(values[position])
        }
        holder.itemView.tag = values[position]
    }

    override fun getItemCount(): Int {
        return if (values.isEmpty()){
            0
        } else {
            values.size
        }
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView

        init {
            image = view.findViewById(R.id.gif_view)
        }
    }

    fun setNewItems(newGifs: List<Datum?>){
        val prevSize = values.size
        val newSize = newGifs.size
        if (newSize == 0) {
            notifyItemRangeRemoved(0, prevSize-1)
        }
        if (prevSize < newSize){
            notifyItemRangeChanged(0, prevSize-1)
            notifyItemRangeInserted(prevSize, newSize-1)
        }
        if(prevSize == newSize){
            notifyItemRangeChanged(0, newSize-1)
        }
        if(prevSize > newSize){
            notifyItemRangeChanged(0, newSize-1)
            notifyItemRangeRemoved(newSize, prevSize-1)
        }

        values = newGifs as MutableList<Datum?>
    }
}