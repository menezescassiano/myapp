package com.cassiano.myapplication.home.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.cassiano.myapplication.BR
import com.cassiano.myapplication.home.model.Recipe

class RecipeViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Recipe) {

        this.binding.apply {
            val context = this.root.context
            item.run {
                setVariable(BR.title, name)
                setVariable(BR.imageURL, thumb)
            }
            executePendingBindings()
        }
    }
}
