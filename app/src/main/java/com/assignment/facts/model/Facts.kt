package com.assignment.facts.model

import com.assignment.facts.networkadapter.apiconstants.ApiConstants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Facts {

    @SerializedName(ApiConstants.Title)
    @Expose
    var itemTitle: String? = ""
        get() = field ?: ""
        set(value) {
            field = value ?: ""
        }

    @SerializedName(ApiConstants.Description)
    @Expose
    var itemDescription: String? = ""
        get() = field ?: ""
        set(value) {
            field = value ?: ""
        }

    @SerializedName(ApiConstants.ImageUrl)
    @Expose
    var itemUrl: String? = ""
        get() = field ?: ""
        set(value) {
            field = value ?: ""
        }
}