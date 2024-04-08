package com.saventiy.wordly.domain.usecase.local

import com.saventiy.wordly.data.model.dto.Collection
import com.saventiy.wordly.data.repository.CollectionRepository
import javax.inject.Inject

class AddCollectionUseCase @Inject constructor(
    private val repository: CollectionRepository
) {
    suspend operator fun invoke(collection: Collection) = repository.addCollection(collection)
}