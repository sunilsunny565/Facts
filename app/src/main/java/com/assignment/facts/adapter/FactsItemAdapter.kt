package com.assignment.facts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.facts.databinding.ListItemFactsBinding
import com.assignment.facts.model.Facts


class FactsItemAdapter : RecyclerView.Adapter<FactsItemAdapter.FactsItemViewHolder>() {
    private val dataList: MutableList<Facts> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsItemViewHolder {
        val binding = ListItemFactsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FactsItemViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: FactsItemViewHolder, position: Int) {
        val fact = dataList[position]
        holder.binding.fact = fact
        holder.binding.executePendingBindings()
    }

    //Method for setting the data in list
    fun setData(dataList: MutableList<Facts>) {
        this.dataList.apply {
            this.clear()
            this.addAll(dataList)
        }
        notifyDataSetChanged()
    }

    //View Holder
    inner class FactsItemViewHolder(val binding: ListItemFactsBinding) :
        RecyclerView.ViewHolder(binding.root)
}