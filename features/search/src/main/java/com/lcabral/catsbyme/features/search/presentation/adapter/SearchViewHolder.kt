package com.lcabral.catsbyme.features.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lcabral.catsbyme.features.breeds.databinding.ItemSearchBinding
import com.lcabral.catsbyme.features.search.domain.model.Search

internal typealias SearchItemClicked = (Search) -> Unit

internal class SearchViewHolder(
    private val binding: ItemSearchBinding,
    private val itemClicked: SearchItemClicked
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(search: Search) {
        itemView.apply {
            with(binding) {
                searchTv.text = search.name
                imageBreed.load("https://cdn2.thecatapi.com/images/"  + search.referenceImageId + ".jpg")
            }

            itemView.setOnClickListener {
                itemClicked(search)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClicked: SearchItemClicked): SearchViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSearchBinding
                .inflate(inflater, parent, false)
            return SearchViewHolder(binding, itemClicked)
        }
    }
}

