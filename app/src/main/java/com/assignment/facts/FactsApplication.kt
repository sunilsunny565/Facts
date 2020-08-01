package com.assignment.facts

import android.app.Application
import com.assignment.facts.datamanager.AppDataManager
import com.assignment.facts.datamanager.DataManager

class FactsApplication : Application() {
    private val dataManager: DataManager by lazy { AppDataManager.getDataManager(this) }

    companion object {
        private lateinit var instance: FactsApplication
        fun getApiClient(): DataManager = instance.dataManager
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}