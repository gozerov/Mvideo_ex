package ru.gozerov.domain.usecase

import ru.gozerov.domain.entity.CardRes
import ru.gozerov.domain.repository.CardRepository
import javax.inject.Inject

class GetAdaptersDataUseCase @Inject constructor(
    private val cardRepository: CardRepository
) {

    suspend fun execute(cardRes: CardRes): MutableMap<String, List<Any>> {
        return cardRepository.getAllCards(cardRes)
    }
}