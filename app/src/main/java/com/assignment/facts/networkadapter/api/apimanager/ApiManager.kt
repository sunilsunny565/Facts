package com.assignment.facts.networkadapter.api.apimanager

import com.assignment.facts.extensions.rx.subscribeAndObserveWithDelaySubscription
import com.assignment.facts.networkadapter.api.apirequest.ApiInterface
import com.assignment.facts.networkadapter.api.apirequest.ApiRequest
import com.assignment.facts.networkadapter.api.apiresponse.FactsRepo
import io.reactivex.Single

class ApiManager private constructor(private val apiClient: ApiInterface) : ApiRequest {

    companion object {
        @Volatile
        private var instance: ApiManager? = null

        @Synchronized
        fun getApiRequest(apiClient: ApiInterface): ApiManager =
            instance ?: synchronized(this) { ApiManager(apiClient).also { instance = it } }
    }

    override fun getFacts(): Single<FactsRepo> =
        apiClient.getFacts().subscribeAndObserveWithDelaySubscription()
}