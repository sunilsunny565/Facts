package com.assignment.facts.utility

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.assignment.facts.R

object BaseUtility {

    fun showAlertMessage(
        context: Context, @StringRes title: Int, @StringRes message: Int,
        @StringRes positiveTitle: Int = R.string.okay
    ): AlertDialog =
        AlertDialog.Builder(context, R.style.Theme_MaterialComponents_Light_Dialog).setTitle(title)
            .setMessage(message).setPositiveButton(positiveTitle) { dialog, _ -> dialog.dismiss() }
            .show()

}