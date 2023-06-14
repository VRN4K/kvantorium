package com.omstu.kvantorium.domain.datacontracts.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsDataModel(
    val miniNewsImage: Int,
    val largeNewsImage: Int,
    val newsTitle: String,
    val newsDescription: String,
    val newsText: String
) : Parcelable