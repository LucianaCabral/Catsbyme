package com.lcabral.catsbyme.features.breeds.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lcabral.catsbyme.core.domain.model.model.Breed
import com.lcabral.catsbyme.features.breeds.databinding.HomeItemBreedBinding


internal typealias ItemClicked = (Breed) -> Unit

internal class BreedViewHolder(
    private val binding: HomeItemBreedBinding,
    private val itemClicked: ItemClicked
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindView(breed: Breed) {
        itemView.apply {
            with(binding) {
                originTv.text = breed.origin
                breedTv.text = breed.name
                imageTv.load(
                    "https://cdn2.thecatapi.com/images/" +
                            breed.referenceImageId + ".jpg"
                )
            }
        }
        itemView.setOnClickListener {
            itemClicked(breed)
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClicked: ItemClicked): BreedViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = HomeItemBreedBinding
                .inflate(inflater, parent, false)
            return BreedViewHolder(binding, itemClicked)
        }
    }
}