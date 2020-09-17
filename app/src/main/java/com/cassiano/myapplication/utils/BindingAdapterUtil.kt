package com.cassiano.myapplication.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cassiano.myapplication.R

object BindingAdapterUtil {

    @JvmStatic
    @BindingAdapter(value = [ "imageUrl", "isPicture" ], requireAll = false)
    fun setImageUrl(imgView: ImageView, imgUrl: String?, isPicture: Boolean = false) {
        val placeHolder = when {
            isPicture -> R.drawable.recipe
            else -> R.drawable.ic_photo_black
        }

        imgUrl?.let {
            Glide.with(imgView.context).load(imgUrl)
                .apply(RequestOptions().placeholder(placeHolder))
                .into(imgView)
        }
    }
}
