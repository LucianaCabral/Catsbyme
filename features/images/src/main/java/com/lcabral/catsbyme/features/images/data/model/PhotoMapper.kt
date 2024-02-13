package com.lcabral.catsbyme.features.images.data.model

import com.lcabral.catsbyme.features.images.domain.model.Photo
import com.lcabral.catsbyme.libs.arch.extensions.orEmpty

internal fun List<PhotoResponse>.toPhotos()=
     this.map { it.toPhoto() }


private fun PhotoResponse?.toPhoto(): Photo {
    return Photo(
        url = this?.url.orEmpty(),
    )
}
