package com.example.notekar

class NoteModel {
    var id = 0
    var title= ""
    var content= ""


    constructor(id: Int, title: String, content: String) {
        this.id = id
        this.title = title
        this.content = content

    }


    override fun toString(): String {
        return "NoteModel(id=$id, title='$title', content='$content')"
    }
}
