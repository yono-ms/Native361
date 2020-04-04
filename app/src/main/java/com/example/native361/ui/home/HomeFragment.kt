/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.native361.R
import com.example.native361.constant.ExtraKey
import com.example.native361.constant.RequestCode
import com.example.native361.databinding.HomeFragmentBinding
import com.example.native361.repository.database.model.Repo
import com.example.native361.ui.BaseFragment
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java].also {
            it.appViewModel = appViewModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<HomeFragmentBinding>(
            inflater,
            R.layout.home_fragment,
            container,
            false
        ).also {
            // この行は果たして必要なのだろうか
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logger.debug("onViewCreated savedInstanceState=$savedInstanceState")
        recyclerViewRepos.layoutManager = LinearLayoutManager(context)
        viewModel.repos.observe(viewLifecycleOwner, Observer {
            logger.debug("observe it=$it")
            recyclerViewRepos.adapter = ReposAdapter(it)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            RequestCode.SINGLE_CHOICE.rawValue -> {
                logger.debug("resultCode=$resultCode")
                data?.let {
                    val choice = it.getStringExtra(ExtraKey.SINGLE_CHOICE.rawValue)
                    logger.debug("choice=$choice")
                    viewModel.login.value = choice
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    class ReposAdapter(private val items: List<Repo>) :
        RecyclerView.Adapter<ReposAdapter.ViewHolder>() {
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textView1: TextView = itemView.findViewById(android.R.id.text1)
            val textView2: TextView = itemView.findViewById(android.R.id.text2)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_2, parent, false)
            return ViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return items.count()
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            items[position].let {
                holder.textView1.text = it.name
                holder.textView2.text = DateFormat.format("yyyy/MM/dd HH:mm:ss", it.updated_at)
            }
        }
    }
}
