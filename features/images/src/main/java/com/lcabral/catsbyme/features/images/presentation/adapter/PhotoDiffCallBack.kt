package com.lcabral.catsbyme.features.images.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.lcabral.catsbyme.features.images.domain.model.Photo

internal class PhotoDiffCallBack : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return  oldItem.url == newItem.url
    }
}
