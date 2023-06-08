package com.omstu.kvantorium.domain.datacontracts.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateTimeMapper {
    const val DATE_PATTERN = "dd MMMM yyyy"


    @SuppressLint("SimpleDateFormat")
    private val dateFormatter = SimpleDateFormat("", Locale.getDefault())

    fun parseToDatetime(date: String, from: String, to: String): Date {
        dateFormatter.applyPattern(from)
        val dateFromResponse = dateFormatter.parse(date)
        dateFormatter.applyPattern(to)
        return requireNotNull(
            dateFormatter.parse(
                dateFormatter.format(
                    requireNotNull(
                        dateFromResponse
                    )
                )
            )
        )
    }

    fun toDate(date: Date, to: String): Date {
        return requireNotNull(with(dateFormatter) {
            applyPattern(to)
            parse(format(date))
        })
    }

    fun parse(date: String, time: String, from: String, to: String): Date {
        var datetime = String.format("$date $time")
        var date: Date
        return requireNotNull(
            with(dateFormatter) {
                applyPattern(from)
                date = requireNotNull(parse(datetime))
                applyPattern(to)
                datetime = format(date)
                parse(datetime)
            }
        )
    }

    fun parse(date: String, from: String, to: String): Date {
        dateFormatter.applyPattern(from)
        val newDate = dateFormatter.parse(date)
        dateFormatter.applyPattern(to)
        return requireNotNull(
            dateFormatter.parse(
                requireNotNull(
                    dateFormatter.format(
                        newDate
                    )
                )
            )
        )
    }

    fun format(date: Date, to: String): String {
        dateFormatter.applyPattern(to)
        val dateString = dateFormatter.format(date).split(" ").toMutableList()
        dateString[1] = dateString[1].replaceFirstChar { it.titlecase(Locale.getDefault()) }

        return dateString.joinToString(" ")
    }
}