package com.example.aop_part3_chapter4.model

import com.google.gson.annotations.SerializedName

data class SearchBookDto(
    @SerializedName("documents") val books: List<Book>
)