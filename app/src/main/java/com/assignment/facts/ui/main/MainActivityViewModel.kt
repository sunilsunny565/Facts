package com.assignment.facts.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.assignment.facts.datamanager.DataManager
import com.assignment.facts.extensions.rx.autoDispose
import com.assignment.facts.model.Facts
import com.assignment.facts.networkadapter.api.apirequest.NetworkRequestState
import com.assignment.facts.ui.base.BaseViewModel
import com.assignment.facts.utility.io
import kotlinx.coroutines.launch

class MainActivityViewModel(private val dataManager: DataManager) : BaseViewModel() {
    companion object {

        @Synchronized
        fun getInstance(dataManager: DataManager): MainActivityViewModel = synchronized(this) {
            MainActivityViewModel(dataManager)
        }
    }

    var factsList: LiveData<MutableList<Facts>> =
        dataManager.getFactsList()

    var title: String = ""

    fun getFacts(): LiveData<MutableList<Facts>> = factsList

    fun getAppBarTitle() = title

    fun getDataStream(): LiveData<NetworkRequestState> = loaderObservable

    //Method for fetching the data from Api
    fun getFactDetails(context: Context) {
        if (checkNetworkAvailability(context)) {
            dataManager.getFacts().doOnSubscribe {
                loaderObservable.value =
                    NetworkRequestState.LoadingData
            }.subscribe({
                try {
                    viewModelScope.launch {
                        io {
                            val data = it.getFactsData()
                            if (data.isNotEmpty())
                                data[0].mainTitle = it.getTitle()
                            dataManager.clearData()
                            val filteredData =
                                data.filter { it.filterData() }
                            dataManager.insertAllData(filteredData)
                            title = it.getTitle()
                        }
                        loaderObservable.value = NetworkRequestState.SuccessResponse(it)
                    }

                } catch (e: Exception) {
//                    println("$TAG ${e.message}")
                }
            }, {
                loaderObservable.value =
                    NetworkRequestState.ErrorResponse
            }).autoDispose(disposables)
        }
    }
}