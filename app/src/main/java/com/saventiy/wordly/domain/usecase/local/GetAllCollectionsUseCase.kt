package com.saventiy.wordly.domain.usecase.local

import com.saventiy.wordly.data.repository.CollectionRepository
import javax.inject.Inject

class GetAllCollectionsUseCase @Inject constructor(
    private val repository: CollectionRepository
) {

    operator fun invoke() = repository.getAllCollections()
}