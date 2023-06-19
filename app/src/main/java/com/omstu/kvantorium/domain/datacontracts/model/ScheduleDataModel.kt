package com.omstu.kvantorium.domain.datacontracts.model

data class ScheduleDataModel(
    val courseName : String,
    val courseTime: String,
    val courseCabinet: String,
    val courseLector: String
)

data class ProfileScheduleDataModel(
    val courseName : String,
    val courseLectors: List<String>,
    val courseIcon: Int
)