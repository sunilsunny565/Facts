package com.assignment.facts.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.assignment.facts.FactsApplication
import com.assignment.facts.datamanager.DataManager
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity <VM : ViewModel> : AppCompatActivity() {

    protected val dataManager: DataManager by lazy { FactsApplication.getApiClient() }

    protected val viewModel: VM by lazy { provideViewModel() }

    protected val disposables: CompositeDisposable by lazy { CompositeDisposable() }


    override fun onDestroy() {
        if ((disposables.size() > 0) && !disposables.isDisposed) {
            disposables.dispose()
            disposables.clear()
        }
        super.onDestroy()
    }

    abstract fun provideViewModel(): VM

}