package com.lcabral.catsbyme.features.images.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lcabral.catsbyme.features.images.domain.model.Photo

internal class PhotoAdapter : ListAdapter<Photo, PhotoViewHolder>(PhotoDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position).also(holder::bindView)
    }
}
