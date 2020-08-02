package com.assignment.facts.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.facts.R
import com.assignment.facts.model.Facts
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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

    //Method for setting the data in list
    fun setData(dataList: MutableList<Facts>) {
        this.dataList.apply {
            this.clear()
            this.addAll(dataList)
        }
        notifyDataSetChanged()
    }

    //View Holder
    inner class FactsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.tvTitle
        private val tvDescription: TextView = itemView.tvDescription
        private val tvImageFact: ImageView = itemView.imgFact
        fun bindData(fact: Facts) {
            tvTitle.text = fact.itemTitle
            tvDescription.text = fact.itemDescription
            if (!fact.imageUrl.isNullOrEmpty() && !fact.isBadImage) {
                Glide.with(tvImageFact)
                    .load(fact.imageUrl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            p0: GlideException?,
                            p1: Any?,
                            p2: Target<Drawable>?,
                            p3: Boolean
                        ): Boolean {
                            tvImageFact.visibility = View.GONE
                            fact.isBadImage = true
                            return false
                        }

                        override fun onResourceReady(
                            p0: Drawable?,
                            p1: Any?,
                            p2: Target<Drawable>?,
                            p3: com.bumptech.glide.load.DataSource?,
                            p4: Boolean
                        ): Boolean {
                            tvImageFact.visibility = View.VISIBLE
                            return false
                        }
                    })
                    .into(tvImageFact)
            } else {
                tvImageFact.visibility = View.GONE
            }
        }

    }
}