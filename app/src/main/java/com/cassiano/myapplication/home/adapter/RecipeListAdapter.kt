package com.cassiano.myapplication.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.cassiano.myapplication.R
import com.cassiano.myapplication.databinding.LayoutRecipeListItemBinding
import com.cassiano.myapplication.home.model.Recipe

class RecipeListAdapter(private val list: ArrayList<Recipe>) : RecyclerView.Adapter<RecipeViewHolder>() {

    val selectedRecipe: MutableLiveData<Recipe> = MutableLiveData()
    lateinit var binding: LayoutRecipeListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false) as LayoutRecipeListItemBinding
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = list[position]

        holder.apply {
            bind(item)
            itemView.setOnClickListener {
                selectedRecipe.value = item
            }
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.layout_recipe_list_item
    }
}
