package com.itis.android.myweatherapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.itis.android.myweatherapp.R
import com.bumptech.glide.request.RequestOptions


fun loadPicture(imageView: ImageView, url: String) {
    val options = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.ic_empty_image)
            .error(R.drawable.ic_empty_image)

    Glide.with(imageView.context)
            .load(url)
            .apply(options)
            .into(imageView)
}