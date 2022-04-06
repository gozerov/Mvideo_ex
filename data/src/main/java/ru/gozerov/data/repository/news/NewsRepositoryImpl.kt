package ru.gozerov.data.repository.news

import ru.gozerov.data.remote.news.models.NewsApi
import ru.gozerov.data.remote.news.service.NewsService
import ru.gozerov.domain.entity.news.SimpleNews
import ru.gozerov.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor (
    private val newsService: NewsService
): NewsRepository {

    override suspend fun fetchSimpleNews(): List<SimpleNews> {
        return newsService.fetchNews().articles.toListSimpleNews()
    }

    private fun List<NewsApi>.toListSimpleNews() = this.map { newsApi ->
        SimpleNews(
            title = newsApi.title,
            content = newsApi.content
        )
    }

}