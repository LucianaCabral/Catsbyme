package com.lcabral.catsbyme.features.images.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lcabral.catsbyme.features.images.databinding.PhotoItemBinding
import com.lcabral.catsbyme.features.images.domain.model.Photo

internal class PhotoViewHolder(
    private val binding: PhotoItemBinding,

    ) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(photo: Photo) {
        itemView.apply {
            with(binding) {

                photoImg.load(photo.url)
                }
        }
    }

    companion object {
        fun create(parent: ViewGroup): PhotoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PhotoItemBinding
                .inflate(inflater, parent, false)
            return PhotoViewHolder(binding)
        }
    }
}
