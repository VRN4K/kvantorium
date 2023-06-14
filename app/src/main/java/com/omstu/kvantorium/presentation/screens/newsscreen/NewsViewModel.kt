package com.omstu.kvantorium.presentation.screens.newsscreen

import androidx.lifecycle.MutableLiveData
import com.omstu.kvantorium.domain.datacontracts.model.NewsDataModel
import com.omstu.kvantorium.presentation.base.BaseViewModel

class NewsViewModel(private val news: NewsDataModel) :
    BaseViewModel() {
    val currentNews = MutableLiveData<NewsDataModel>()

    init {
        setNewsData()
    }

    fun setNewsData() {
        currentNews.postValue(news)
    }
}