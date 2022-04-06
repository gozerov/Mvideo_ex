package ru.gozerov.domain.usecase

import ru.gozerov.domain.repository.NewsRepository
import javax.inject.Inject

class GetSimpleNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend fun execute() = newsRepository.fetchSimpleNews()

}