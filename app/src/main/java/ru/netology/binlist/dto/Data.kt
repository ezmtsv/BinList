package ru.netology.binlist.dto


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("bank")
    val bank: Bank,
    @SerializedName("card")
    val card: Card,
    @SerializedName("country")
    val country: Country,
)