package com.mobimeo.gifbrowser.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Onload(
    val url: String
): Parcelable