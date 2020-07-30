package com.assignment.facts.datamanager

import android.app.Application
import com.assignment.facts.networkadapter.api.apimanager.ApiManager
import com.assignment.facts.networkadapter.api.apirequest.ApiRequest
import com.assignment.facts.networkadapter.api.apiresponse.FactsRepo
import com.assignment.facts.networkadapter.retrofit.RetrofitClient
import io.reactivex.Single

class AppDataManager private constructor(
    private val apiRequest: ApiRequest
) : DataManager {

    // ApiRequests
    override fun getFacts(): Single<FactsRepo> = apiRequest.getFacts()

    companion object {
        @Volatile
        private var instance: AppDataManager? = null

        @Synchronized
        fun getDataManager(application: Application): AppDataManager =
            instance ?: synchronized(this) {
                AppDataManager(
                    ApiManager.getApiRequest(RetrofitClient.createApiClient(application))
                ).also { instance = it }
            }
    }


}