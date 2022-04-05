package ru.gozerov.data.repository.cards

import android.content.Context
import javax.inject.Inject

class CardService @Inject constructor (
    private val context: Context,
) {

    fun getString(key: Int): String {
        return context.getString(key)
    }

}