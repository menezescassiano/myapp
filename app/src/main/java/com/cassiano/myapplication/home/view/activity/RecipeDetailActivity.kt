package com.cassiano.myapplication.home.view.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cassiano.myapplication.BR
import com.cassiano.myapplication.R
import com.cassiano.myapplication.extension.bindingContentView
import com.cassiano.myapplication.extension.getSharedPreferences
import com.cassiano.myapplication.extension.showToast
import com.cassiano.myapplication.home.model.Recipe
import com.cassiano.myapplication.home.view.viewmodel.RecipeDetailViewModel
import com.cassiano.myapplication.internal.Constants.Companion.BUNDLE_RECIPE
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeDetailActivity : AppCompatActivity() {

    private val viewModel: RecipeDetailViewModel by viewModel()
    lateinit var recipe: Recipe
    private val sharedPreferences by lazy { getSharedPreferences(getString(R.string.recipe_shared_preferences)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleExtras()

        setupFavIcon()

        bindContentView()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun bindContentView() {
        recipe.run {
            bindingContentView(R.layout.activity_recipe_detail).apply {
                setVariable(BR.viewModel, viewModel)
                setVariable(BR.imageURL, image)
                setVariable(BR.name, name)
                setVariable(BR.headline, headline)
                setVariable(BR.description, description)
                setVariable(BR.onFavCLick, View.OnClickListener { onFavCLick() })
            }
        }
    }

    private fun handleExtras() {
        intent.extras?.let {
            recipe = it.getParcelable(BUNDLE_RECIPE) as Recipe
        } ?: finish()
    }

    private fun setupFavIcon() {
        viewModel.run {
            isFavorite.set(sharedPreferences.getBoolean(recipe.id, false))
            setFavIcon()
        }
    }

    private fun onFavCLick() {
        viewModel.run {
            isFavorite.set(!isFavorite.get())
            setFavIcon()
            when {
                isFavorite.get() -> {
                    saveRecipe(recipe.id, isFavorite.get(), getString(R.string.recipe_favored_alert))
                }
                else -> {
                    saveRecipe(recipe.id, isFavorite.get(), getString(R.string.recipe_unfavored_alert))
                }
            }
        }
    }

    private fun saveRecipe(id: String, save: Boolean, message: String) {
        with(sharedPreferences.edit()) {
            putBoolean(id, save)
            commit()
        }

        showToast(message)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
