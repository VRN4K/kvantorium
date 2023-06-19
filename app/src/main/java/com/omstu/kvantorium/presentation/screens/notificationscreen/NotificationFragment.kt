package com.omstu.kvantorium.presentation.screens.notificationscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.omstu.kvantorium.databinding.NotificationLayoutBinding
import com.omstu.kvantorium.presentation.base.BaseFragment
import com.omstu.kvantorium.presentation.common.onDestroyNullable
import com.omstu.kvantorium.presentation.screens.profile.ProfileCourseItemDataAdapter

class NotificationFragment : BaseFragment() {
    private var binding by onDestroyNullable<NotificationLayoutBinding>()
    private val viewModel by viewModels<NotificationViewModel>()

    private val notificationItemsAdapter by lazy {
        NotificationItemDataAdapter(
            resources,
            onRemoveButtonClick = { item, index ->
                viewModel.onRemoveButtonClick(item)
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NotificationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigationVisibility(true)
        binding.visibilityToggle.visibleChildId = binding.contentLayout.id
        binding.courseRecycler.adapter = notificationItemsAdapter
        setObservers()
    }

    private fun setObservers() {
        viewModel.apply {
            notificationItems.observe(viewLifecycleOwner) { it ->
                if (it.isNullOrEmpty()) {
                    binding.visibilityToggle.visibleChildId =
                        binding.noContentLayout.id
                } else {
                    notificationItemsAdapter.data = it.toMutableList()
                    binding.count.text = notificationItemsAdapter.data .count { it.isUnread }.toString()
                }
            }

            deletedNotification.observe(viewLifecycleOwner) {
                notificationItemsAdapter.removeItem(it)
                binding.count.text = notificationItemsAdapter.data .count { it.isUnread }.toString()
            }
        }
    }
}