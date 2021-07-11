package com.mobimeo.gifbrowser.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DownsizedLarge(
    val height: String,
    val size: String,
    val url: String,
    val width: String
): Parcelable