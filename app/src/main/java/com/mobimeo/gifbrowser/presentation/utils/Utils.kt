package com.mobimeo.gifbrowser.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mobimeo.gifbrowser.R

fun ImageView.loadImage(uri: String?) {
    val options = RequestOptions()
        .error(R.drawable.error)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .placeholder(R.drawable.gif)
        .into(this)
}