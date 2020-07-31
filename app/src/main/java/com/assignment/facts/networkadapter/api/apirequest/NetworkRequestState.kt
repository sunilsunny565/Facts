package com.assignment.facts.networkadapter.api.apirequest

sealed class NetworkRequestState {
    object NetworkNotAvailable : NetworkRequestState()
    object LoadingData : NetworkRequestState()
    class ErrorResponse(val throwable: Throwable? = null) :
        NetworkRequestState()
    class SuccessResponse<T>(val data: T?) : NetworkRequestState()
}