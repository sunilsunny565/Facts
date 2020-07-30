package com.assignment.facts.extensions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified VM : ViewModel> AppCompatActivity.getViewModel(crossinline factory: () -> VM): VM =
    createViewModel(factory)

inline fun <reified VM : ViewModel> AppCompatActivity.createViewModel(crossinline factory: () -> VM): VM {

    @Suppress("UNCHECKED_CAST")
    val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <VM : ViewModel> create(modelClass: Class<VM>): VM = factory() as VM
    }

    return ViewModelProvider(this, viewModelFactory)[VM::class.java]
}


fun AppCompatActivity.addFragment(
    containerId: Int, fragment: Fragment, fragmentTag: String, addToBackStack: Boolean = true
) = supportFragmentManager.beginTransaction()
    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    .add(containerId, fragment, fragmentTag)
    .addToBackStack(if (addToBackStack) fragmentTag else null)
    .commit()

