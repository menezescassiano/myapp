package com.cassiano.myapplication.home.view.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cassiano.myapplication.home.model.Recipe
import com.cassiano.myapplication.repository.DataRepository
import kotlinx.coroutines.launch

class MainViewModel(val repository: DataRepository) : ViewModel() {

    lateinit var list: ArrayList<Recipe>
    val responseData: MutableLiveData<Boolean> = MutableLiveData()
    val running = ObservableBoolean(false)
    var showTryAgain = ObservableBoolean(false)

    fun getData() {
        running.set(true)
        showTryAgain.set(false)
        viewModelScope.launch {
            try {
                repository.getRecipes().run {
                    running.set(false)
                    takeIf { this.isSuccessful }?.run {
                        responseData.postValue(true)
                        list = this.body() as ArrayList<Recipe>
                    } ?: showTryAgain.set(true)
                }
            } catch (e: Exception) {
                showTryAgain.set(true)
            }
        }
    }
}
