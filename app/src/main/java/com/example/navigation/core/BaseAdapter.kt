package com.example.navigation.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : ViewDataBinding, V>(@LayoutRes val layout: Int) : RecyclerView.Adapter<BaseAdapter.BaseHolder<T, V>>() {

    var items: ArrayList<V>? = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var layoutInflater: LayoutInflater
    var binding: T? = null

    override fun onCreateViewHolder(parent: ViewGroup, container: Int): BaseHolder<T, V> {
        layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, layout, parent, false)
        return BaseHolder(binding!!, this)
    }

    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(holder: BaseHolder<T, V>, position: Int) {
        holder.bind(items?.get(position), this)

    }

    class BaseHolder<T : ViewDataBinding, V>(val binding: T, adapter: BaseAdapter<T, V>) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { adapter.clickListener(adapter.items?.get(adapterPosition), adapterPosition, binding) }
        }

        fun bind(item: V?, adapter: BaseAdapter<T, V>) {
            adapter.bindView(binding, item, adapterPosition)
        }
    }

    abstract fun bindView(binding: T, item: V?, adapterPosition: Int)

    open fun clickListener(item: V?, position: Int, binding: T) {

    }
}