package com.assignment.facts.custombindings

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.assignment.facts.model.Facts
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

object CustomBindings {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindRecyclerViewAdapter(imageView: ImageView, fact: Facts) {
        if (!fact.imageUrl.isNullOrEmpty() && !fact.isBadImage) {
            Glide.with(imageView)
                .load(fact.imageUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        p0: GlideException?,
                        p1: Any?,
                        p2: Target<Drawable>?,
                        p3: Boolean
                    ): Boolean {
                        imageView.visibility = View.GONE
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
                        imageView.visibility = View.VISIBLE
                        return false
                    }
                })
                .into(imageView)
        } else {
            imageView.visibility = View.GONE
        }
    }
}