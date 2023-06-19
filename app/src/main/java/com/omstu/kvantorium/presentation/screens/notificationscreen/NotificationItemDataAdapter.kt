package com.omstu.kvantorium.presentation.screens.notificationscreen

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omstu.kvantorium.databinding.NotificationItemBinding
import com.omstu.kvantorium.domain.datacontracts.model.NotificationDataModel

class NotificationItemDataAdapter(
    private val resources: Resources,
    private val onRemoveButtonClick: (item: NotificationDataModel, index: Int) -> Unit,
) :
    RecyclerView.Adapter<NotificationItemDataAdapter.NotificationItemDataViewHolder>() {

    class NotificationItemDataViewHolder(
        private val binding: NotificationItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(
            item: NotificationDataModel,
            index: Int,
            onRemoveButtonClick: (item: NotificationDataModel, index: Int) -> Unit,
            resources: Resources
        ) {
            binding.apply {
                this.imageView.setImageDrawable(resources.getDrawable(item.icon))
                this.itemCourseName.text = item.title
                this.itemCourseTime.text = item.time

                removeButton.setOnClickListener {
                    onRemoveButtonClick(item, index)
                }
            }
        }
    }

    var data: MutableList<NotificationDataModel> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun removeItem(item: NotificationDataModel) {
        val index = data.indexOf(item)
        data.remove(item)
        notifyItemRemoved(index)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationItemDataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NotificationItemBinding.inflate(inflater, parent, false)
        return NotificationItemDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationItemDataViewHolder, position: Int) {
        val item = data[position]
        holder.bindTo(item, position, onRemoveButtonClick, resources)
    }

    override fun getItemCount(): Int = data.size
}