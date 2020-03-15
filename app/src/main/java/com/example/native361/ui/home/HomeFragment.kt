/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui.home

import android.os.Bundle
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

    class ReposAdapter(private val items: List<Repo>) :
        RecyclerView.Adapter<ReposAdapter.ViewHolder>() {
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textView: TextView = itemView.findViewById<TextView>(android.R.id.text1)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return ViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return items.count()
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            items[position].let {
                holder.textView.text = it.name
            }
        }
    }
}
