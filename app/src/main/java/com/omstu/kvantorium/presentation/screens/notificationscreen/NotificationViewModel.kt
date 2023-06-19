package com.omstu.kvantorium.presentation.screens.notificationscreen

import androidx.lifecycle.MutableLiveData
import com.omstu.kvantorium.R
import com.omstu.kvantorium.domain.datacontracts.model.NotificationDataModel
import com.omstu.kvantorium.presentation.base.BaseViewModel

class NotificationViewModel : BaseViewModel() {
    val deletedNotification = MutableLiveData<NotificationDataModel>()

    private val notificationItemsList = mutableListOf(
        NotificationDataModel(
            R.drawable.badges,
            "Изменение в расписании на 14 августа",
            "Сегодня в 14:30",
            true
        ),
        NotificationDataModel(
            R.drawable.badges2,
            "Отмена занятия 12 августа",
            "Сегодня в 11:00",
            true
        ),
        NotificationDataModel(
            R.drawable.badges11,
            "Новый преподаватель!",
            "Вчера в 10:00",
            false
        ),
        NotificationDataModel(
            R.drawable.badges3,
            "Новое расписание на неделю",
            "Вчера в 10:00",
            false
        ),
        NotificationDataModel(
            R.drawable.badges3,
            "Отмена занятия июня",
            "3 июня",
            false
        ),
    )

    val notificationItems = MutableLiveData<List<NotificationDataModel>>()

    init {
        notificationItems.postValue(notificationItemsList)
    }

    fun onRemoveButtonClick(item: NotificationDataModel) {
        notificationItemsList.remove(item)
        deletedNotification.postValue(item)
        if (notificationItemsList.isEmpty()) notificationItems.postValue(null)
    }
}