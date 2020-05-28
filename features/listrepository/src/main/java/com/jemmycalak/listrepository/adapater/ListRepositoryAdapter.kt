package com.jemmycalak.listrepository.adapater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jemmycalak.listrepository.R
import com.jemmycalak.listrepository.databinding.ItemRepositoryBinding
import com.jemmycalak.model.Item

class ListRepositoryAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    var data = arrayListOf<Item>()
    val VIEW_TYPE_ITEM = 0
    val VIEW_TYPE_LOADING = 1
    var IS_AVAILABLE_PAGE = false

    override fun getItemViewType(position: Int): Int {
        return if (IS_AVAILABLE_PAGE) {
            if (position == data.size - 1) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
        } else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val binding :ItemRepositoryBinding = DataBindingUtil.inflate(inflater, R.layout.item_repository, parent, false)
                RepositoryViewHolder(binding)
            }
            else -> {
                LoaderHolder(
                    inflater.inflate(
                        R.layout.item_loader,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int  = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is RepositoryViewHolder) holder.bind(data[position], position)
    }

    inner class RepositoryViewHolder(val binding: ItemRepositoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Item, position: Int) {
                binding.m = data
        }
    }

    inner class LoaderHolder(v: View) : RecyclerView.ViewHolder(v)

    fun addItem(mdata:ArrayList<Item>, isLoadMore:Boolean){
        if(!isLoadMore)data.clear()
        data.addAll(mdata)
        notifyDataSetChanged()
        IS_AVAILABLE_PAGE = false
    }

}