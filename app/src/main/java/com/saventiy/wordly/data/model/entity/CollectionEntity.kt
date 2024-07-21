package com.saventiy.wordly.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.saventiy.wordly.data.model.dto.Collection

@Entity(tableName = "collection_table")
data class CollectionEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "collection") @TypeConverters(CollectionConverter::class) val collection: List<String>,
    @ColumnInfo(name = "isActive") val isActive: Boolean
) {
    fun toCollection(): Collection =
        Collection(
            id = this.id,
            name = this.name,
            collection = this.collection,
            isActive = this.isActive
        )
}

class CollectionConverter {

    @TypeConverter
    fun fromCollectionToJSON(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromJSONToCollection(json: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, listType)
    }
}