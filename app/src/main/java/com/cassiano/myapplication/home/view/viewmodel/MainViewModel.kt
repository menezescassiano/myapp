package com.cassiano.myapplication.home.view.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cassiano.myapplication.home.model.Recipe
import com.cassiano.myapplication.repository.DataRepository
import com.cassiano.myapplication.internal.RequestStatus
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
            repository.getRecipes().run {
                running.set(false)
                responseData.postValue(true)
                list = this.data as ArrayList<Recipe>
                changeState(this.status)
            }
        }
    }

    private fun changeState(state: RequestStatus) {
        when (state) {
            RequestStatus.ERROR -> showTryAgain.set(true)
            else -> showTryAgain.set(false)
        }
    }
}
