package com.assignment.facts.extensions.rx

import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

private const val TIME_DURATION_MEDIUM: Long = 500L

fun View.throttleClick(): Observable<Unit> = clicks()
    .throttleLatest(TIME_DURATION_MEDIUM, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
