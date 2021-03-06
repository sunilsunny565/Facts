package com.assignment.facts.networkadapter.api.apirequest

import com.assignment.facts.networkadapter.api.apiresponse.FactsRepo
import com.assignment.facts.networkadapter.apiconstants.ApiProvider
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {
    @GET(ApiProvider.ApiFacts)
    fun getFacts(): Single<FactsRepo>
}