package com.omstu.kvantorium.presentation.screens.newsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omstu.kvantorium.domain.datacontracts.model.NewsDataModel

class NewsViewModelFactory(private val news: NewsDataModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(news) as T
    }
}