package ru.gozerov.domain.repository

import ru.gozerov.domain.entity.news.SimpleNews

interface NewsRepository {

    suspend fun fetchSimpleNews(): List<SimpleNews>

}