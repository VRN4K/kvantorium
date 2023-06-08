package com.omstu.kvantorium.presentation.screens.onboardingscreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omstu.kvantorium.databinding.OnboardingButtonItemBinding
import com.omstu.kvantorium.domain.datacontracts.model.OnboardingButtonItem

class OnboardingButtonAdapter(private val onNextButtonClick: (Boolean) -> Unit) :
    RecyclerView.Adapter<OnboardingButtonAdapter.OnboardingButtonViewHolder>() {
    class OnboardingButtonViewHolder(
        private val binding: OnboardingButtonItemBinding,
        private val onNextButtonClick: (Boolean) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bindTo(
            item: OnboardingButtonItem,
            isLastItem: Boolean,
            onNextButtonClick: (Boolean) -> Unit,
        ) {
            binding.nextItemButton.apply {
                text = binding.root.resources.getText(item.title)
                setOnClickListener { onNextButtonClick(isLastItem) }
            }
        }
    }

    var items = mutableListOf<OnboardingButtonItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingButtonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OnboardingButtonItemBinding.inflate(inflater, parent, false)
        return OnboardingButtonViewHolder(binding, onNextButtonClick)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: OnboardingButtonViewHolder, position: Int) {
        val item = items[position]
        holder.bindTo(item, items.last() == item, onNextButtonClick)
    }
}