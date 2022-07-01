package com.example.advanced_kotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.advanced_kotlin.databinding.ItemTodoBinding

class UserAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<UserAdapter.UserVH>() {

    inner class UserVH(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserVH(ItemTodoBinding.inflate(LayoutInflater.from(parent.context)))


    override fun onBindViewHolder(holder: UserVH, position: Int) {
        holder.binding.apply {
            val user = userList[position]
            firstName.text = user.firstName
            lastName.text = user.lastName
            age.text = user.age.toString()
            id.text = user.id.toString()
            holder.itemView.setOnClickListener {
                listener.onClick(user)
            }
            delete.setOnClickListener{
                listener.onDeleteClick(user)
            }
        }
    }

    private val diffUtilCallBack = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
    }

    private val differ = AsyncListDiffer(this, diffUtilCallBack)

    var userList: List<User>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun getItemCount() = userList.size

    interface OnItemClickListener {
        fun onClick(user: User)
        fun onDeleteClick(user: User)
    }

}