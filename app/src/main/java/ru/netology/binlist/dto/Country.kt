package ru.netology.binlist.dto


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("code")
    val code: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("currency_code")
    val currencyCode: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("name")
    val name: String
)