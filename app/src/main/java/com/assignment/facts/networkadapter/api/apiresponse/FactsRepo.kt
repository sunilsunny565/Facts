package com.assignment.facts.networkadapter.api.apiresponse

import com.assignment.facts.model.Facts
import com.assignment.facts.networkadapter.apiconstants.ApiConstants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FactsRepo {
    @SerializedName(ApiConstants.Title)
    @Expose
    private var mainTitle: String = ""

    @SerializedName(ApiConstants.Rows)
    @Expose
    private val facts: List<Facts> = mutableListOf()

    fun getFactsData(): List<Facts> = facts

    fun getTitle(): String = mainTitle

}