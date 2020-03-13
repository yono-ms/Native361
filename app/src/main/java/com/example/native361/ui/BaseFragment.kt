/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class BaseFragment : Fragment() {
    val logger: Logger by lazy {
        LoggerFactory.getLogger(javaClass.simpleName)
    }

    protected val appViewModel: AppViewModel by lazy {
        ViewModelProvider(activity!!)[AppViewModel::class.java].apply {
            dialogMessage.observe(viewLifecycleOwner, Observer {
                it?.let {
                    logger.info(it.toString())
                    val dialog = AlertDialogFragment.newInstance(it, this@BaseFragment)
                    dialog.show(activity!!.supportFragmentManager, "alert")
                    dialogMessage.value = null
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        logger.info("onActivityResult requestCode=$requestCode resultCode=$resultCode")
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logger.info("onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.info("onCreate savedInstanceState=$savedInstanceState")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logger.info("onActivityCreated savedInstanceState=$savedInstanceState")
    }

    override fun onStart() {
        super.onStart()
        logger.info("onStart")
    }

    override fun onResume() {
        super.onResume()
        logger.info("onResume")
    }

    override fun onPause() {
        super.onPause()
        logger.info("onPause")
    }

    override fun onStop() {
        super.onStop()
        logger.info("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.info("onDestroy")
    }
}