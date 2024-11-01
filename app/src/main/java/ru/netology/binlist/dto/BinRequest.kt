package ru.netology.binlist.dto


import com.google.gson.annotations.SerializedName

data class BinRequest(
    val id: Long = 0,
    @SerializedName("data")
    val data: Data?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: Int?
)