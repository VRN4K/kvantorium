package com.omstu.kvantorium.presentation.screens.mainscreen

import android.icu.util.Calendar
import androidx.lifecycle.MutableLiveData
import com.omstu.kvantorium.R
import com.omstu.kvantorium.domain.datacontracts.model.NewsDataModel
import com.omstu.kvantorium.domain.datacontracts.model.ScheduleDataModel
import com.omstu.kvantorium.domain.datacontracts.utils.DateTimeMapper
import com.omstu.kvantorium.presentation.base.BaseViewModel
import java.util.*

class MainScreenViewModel : BaseViewModel() {

    private val calendar = Calendar.getInstance()
    val currentDate = MutableLiveData<String>()

    private val scheduleItemsList = listOf(
        ScheduleDataModel(
            "Космоквантум",
            "10:00 - 11:40",
            "Кабинет 12",
            "Понятовская А.Г."
        ),
        ScheduleDataModel(
            "Космоквантум",
            "15:00 - 16:40",
            "Кабинет 12",
            "Казанцева Д.В."
        )
    )

    private val newsItemsList = listOf(
        NewsDataModel(
            R.drawable.rectangle22,
            R.drawable.rectangle2025,
            "Аэрокосмический фестиваль",
            "Аэрокосмический фестиваль - 2023 проводится с целью популяризации ракетно-космической отрасли...",
            "БУ ДО «Омская областная станция юных техников» проводит Аэрокосмический фестиваль!\n" +
                    "\n" +
                    "Аэрокосмический фестиваль - 2023 проводится с целью популяризации ракетно-космической отрасли и авиационной промышленности, профессиональной ориентации обучающихся для дальнейшей деятельности в интересах научно-технического развития страны.\n" +
                    "\n" +
                    "В программу Аэрокосмического фестиваля включены мероприятия в области аэрокосмических технологий, геоинформатики и проектирования летательных аппаратов.\n" +
                    "\n" +
                    "Участниками Аэрокосмического фестиваля являются обучающиеся БУ ДО «Омская областная СЮТ», ученики ракетно-космических, аэрокосмических, физико-математических, инженерно-технологических, инженерных классов образовательных школ г.Омска"
        ),
        NewsDataModel(
            R.drawable.rectangle2021,
            R.drawable.rectangle2024,
            "Итоги конкурса технологии в искуссвтве",
            "Участники создавали 3D модель виртуальной реальности в виде...",
            "Участники создавали 3D модель виртуальной реальности в виде достопримечательности, символа своего региона с описательным документом созданного проекта\n" +
                    "\n" +
                    "Номинация «История одного здания»\n" +
                    "В средней возрастной группе\n" +
                    "Жазыбаева Айгерим «Екатеринославский дом культуры»\n" +
                    "В старшей возрастной группе \n" +
                    "Швеглер Вероника «Тарские ворота»\n" +
                    "\n" +
                    "Номинация «Мой город.История»\n" +
                    "В средней возрастной группе \n" +
                    "Калеева Ксения «Родные просторы и Иртыш»\n" +
                    "\n" +
                    "Благодарим всех участников конкурса. \n" +
                    "В течение сегодняшнего дня сертификаты \n" +
                    "и дипломы будут направлены на электронную почту!"
        )
    )
    val coursesItems = MutableLiveData<List<ScheduleDataModel>>()
    val newsItems = MutableLiveData<List<NewsDataModel>>()

    init {
        coursesItems.postValue(scheduleItemsList)
        newsItems.postValue(newsItemsList)
        setStartDate()
    }

    private fun setStartDate() {

        currentDate.postValue(
            DateTimeMapper.format(
                calendar.time,
                DateTimeMapper.DATE_PATTERN_WYEAR
            ).replaceFirst(" ",", ")
        )
    }
}