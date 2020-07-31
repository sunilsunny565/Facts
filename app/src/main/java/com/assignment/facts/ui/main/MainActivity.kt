package com.assignment.facts.ui.main

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.facts.R
import com.assignment.facts.adapter.FactsItemAdapter
import com.assignment.facts.extensions.getViewModel
import com.assignment.facts.extensions.rx.autoDispose
import com.assignment.facts.networkadapter.api.apirequest.NetworkRequestState
import com.assignment.facts.ui.base.BaseActivity
import com.assignment.facts.utility.LOAD_ELEMENTS_WITH_DELAY
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity<MainActivityViewModel>() {


    private val factsItemAdapter by lazy { FactsItemAdapter() }
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
        val rvPendingList = (rvFacts as RecyclerView)
        rvPendingList.apply {
            this.adapter = factsItemAdapter
            this.layoutManager =LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL ,false)
        }
    }

    private fun getObservableDataStream() {
        viewModel.getFacts().removeObservers(this)
        viewModel.getFacts().observe(this, Observer {
            factsItemAdapter.setData(it)
            factsItemAdapter.notifyDataSetChanged()
        })

        viewModel.getDataStream().removeObservers(this)
        viewModel.getDataStream().observe(this, Observer {
//            when (it) {
//                is NetworkRequestState.NetworkNotAvailable -> TODO()
//                is NetworkRequestState.Error -> TODO()
//                is NetworkRequestState.ErrorResponse -> TODO()
//                is NetworkRequestState.SuccessResponse<*> -> TODO()
//            }
        })
    }

    override fun provideViewModel(): MainActivityViewModel =
        getViewModel { MainActivityViewModel.getInstance(dataManager) }


    override fun provideLayoutResource(): Int = R.layout.activity_main
}