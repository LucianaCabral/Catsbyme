package com.lcabral.catsbyme.features.breeds.presentation.extensions

import androidx.fragment.app.Fragment
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.lcabral.catsbyme.core.domain.model.model.Breed
import com.lcabral.catsbyme.features.breeds.R
import com.lcabral.catsbyme.features.breeds.databinding.CustomBottomSheetBinding

fun Fragment.showDetails(breed: Breed) {
    val customDialogBuilder =
        BottomSheetDialog(
            requireContext(),
            com.google.android.material.R.style.Base_Theme_Material3_Dark_BottomSheetDialog
        )
    val bottomSheetBinding: CustomBottomSheetBinding = CustomBottomSheetBinding.inflate(
        layoutInflater, null, false
    )
    customDialogBuilder.setContentView(bottomSheetBinding.root)
    with(bottomSheetBinding) {
        imgCat.load( "https://cdn2.thecatapi.com/images/" +
                breed.referenceImageId + ".jpg")
        breedNameDetailTv.text = breed.name
        chipIntelligence.text = String.format(resources.getString(
            R.string.details_chip_intelligence), breed.intelligence)
        origin.text = String.format(resources.getString(
            R.string.details_origin_label), breed.origin)
        descriptionTv.text = breed.description
        chipChildFriendly.text = String.format(resources.getString(
            R.string.details_chip_child_friendly), breed.childFriendly)
        chipSocialNeeds.text = String.format(resources.getString(
            R.string.details_chip_social_needs), breed.socialNeeds)
        chipHealthyIssues.text = String.format(resources.getString(
            R.string.details_chip_healthy_issues), breed.healthIssues)
        chipVocalisation.text = String.format(resources.getString(
            R.string.details_chip_vocatisation), breed.vocalisation)
    }
    customDialogBuilder.show()
}
