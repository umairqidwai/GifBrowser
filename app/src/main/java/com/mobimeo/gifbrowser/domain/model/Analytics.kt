package com.mobimeo.gifbrowser.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Analytics(
    val onclick: Onclick,
    val onload: Onload,
    val onsent: Onsent
): Parcelable