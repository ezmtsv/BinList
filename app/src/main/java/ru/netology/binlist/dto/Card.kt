package ru.netology.binlist.dto


import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("category")
    val category: String,
    @SerializedName("checkluhn")
    val checkluhn: Int,
    @SerializedName("cvvlen")
    val cvvlen: Int,
    @SerializedName("length")
    val length: Int,
    @SerializedName("scheme")
    val scheme: String,
    @SerializedName("type")
    val type: String
)