package com.saventiy.wordly.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saventiy.wordly.data.model.entity.CollectionConverter
import com.saventiy.wordly.data.model.entity.CollectionEntity
import com.saventiy.wordly.data.model.entity.WordEntity
import com.saventiy.wordly.data.source.local.dao.CollectionDao

@Database(
    entities = [
        WordEntity::class,
        CollectionEntity::class
    ],
//    BuildConfig.DATABASE_VERSION
    version = 6,
    exportSchema = false
)
@TypeConverters(CollectionConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun collectionDao(): CollectionDao
}
