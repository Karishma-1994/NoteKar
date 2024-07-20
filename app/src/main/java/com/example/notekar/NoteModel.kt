package com.example.notekar

class NoteModel {
    var id = 0
    var title= ""
    var content= ""
    val isChecked = false


    constructor(id: Int, title: String, content: String) {
        this.id = id
        this.title = title
        this.content = content
        this.isChecked

    }


    override fun toString(): String {
        return "NoteModel(id=$id, title='$title', content='$content', isChecked= $isChecked)"
    }
}
