package com.android.paging.activities.landing.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.android.paging.R
import com.android.paging.pojo.Posts

class PostsAdapter : PagingDataAdapter<Posts, PostsViewHolder>(CharacterComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder = PostsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.posts_rv_item, parent, false))
    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) = getItem(position)?.let { holder.set(it) }
            ?: Unit

}

object CharacterComparator : DiffUtil.ItemCallback<Posts>() {
    override fun areItemsTheSame(oldItem: Posts, newItem: Posts) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Posts, newItem: Posts) = oldItem == newItem
}
