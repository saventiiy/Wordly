package com.saventiy.wordly.data.repository

import com.saventiy.wordly.data.model.dto.Collection
import com.saventiy.wordly.data.source.local.dao.CollectionDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val collectionDao: CollectionDao
) : CollectionRepository {

    override fun addCollection(collection: Collection) =
        collectionDao.insert(collection.toCollectionEntity())

    override fun updateCollection(collection: Collection) =
        collectionDao.update(collection.toCollectionEntity())

    override fun getAllCollections(): Flow<List<Collection>> =
        collectionDao.getAll().map { it.map { it.toCollection() } }

    override fun getCollection(id: Int): Flow<Collection> =
        collectionDao.getCollection(id).map { it.toCollection() }
}

//class CollectionRepositoryImpl @Inject constructor(
//    private val collectionDao: CollectionDao
//) : CollectionRepository {
//    override fun addWord(word: String) = collectionDao.insert(com.saventiy.wordly.data.model.entity.WordEntity(
//        word = word
//    ))
//
//    override fun updateWord(word: String) = collectionDao.update(com.saventiy.wordly.data.model.entity.WordEntity(word = word))
//
//    override fun getAllWords(): Flow<List<com.saventiy.wordly.data.model.dto.WordDto>> =
//        collectionDao.getAll().map { it.map { it.toCollection() } }
//
//    override fun getWord(id: Int): Flow<com.saventiy.wordly.data.model.dto.WordDto> =
//        collectionDao.getWord(id).map { it.toCollection() }
//}