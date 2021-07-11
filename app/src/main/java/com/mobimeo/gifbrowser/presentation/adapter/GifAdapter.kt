package com.mobimeo.gifbrowser.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobimeo.gifbrowser.domain.model.Data
import com.mobimeo.gifbrowser.databinding.GifItemBinding
import com.mobimeo.gifbrowser.presentation.utils.loadImage

class GifAdapter(var gifItemList: ArrayList<Data>) :
    RecyclerView.Adapter<GifAdapter.GifViewHolder>() {

    var onItemClick: ((Data) -> Unit)? = null

    fun updateGifs(newGifList: List<Data>) {
        gifItemList.clear()
        gifItemList.addAll(newGifList)
        notifyDataSetChanged()
    }

    fun loadMoreGifs(newGifList: List<Data>){
        gifItemList.addAll(newGifList)
        notifyDataSetChanged()
    }

    class GifViewHolder(private val itemBinding: GifItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(gifItem: Data) {
            itemBinding.gifImageView.loadImage(gifItem.images.preview_gif.url)
            itemBinding.gifNameTv.text = gifItem.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val itemBinding = GifItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GifViewHolder(itemBinding)
    }


    override fun getItemCount(): Int {
        return gifItemList.size
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(gifItemList[position])

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(gifItemList[position])

        }
    }


}