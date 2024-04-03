package com.saventiy.wordly.domain.usecase.local

import com.saventiy.wordly.data.repository.CollectionRepository
import javax.inject.Inject

class GetCollectionUseCase @Inject constructor(
    private val repository: CollectionRepository
) {

    operator fun invoke(id: Int) = repository.getCollection(id)
}