package com.rguzmanc.friends.android.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rguzmanc.friends.android.R
import com.rguzmanc.friends.domain.model.Friend

class FriendAdapter: ListAdapter<Friend, FriendAdapter.FriendViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_friend, parent, false)
        return FriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val item = getItem(position)
        holder.txtFullName.text = "${item.name}  ${item.lastName}"
    }

    class DiffCallback : DiffUtil.ItemCallback<Friend>() {
        override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean {
            return oldItem == newItem
        }
    }

    class FriendViewHolder(private val root: View) : RecyclerView.ViewHolder(root){
        val txtFullName: TextView = root.findViewById(R.id.tv_full_name)
    }
}
