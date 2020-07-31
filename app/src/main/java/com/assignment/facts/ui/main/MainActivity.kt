package com.assignment.facts.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.assignment.facts.R
import com.assignment.facts.extensions.getViewModel
import com.assignment.facts.extensions.rx.autoDispose
import com.assignment.facts.networkadapter.api.apirequest.NetworkRequestState
import com.assignment.facts.ui.base.BaseActivity
import com.assignment.facts.utility.LOAD_ELEMENTS_WITH_DELAY
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity<MainActivityViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            initRecyclerView()

            Observable.timer(LOAD_ELEMENTS_WITH_DELAY, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribe {
                    getObservableDataStream()
                    viewModel.getFactDetails(this)
                }.autoDispose(disposables)
        } catch (e: Exception) {
            println("TAG -- MyData --> ${e.message}")
        }
    }

    private fun initRecyclerView() {

    }

    private fun getObservableDataStream() {
        viewModel.getFacts().removeObservers(this)
        viewModel.getFacts().observe(this, Observer {

        })

        viewModel.getDataStream().removeObservers(this)
        viewModel.getDataStream().observe(this, Observer {
            when(it){
                is NetworkRequestState.NetworkNotAvailable -> TODO()
                is NetworkRequestState.Error -> TODO()
                is NetworkRequestState.ErrorResponse -> TODO()
                is NetworkRequestState.SuccessResponse<*> -> TODO()
            }
        })
    }

    override fun provideViewModel(): MainActivityViewModel =
        getViewModel { MainActivityViewModel.getInstance(dataManager) }


    override fun provideLayoutResource(): Int = R.layout.activity_main
}