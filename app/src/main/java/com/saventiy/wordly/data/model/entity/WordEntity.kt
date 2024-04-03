package com.saventiy.wordly.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.saventiy.wordly.data.model.dto.WordDto

@Entity(tableName = "word_table")
data class WordEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "word") val word: String,
) {
    fun toCollection(): WordDto =
        WordDto(word = this.word)
}