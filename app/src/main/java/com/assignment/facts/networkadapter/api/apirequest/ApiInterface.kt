package com.assignment.facts.networkadapter.api.apirequest

import com.assignment.facts.networkadapter.api.apiresponse.FactsRepo
import com.assignment.facts.networkadapter.apiconstants.ApiProvider
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST(ApiProvider.ApiFacts)
    fun getFacts(): Single<FactsRepo>
}