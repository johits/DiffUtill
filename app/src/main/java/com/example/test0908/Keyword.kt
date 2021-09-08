package com.example.test0908

data class Keyword(
    val word : String = ""
):DiffModel{
    //아이템을 구분하는 고유한 값으로 비교하기
    override val diffId: Any get() = word
}
