package com.saventiy.wordly.data.repository

import com.saventiy.wordly.data.model.dto.Collection
import com.saventiy.wordly.data.source.local.dao.CollectionDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val collectionDao: CollectionDao
) : CollectionRepository {

    override suspend fun addCollection(collection: Collection) =
        collectionDao.insert(collection.toCollectionEntity())

    override fun updateCollection(collection: Collection) =
        collectionDao.update(collection.toCollectionEntity())

    override fun getAllCollections(): Flow<List<Collection>> =
        collectionDao.getAll().map { it.map { it.toCollection() } }

    override fun getCollection(id: Int): Flow<Collection> =
        collectionDao.getCollection(id).map { it.toCollection() }
}