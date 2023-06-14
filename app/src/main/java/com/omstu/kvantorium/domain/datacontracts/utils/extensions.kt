package com.omstu.kvantorium.domain.datacontracts.utils

fun <T> List<T>.indexOfOrNull(element: T): Int? {
    val index = indexOf(element)
    return if (index == -1) null else index
}

fun String.splitTranslation(): List<String> {
    val separatorComma = ", "
    val separatorSemicolon = "; "
    return this.split(separatorComma, separatorSemicolon)
}
