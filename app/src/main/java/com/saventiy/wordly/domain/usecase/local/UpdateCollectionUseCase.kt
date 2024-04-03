package com.saventiy.wordly.domain.usecase.local

import com.saventiy.wordly.data.model.dto.Collection
import com.saventiy.wordly.data.repository.CollectionRepository
import javax.inject.Inject

class UpdateCollectionUseCase @Inject constructor(
    private val repository: CollectionRepository
) {

    operator fun invoke(collection: Collection) = repository.updateCollection(collection)
}