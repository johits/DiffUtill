package com.example.test0908

data class Keyword(
    val word : String = ""
):DiffModel{
    override val diffId: Any get() = word
}
