package mx.edu.delasalle.notes.data.model

fun List<NoteEntity>.toNoteList(): NoteList {
    val list = mutableListOf<Note>()
    this.forEach() { note ->
        list.add(note.toNote())
    }
    return  NoteList(list)
}

fun NoteEntity.toNote(): Note {
    return Note(
        id = this.id.toString(),
        title = this.title,
        content = this.content,
        image = this.image
    )
}

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = this.id.toInt(),
        title = this.title,
        content = this.content,
        image = this.image
    )
}