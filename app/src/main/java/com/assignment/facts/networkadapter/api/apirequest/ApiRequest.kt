package com.assignment.facts.networkadapter.api.apirequest

import com.assignment.facts.networkadapter.api.apiresponse.FactsRepo
import com.google.gson.JsonObject
import io.reactivex.Single

interface ApiRequest {
    fun getFacts(): Single<FactsRepo>
}