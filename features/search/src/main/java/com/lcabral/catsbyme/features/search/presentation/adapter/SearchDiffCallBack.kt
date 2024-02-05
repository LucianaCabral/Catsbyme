package com.lcabral.catsbyme.features.search.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.lcabral.catsbyme.features.search.domain.model.Search

internal class SearchDiffCallBack : DiffUtil.ItemCallback<Search>() {
    override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
        return oldItem.id == newItem.id
    }
}
