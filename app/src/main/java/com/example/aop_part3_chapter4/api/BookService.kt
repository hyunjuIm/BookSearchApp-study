package com.example.aop_part3_chapter4.api

import com.example.aop_part3_chapter4.model.SearchBookDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BookService {

    @GET("/v3/search/book")
    fun getBooksByName(
        @Header("Authorization") apiKey: String,
        @Query("query") keyword: String
    ): Call<SearchBookDto>

}