package com.omstu.kvantorium.presentation.screens.profile

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omstu.kvantorium.databinding.CourseItemLayoutBinding
import com.omstu.kvantorium.databinding.ProfileCourseItemBinding
import com.omstu.kvantorium.databinding.ProfileLayoutBinding
import com.omstu.kvantorium.domain.datacontracts.model.ProfileScheduleDataModel
import com.omstu.kvantorium.domain.datacontracts.model.ScheduleDataModel

class ProfileCourseItemDataAdapter(private val resources: Resources) :
    RecyclerView.Adapter<ProfileCourseItemDataAdapter.ProfileCourseItemDataViewHolder>() {

    class ProfileCourseItemDataViewHolder(
        private val binding: ProfileCourseItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(
            item: ProfileScheduleDataModel,
            resources: Resources
        ) {
            binding.apply {
                this.courseTitle.text = item.courseName
                this.courseIcon.setImageDrawable(resources.getDrawable(item.courseIcon))
                this.teacherFirst.text = item.courseLectors[0]
                this.teacherSecond.text = item.courseLectors[1]
            }
        }
    }

    var data: List<ProfileScheduleDataModel> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileCourseItemDataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProfileCourseItemBinding.inflate(inflater, parent, false)
        return ProfileCourseItemDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileCourseItemDataViewHolder, position: Int) {
        val item = data[position]
        holder.bindTo(item, resources)
    }

    override fun getItemCount(): Int = data.size
}