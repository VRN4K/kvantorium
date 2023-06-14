package com.omstu.kvantorium.presentation.screens.mainscreen.adapters

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omstu.kvantorium.databinding.NewsItemLayoutBinding
import com.omstu.kvantorium.domain.datacontracts.model.NewsDataModel

class NewsItemDataAdapter(
    private val resources: Resources,
    private val onViewItemClick: (new: NewsDataModel) -> Unit,
) :
    RecyclerView.Adapter<NewsItemDataAdapter.NewsItemDataViewHolder>() {

    class NewsItemDataViewHolder(
        private val binding: NewsItemLayoutBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(
            item: NewsDataModel,
            resources: Resources,
            onViewItemClick: (new: NewsDataModel) -> Unit,
        ) {
            binding.apply {
                this.newsImage.setImageDrawable(resources.getDrawable(item.miniNewsImage))
                this.newsTitle.text = item.newsTitle
                this.newsDescription.text = item.newsDescription
            }

            itemView.setOnClickListener { onViewItemClick(item) }
        }
    }

    var data: List<NewsDataModel> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemDataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemLayoutBinding.inflate(inflater, parent, false)
        return NewsItemDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsItemDataViewHolder, position: Int) {
        val item = data[position]
        holder.bindTo(item, resources, onViewItemClick)
    }

    override fun getItemCount(): Int = data.size
}