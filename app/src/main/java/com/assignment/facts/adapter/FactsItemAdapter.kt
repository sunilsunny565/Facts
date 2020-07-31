package com.assignment.facts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.facts.R
import com.assignment.facts.model.Facts
import kotlinx.android.synthetic.main.list_item_facts.view.*

class FactsItemAdapter : RecyclerView.Adapter<FactsItemAdapter.FactsItemViewHolder>() {
    private val dataList: MutableList<Facts> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsItemViewHolder {
        val customView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_facts, parent, false)
        return FactsItemViewHolder(customView)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: FactsItemViewHolder, position: Int) {
        holder.bindData(dataList[position])
    }

    fun setData(dataList: MutableList<Facts>) {
        this.dataList.apply {
            this.clear()
            this.addAll(dataList)
        }
        notifyDataSetChanged()
    }

    inner class FactsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.tvTitle
        private val tvDescription: TextView = itemView.tvDescription
        private val tvImageFact: ImageView = itemView.imgFact
        fun bindData(fact: Facts) {
            tvTitle.text = fact.itemTitle
            tvDescription.text = fact.itemDescription
        }

    }
}