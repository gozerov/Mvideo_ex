package ru.gozerov.domain.repository

import ru.gozerov.domain.entity.CardRes

interface CardRepository {

    suspend fun getAllCards(cardRes: CardRes): MutableMap<String, List<Any>>

}