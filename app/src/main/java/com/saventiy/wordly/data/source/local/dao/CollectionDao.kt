package com.saventiy.wordly.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.saventiy.wordly.data.model.entity.CollectionEntity
import com.saventiy.wordly.data.model.entity.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(collection: CollectionEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(collection: CollectionEntity)

    @Query("SELECT * FROM collection_table")
    fun getAll(): Flow<List<CollectionEntity>>

    @Query("SELECT * FROM collection_table WHERE id=:id")
    fun getCollection(id: Int): Flow<CollectionEntity>
}