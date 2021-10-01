package com.android.paging.activities.landing.posts

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.android.paging.R
import com.android.paging.pojo.Posts

class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: AppCompatTextView = itemView.findViewById(R.id.title)
    private val body: AppCompatTextView = itemView.findViewById(R.id.body)

    fun set(item: Posts) {
        title.text = item.title
        body.text = item.body
    }
}