package tech.xuzheng.forwork.memoserverkt.api

import org.springframework.web.bind.annotation.*
import tech.xuzheng.forwork.memoserverkt.api.response.Response
import tech.xuzheng.forwork.memoserverkt.api.response.failure
import tech.xuzheng.forwork.memoserverkt.api.response.success
import tech.xuzheng.forwork.memoserverkt.domain.Note
import tech.xuzheng.forwork.memoserverkt.service.NoteService

@RestController
@RequestMapping("/api/note")
class NoteController(
    private val noteService: NoteService
) : API {
    @PostMapping
    fun createNote(@RequestBody note: Note): Response<Note> =
        noteService.createNote(note).fold(
            { failure(it.message) },
            { success { it } }
        )

    @GetMapping
    fun findNotesByKey(@RequestParam key: String): Response<List<Note>> = success {
        noteService.findNotesByKey(key)
    }

    @DeleteMapping
    fun deleteNoteByKey(@RequestParam key: String): Response<Note> =
        noteService.deleteNoteByKey(key).fold(
            { failure(it.message) },
            { success { it } }
        )
}