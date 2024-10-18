package tech.xuzheng.forwork.memoserverkt.service

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import org.springframework.stereotype.Service
import tech.xuzheng.forwork.memoserverkt.domain.Note
import tech.xuzheng.forwork.memoserverkt.repository.NoteRepository
import tech.xuzheng.forwork.memoserverkt.service.failure.NoteCreateFailure
import tech.xuzheng.forwork.memoserverkt.service.failure.NoteDeleteFailure
import tech.xuzheng.forwork.memoserverkt.service.failure.NoteUpdateFailure

@Service
class DefaultNoteService(
    private val repository: NoteRepository
) : NoteService {

    override fun findNotesByKey(key: String): List<Note> =
        repository.findAllByKeyContainingIgnoreCase(key)

    override fun createNote(note: Note): Either<NoteCreateFailure, Note> = either {
        findNotesByKey(note.key).run {
            ensure(isEmpty()) { NoteCreateFailure.KeyExist }
        }
        repository.save(note)
    }

    override fun updateNote(note: Note): Either<NoteUpdateFailure, Note> = either {
        findNotesByKey(note.key).apply {
            ensure(isNotEmpty()) { NoteUpdateFailure.KeyNotExist }
        }.first().let {
            repository.save(it)
        }
    }

    override fun deleteNoteByKey(key: String): Either<NoteDeleteFailure, Note> = either {
        findNotesByKey(key).apply {
            ensure(isNotEmpty()) { NoteDeleteFailure.KeyNotExist }
        }.first().let {
            repository.delete(it)
            it
        }
    }
}