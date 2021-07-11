package com.mobimeo.gifbrowser.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FixedHeightSmall(
    val height: String,
    val mp4: String,
    val mp4_size: String,
    val size: String,
    val url: String,
    val webp: String,
    val webp_size: String,
    val width: String
): Parcelable