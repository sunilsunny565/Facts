package com.assignment.facts.datamanager

import android.app.Application
import androidx.lifecycle.LiveData
import com.assignment.facts.database.FactsDatabase
import com.assignment.facts.database.dao.FactsDao
import com.assignment.facts.model.Facts
import com.assignment.facts.networkadapter.api.apimanager.ApiManager
import com.assignment.facts.networkadapter.api.apirequest.ApiRequest
import com.assignment.facts.networkadapter.api.apiresponse.FactsRepo
import com.assignment.facts.networkadapter.retrofit.RetrofitClient
import io.reactivex.Single

class AppDataManager private constructor(
    private val apiRequest: ApiRequest, private val dbManager: FactsDatabase
) : DataManager {

    override fun getFactsList(): LiveData<MutableList<Facts>> = factsDao.getAllFacts()
    override fun insertAllData(facts: List<Facts>) = factsDao.insertAll(facts)
    override fun clearData() = factsDao.clearAllData()

    private val factsDao: FactsDao by lazy { dbManager.provideFactsDao() }

    // ApiRequests
    override fun getFacts(): Single<FactsRepo> = apiRequest.getFacts()

    companion object {
        @Volatile
        private var instance: AppDataManager? = null

        @Synchronized
        fun getDataManager(application: Application): AppDataManager =
            instance ?: synchronized(this) {
                AppDataManager(
                    ApiManager.getApiRequest(RetrofitClient.createApiClient(application)),
                    FactsDatabase.getDatabase(application)
                ).also { instance = it }
            }
    }


}