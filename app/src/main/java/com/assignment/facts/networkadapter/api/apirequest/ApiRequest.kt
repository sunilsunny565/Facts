package com.assignment.facts.networkadapter.api.apirequest

import com.assignment.facts.networkadapter.api.apiresponse.FactsRepo
import io.reactivex.Single

interface ApiRequest {
    fun getFacts(): Single<FactsRepo>
}