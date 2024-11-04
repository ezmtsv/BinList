package ru.netology.binlist.dto

import com.google.gson.annotations.SerializedName


data class BinRequest(
    val id: Long,
    @SerializedName("success") val
    success: Boolean? = null,
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("BIN")
    val bin: Bin? = null,
    @SerializedName("IP")
    val ip: Ip? = null
)