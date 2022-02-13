package ru.gozerov.domain.entity

import java.io.Serializable

data class MagContent(
    val id: Long,
    val magType: String,
    val shortDescription: String,
    val magBackground: String
): Serializable