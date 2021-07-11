package com.mobimeo.gifbrowser.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Preview(
    val height: String,
    val mp4: String,
    val mp4_size: String,
    val width: String
): Parcelable