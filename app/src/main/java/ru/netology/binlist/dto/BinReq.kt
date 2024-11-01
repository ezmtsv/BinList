package ru.netology.binlist.dto

import com.google.gson.annotations.SerializedName

data class BinReq (
    val id: Long,
//    @SerializedName("data")
//    val data: Data?,
    val message: String?,
    @SerializedName("result")
    val result: Int?
)