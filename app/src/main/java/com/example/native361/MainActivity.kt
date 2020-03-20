/*
 * Copyright (c) 2020. No name
 */

package com.example.native361

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.native361.constant.Destination
import com.example.native361.databinding.ActivityMainBinding
import com.example.native361.ui.AppViewModel
import com.example.native361.ui.home.HomeFragment
import com.example.native361.ui.splash.SplashFragment
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MainActivity : AppCompatActivity() {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MainActivity::class.java.simpleName)
    }

    private val appViewModel: AppViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(AppViewModel::class.java).apply {
            logger.info("appViewModel created.")
        }.apply {
            destination.observe(this@MainActivity, Observer {
                it?.let {
                    logger.info(it.toString())
                    transition(it)
                    destination.value = null
                }
            })
        }
    }

    private fun transition(destination: Destination) {
        logger.info("transition destination=$destination")
        val id = R.id.frameLayoutContent
        supportFragmentManager.beginTransaction().let {
            when (destination) {
                Destination.REPLACE_HOME -> it.replace(id, HomeFragment.newInstance()).commit()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.info("onCreate savedInstanceState=$savedInstanceState")
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        // この行は果たして必要なのだろうか
        binding.appViewModel = appViewModel
        binding.lifecycleOwner = this
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayoutContent, SplashFragment.newInstance()).commit()
        }
    }
}
