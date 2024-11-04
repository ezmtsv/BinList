package ru.netology.binlist.dto

import com.google.gson.annotations.SerializedName


data class Bin(

    @SerializedName("valid")
    val valid: Boolean? = null,
    @SerializedName("number")
    val number: Int? = null,
    @SerializedName("length")
    val length: Int? = null,
    @SerializedName("scheme")
    val scheme: String? = null,
    @SerializedName("brand")
    val brand: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("level")
    val level: String? = null,
    @SerializedName("is_commercial")
    val isCommercial: String? = null,
    @SerializedName("is_prepaid")
    val isPrepaid: String? = null,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("issuer")
    val issuer: Issuer? = null,
    @SerializedName("country")
    val country: Country? = null

)