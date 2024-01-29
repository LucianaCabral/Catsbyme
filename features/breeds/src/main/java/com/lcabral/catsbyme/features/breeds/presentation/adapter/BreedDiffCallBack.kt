package com.lcabral.catsbyme.features.breeds.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.lcabral.catsbyme.core.domain.model.model.Breed

internal class BreedDiffCallBack : DiffUtil.ItemCallback<Breed>() {
    override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return  oldItem.id == newItem.id && oldItem.name ==  newItem.name
    }
}