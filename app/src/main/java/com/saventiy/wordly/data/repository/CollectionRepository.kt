package com.saventiy.wordly.data.repository

import com.saventiy.wordly.data.model.dto.Collection
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    fun addCollection(collection: Collection)
    fun updateCollection(collection: Collection)
    fun getAllCollections(): Flow<List<Collection>>
    fun getCollection(id: Int): Flow<Collection>
}