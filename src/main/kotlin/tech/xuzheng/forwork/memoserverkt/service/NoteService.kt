package tech.xuzheng.forwork.memoserverkt.service

import arrow.core.Either
import tech.xuzheng.forwork.memoserverkt.domain.Note
import tech.xuzheng.forwork.memoserverkt.service.failure.NoteCreateFailure
import tech.xuzheng.forwork.memoserverkt.service.failure.NoteDeleteFailure
import tech.xuzheng.forwork.memoserverkt.service.failure.NoteUpdateFailure

interface NoteService {
    fun findNotesByKey(key: String): List<Note>

    fun createNote(note: Note): Either<NoteCreateFailure, Note>

    fun updateNote(note: Note): Either<NoteUpdateFailure, Note>

    fun deleteNoteByKey(key: String): Either<NoteDeleteFailure, Note>
}