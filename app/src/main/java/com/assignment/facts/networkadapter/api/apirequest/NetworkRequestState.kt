package com.assignment.facts.networkadapter.api.apirequest

sealed class NetworkRequestState {
    object NetworkNotAvailable : NetworkRequestState()
    object LoadingData : NetworkRequestState()
    object ErrorResponse : NetworkRequestState()
    class SuccessResponse<T>(val data: T?) : NetworkRequestState()
}