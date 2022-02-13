package ru.gozerov.domain.entity

import java.io.Serializable

data class AdaptersData(
    val data: Map<String, List<Any>>
): Serializable

class AdaptersDataConverter {

    companion object {
        fun convert(data: Map<String, List<Any>>): AdaptersData = AdaptersData(data)
    }

}