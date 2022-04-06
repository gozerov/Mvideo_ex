package ru.gozerov.mvideo_ex.di

import dagger.Binds
import dagger.Module
import ru.gozerov.data.repository.cards.CardRepositoryImplementation
import ru.gozerov.data.repository.news.NewsRepositoryImpl
import ru.gozerov.domain.repository.CardRepository
import ru.gozerov.domain.repository.NewsRepository
import javax.inject.Singleton

@Module
interface AbstractBindsModule {

    @Binds
    @Suppress("unused")
    fun provideCardRepository(cardRepositoryImpl: CardRepositoryImplementation): CardRepository

    @Binds
    @Singleton
    @Suppress("unused")
    fun provideNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}