package com.project.whattowatch.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.whattowatch.R
import com.project.whattowatch.common.data.ReviewsModel

class ReviewerAdapter (private var itemList: MutableList<ReviewsModel>, internal var ctx: Context) : RecyclerView.Adapter<ReviewerAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_reviewer, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.findViewById(R.id.username_reviewer)
        val comment: TextView = itemView.findViewById(R.id.comment_reviewer)

        @SuppressLint("SetTextI18n")
        fun bind(reviewer: ReviewsModel) {
            username.text = reviewer.author
            comment.text = reviewer.content
        }
    }
}