package ru.netology.binlist.dto

import com.google.gson.annotations.SerializedName


data class Issuer(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("website")
    val website: String? = null,
    @SerializedName("phone")
    val phone: String? = null
)