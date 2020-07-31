package com.assignment.facts.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.assignment.facts.R
import com.assignment.facts.extensions.getTargetIntent
import com.assignment.facts.ui.main.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        disposable = Observable.timer(2000L, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { navigateToNextActivity() }
    }

    private fun navigateToNextActivity() {
        val targetIntent = getTargetIntent(MainActivity::class.java)
        startActivity(targetIntent)
        finish()
    }
}