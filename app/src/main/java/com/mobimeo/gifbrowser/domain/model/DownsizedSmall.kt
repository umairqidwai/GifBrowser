package com.mobimeo.gifbrowser.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DownsizedSmall(
    val height: String,
    val mp4: String,
    val mp4_size: String,
    val width: String
): Parcelable