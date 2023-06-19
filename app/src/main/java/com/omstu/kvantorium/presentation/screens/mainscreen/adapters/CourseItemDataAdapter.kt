package com.omstu.kvantorium.presentation.screens.mainscreen.adapters

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omstu.kvantorium.databinding.CourseItemLayoutBinding
import com.omstu.kvantorium.domain.datacontracts.model.ScheduleDataModel

class CourseItemDataAdapter(private val resources: Resources) :
    RecyclerView.Adapter<CourseItemDataAdapter.CourseItemDataViewHolder>() {

    class CourseItemDataViewHolder(
        private val binding: CourseItemLayoutBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(
            item: ScheduleDataModel,
            resources: Resources
        ) {
            binding.apply {
                    itemCourseName.text = item.courseName
                    itemCourseTime.text = item.courseTime
                    itemCourseCabinet.text = item.courseCabinet
                    itemCourseLector.text = item.courseLector
            }
        }
    }

    var data: List<ScheduleDataModel> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseItemDataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CourseItemLayoutBinding.inflate(inflater, parent, false)
        return CourseItemDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseItemDataViewHolder, position: Int) {
        val item = data[position]
        holder.bindTo(item, resources)
    }

    override fun getItemCount(): Int = data.size
}