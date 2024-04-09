package com.saventiy.wordly.domain.usecase.local

import com.saventiy.wordly.data.model.dto.Collection
import com.saventiy.wordly.data.repository.CollectionRepository
import javax.inject.Inject

class DeleteCollectionUseCase @Inject constructor(
    private val repository: CollectionRepository
) {

    //what operator do???????????
    suspend operator fun invoke(collection: Collection) = repository.deleteCollection(collection)
}