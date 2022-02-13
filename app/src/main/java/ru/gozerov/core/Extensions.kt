package ru.gozerov.core

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment


fun <T> Fragment.sendResult(key: String, result: T) {
    this.parentFragmentManager.setFragmentResult("RESULT_KEY", bundleOf(key to result))
}