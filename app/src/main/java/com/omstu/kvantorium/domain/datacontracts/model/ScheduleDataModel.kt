package com.omstu.kvantorium.domain.datacontracts.model

data class ScheduleDataModel(
    val courseName : String,
    val courseTime: String,
    val courseCabinet: String,
    val courseLector: String
)