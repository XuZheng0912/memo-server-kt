package tech.xuzheng.forwork.memoserverkt.repository

import org.springframework.data.mongodb.repository.MongoRepository
import tech.xuzheng.forwork.memoserverkt.domain.Note

interface NoteRepository : MongoRepository<Note, String> {
    fun getNoteByKey(key: String): Note?

    fun deleteByKey(key: String)

    fun findAllByKeyContainingIgnoreCase(key: String): List<Note>
}