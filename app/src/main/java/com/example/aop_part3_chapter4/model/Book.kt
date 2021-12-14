package com.example.aop_part3_chapter4.model

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Book(
    @SerializedName("isbn") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("authors") val authors: List<String>,
    @SerializedName("contents") val contents: String,
    @SerializedName("thumbnail") val thumbnail: String
) : Parcelable