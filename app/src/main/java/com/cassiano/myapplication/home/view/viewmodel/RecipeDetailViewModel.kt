package com.cassiano.myapplication.home.view.viewmodel

import android.graphics.drawable.Drawable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.cassiano.myapplication.R
import com.cassiano.myapplication.utils.ResourceManager

class RecipeDetailViewModel(private val resourceManager: ResourceManager) : ViewModel() {

    val favIcon = ObservableField<Drawable>()
    var isFavorite = ObservableBoolean(false)

    fun setFavIcon() {
        resourceManager.run {
            when {
                isFavorite.get() -> favIcon.set(getDrawable(R.drawable.ic_fav))
                else -> favIcon.set(getDrawable(R.drawable.ic_unfav))
            }
        }
    }
}
