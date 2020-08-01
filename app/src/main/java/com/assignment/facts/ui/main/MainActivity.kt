package com.assignment.facts.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.facts.R
import com.assignment.facts.adapter.FactsItemAdapter
import com.assignment.facts.extensions.getViewModel
import com.assignment.facts.extensions.rx.autoDispose
import com.assignment.facts.networkadapter.api.apirequest.NetworkRequestState
import com.assignment.facts.networkadapter.api.apiresponse.FactsRepo
import com.assignment.facts.ui.base.BaseActivity
import com.assignment.facts.utility.BaseUtility
import com.assignment.facts.utility.LOAD_ELEMENTS_WITH_DELAY
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity<MainActivityViewModel>() {


    private val factsItemAdapter by lazy { FactsItemAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            initRecyclerView()
            initRefreshLayout()
            Observable.timer(LOAD_ELEMENTS_WITH_DELAY, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribe {
                    getObservableDataStream()
                    viewModel.getFactDetails(this)
                }.autoDispose(disposables)
        } catch (e: Exception) {
            println("TAG -- MyData --> ${e.message}")
        }
    }

    private fun initRefreshLayout() {
        swpRefresh.setOnRefreshListener {
            viewModel.getFactDetails(this)
        }
    }

    private fun initRecyclerView() {
        val rvPendingList = (rvFacts as RecyclerView)
        rvPendingList.apply {
            this.adapter = factsItemAdapter
            this.layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun getObservableDataStream() {
        viewModel.getFacts().removeObservers(this)
        viewModel.getFacts().observe(this, Observer {
            factsItemAdapter.setData(it)
            if (viewModel.getAppBarTitle().isEmpty() && it.isNotEmpty()) {
                toolBar.title = it[0].mainTitle
            } else {
                toolBar.title = viewModel.getAppBarTitle()
            }
        })

        viewModel.getDataStream().removeObservers(this)
        viewModel.getDataStream().observe(this, Observer {
            when (it) {
                is NetworkRequestState.LoadingData -> {
                    tvNoItem.visibility = View.GONE
                    if (!swpRefresh.isRefreshing) {
                        progressBar.visibility = View.VISIBLE
                    }
                }
                is NetworkRequestState.NetworkNotAvailable -> {
                    swpRefresh.isRefreshing = false
                    progressBar.visibility = View.GONE
                    if (factsItemAdapter.itemCount == 0) {
                        tvNoItem.visibility = View.VISIBLE
                    }
                    BaseUtility.showAlertMessage(
                        this, R.string.error, R.string.api_connection_error
                    )
                }
                is NetworkRequestState.ErrorResponse -> {
                    swpRefresh.isRefreshing = false
                    progressBar.visibility = View.GONE
                    if (factsItemAdapter.itemCount == 0) {
                        tvNoItem.visibility = View.VISIBLE
                    }
                    BaseUtility.showAlertMessage(
                        this, R.string.error, R.string.api_connection_error
                    )
                }
                is NetworkRequestState.SuccessResponse<*> -> {
                    val data = it.data as FactsRepo
                    tvNoItem.visibility = View.GONE
                    if (data.getFactsData().isEmpty() && factsItemAdapter.itemCount == 0) {
                        tvNoItem.visibility = View.VISIBLE
                    }
                    progressBar.visibility = View.GONE
                    swpRefresh.isRefreshing = false
                }
            }
        })
    }

    override fun provideViewModel(): MainActivityViewModel =
        getViewModel { MainActivityViewModel.getInstance(dataManager) }


    override fun provideLayoutResource(): Int = R.layout.activity_main
}