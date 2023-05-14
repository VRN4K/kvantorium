package com.omstu.kvantorium.domain.datacontracts.model

data class OnboardingItem(
    val title: Int,
    val description: Int,
    val itemImg: Int
)

data class OnboardingButtonItem(
    val title: Int,
    val isLastPage: Boolean
)