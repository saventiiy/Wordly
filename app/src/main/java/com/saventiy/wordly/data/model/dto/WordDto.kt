package com.saventiy.wordly.data.model.dto

import com.saventiy.wordly.data.model.entity.WordEntity

data class WordDto(
    val word: String
) {
    fun toCollectionEntity(): WordEntity =
        WordEntity(word = word)
}
