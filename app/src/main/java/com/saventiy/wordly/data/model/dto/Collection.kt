package com.saventiy.wordly.data.model.dto

import com.saventiy.wordly.data.model.entity.CollectionEntity

data class Collection(
    var name: String,
    var collection: List<String>,
    var isActive: Boolean
) {
    fun toCollectionEntity(): CollectionEntity =
        CollectionEntity(
            name = this.name,
            collection = this.collection,
            isActive = this.isActive
        )
}