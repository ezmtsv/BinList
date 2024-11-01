package ru.netology.binlist.dto


import com.google.gson.annotations.SerializedName

data class Bank(
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("website")
    val website: String
)