package com.omstu.kvantorium.presentation.screens.onboardingpage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omstu.kvantorium.databinding.OnboardingItemBinding
import com.omstu.kvantorium.domain.datacontracts.model.OnboardingItem

class OnboardingAdapter(
    private val onSkipButtonClick: () -> Unit,
) :
    RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {
    class OnboardingViewHolder(
        private val binding: OnboardingItemBinding,
        private val onSkipButtonClick: () -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bindTo(
            item: OnboardingItem,
            onSkipButtonClick: () -> Unit,
        ) {
            binding.apply {
                onboardingTitle.text = root.resources.getText(item.title)
                onboardingDescription.text = root.resources.getText(item.description)
                onboardingImg.setImageDrawable(binding.root.context.getDrawable(item.itemImg))
                skipButton.setOnClickListener { onSkipButtonClick() }
            }
        }
    }

    var items = mutableListOf<OnboardingItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OnboardingItemBinding.inflate(inflater, parent, false)
        return OnboardingViewHolder(binding, onSkipButtonClick)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        val item = items[position]
        holder.bindTo(item, onSkipButtonClick)
    }
}
