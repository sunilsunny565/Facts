package com.assignment.facts.extensions.rx

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

private const val DELAY_SUBSCRIPTION_MILLIS: Long = 700L

fun Disposable.autoDispose(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)

// Single Observable Pattern
fun <T> Single<T>.subscribeAndObserveWithDelaySubscription(): Single<T> =
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .delaySubscription(DELAY_SUBSCRIPTION_MILLIS, TimeUnit.MILLISECONDS)
