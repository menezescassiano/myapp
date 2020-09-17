package com.cassiano.myapplication.home.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cassiano.myapplication.BR
import com.cassiano.myapplication.R
import com.cassiano.myapplication.databinding.ActivityMainBinding
import com.cassiano.myapplication.extension.bindingContentView
import com.cassiano.myapplication.extension.hasInternetConnection
import com.cassiano.myapplication.extension.observe
import com.cassiano.myapplication.extension.showToast
import com.cassiano.myapplication.home.adapter.RecipeListAdapter
import com.cassiano.myapplication.home.view.viewmodel.MainViewModel
import com.cassiano.myapplication.internal.Constants.Companion.BUNDLE_RECIPE
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.apply {
            observe(responseData) {
                setRecyclerView()
            }
        }

        setupBinding()

        getData()
    }

    private fun setupBinding() {
        bindingContentView(R.layout.activity_main).apply {
            setVariable(BR.viewModel, viewModel)
            setVariable(BR.onTryAgainClick, View.OnClickListener { onTryAgainClick() })
        } as ActivityMainBinding
    }

    private fun setRecyclerView() {
        val listAdapter = RecipeListAdapter(viewModel.list)
        recyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
        }

        listAdapter.selectedRecipe.observe(this@MainActivity, {
            Intent(this, RecipeDetailActivity::class.java).apply {
                this.putExtra(BUNDLE_RECIPE, it)
                startActivity(this)
            }
        })
    }

    private fun onTryAgainClick() {
        getData()
    }

    private fun getData() {
        viewModel.run {
            when {
                hasInternetConnection() -> getData()
                else -> {
                    showToast(getString(R.string.internet_connection_warning))
                    showTryAgain.set(true)
                }
            }
        }
    }
}
