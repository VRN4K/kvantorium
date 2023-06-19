package com.omstu.kvantorium.domain.datacontracts.model

data class NotificationDataModel(
    val icon: Int,
    val title: String,
    val time: String,
    val isUnread: Boolean
)