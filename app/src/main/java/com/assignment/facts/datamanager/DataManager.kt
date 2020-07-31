package com.assignment.facts.datamanager

import androidx.lifecycle.LiveData
import com.assignment.facts.model.Facts
import com.assignment.facts.networkadapter.api.apirequest.ApiRequest

interface DataManager : ApiRequest {

    //Database access
    fun getFactsList(): LiveData<MutableList<Facts>>
    fun insertAllData(facts: List<Facts>)
    fun clearData()
}