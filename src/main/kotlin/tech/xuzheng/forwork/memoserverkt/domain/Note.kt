package tech.xuzheng.forwork.memoserverkt.domain

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "notes")
data class Note(
    val id: String? = null,
    val key: String,
    val value: String
)