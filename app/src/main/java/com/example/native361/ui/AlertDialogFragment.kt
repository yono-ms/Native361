/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.native361.Constants
import com.example.native361.R
import kotlinx.android.synthetic.main.dialog_error.view.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AlertDialogFragment : DialogFragment() {

    companion object {
        private const val KEY_TITLE = "TITLE"
        private const val KEY_MESSAGE = "MESSAGE"
        private const val KEY_EXCEPTION = "EXCEPTION"

        private val logger: Logger =
            LoggerFactory.getLogger(AlertDialogFragment::class.java.simpleName)

        fun newInstance(
            dialogMessage: DialogMessage,
            target: Fragment? = null
        ): AlertDialogFragment {
            return AlertDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TITLE, dialogMessage.title)
                    putString(KEY_MESSAGE, dialogMessage.message)
                    dialogMessage.exception?.let {
                        putString(KEY_EXCEPTION, it.toString())
                    }
                }
                target?.let { setTargetFragment(target, Constants.ALERT) }
            }
        }
    }

    val appViewModel: AppViewModel by lazy {
        ViewModelProvider(activity!!, ViewModelProvider.NewInstanceFactory()).get(
            AppViewModel::class.java
        )
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { activity ->
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_error, null)
            view.textViewMessage.text = arguments?.getString(KEY_MESSAGE)
            arguments?.getString(KEY_EXCEPTION)?.let {
                view.textViewException.text = it
            }
            val builder = AlertDialog.Builder(activity)
            builder.setView(view)
            builder.setTitle(arguments?.getString(KEY_TITLE))
                .setPositiveButton(R.string.button_ok) { dialog, which ->
                    logger.info("dialog=$dialog which=$which")
                    targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, null)
                    dialog.dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("activity is null.")
    }
}