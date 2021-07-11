package com.mobimeo.gifbrowser.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GifSearch(
    val data: List<Data>,
    val meta: Meta,
    val pagination: Pagination
): Parcelable