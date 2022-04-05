package ru.gozerov.mvideo_ex.di

import dagger.Binds
import dagger.Module
import ru.gozerov.data.repository.cards.CardRepositoryImplementation
import ru.gozerov.domain.repository.CardRepository

@Module
interface AbstractBindsModule {

    @Binds
    @Suppress("unused")
    fun provideCardRepository(cardRepositoryImpl: CardRepositoryImplementation): CardRepository

}