package com.lcabral.catsbyme.features.breeds.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.lcabral.catsbyme.core.domain.model.model.Breed

internal class BreedAdapter (
    private val itemClicked: ItemClicked
) : PagingDataAdapter<Breed, BreedViewHolder>(BreedDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        return BreedViewHolder.create(parent, itemClicked = itemClicked)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        getItem(position)?.also(holder::bindView)
    }
}