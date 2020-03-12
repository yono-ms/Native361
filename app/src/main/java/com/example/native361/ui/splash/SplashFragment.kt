/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.native361.R
import com.example.native361.databinding.SplashFragmentBinding
import com.example.native361.ui.BaseFragment

class SplashFragment : BaseFragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private val viewModel: SplashViewModel by lazy {
        ViewModelProvider(this)[SplashViewModel::class.java].also {
            it.appViewModel = appViewModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<SplashFragmentBinding>(
            inflater,
            R.layout.splash_fragment,
            container,
            false
        ).also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logger.debug("onViewCreated savedInstanceState=$savedInstanceState")
        if (savedInstanceState == null) {
            viewModel.start()
        }
    }
}
