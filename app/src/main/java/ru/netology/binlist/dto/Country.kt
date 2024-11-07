package ru.netology.binlist.dto

import com.google.gson.annotations.SerializedName


data class Country(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("native")
    val native: String? = null,
    @SerializedName("flag")
    val flag: String? = null,
    @SerializedName("numeric")
    val numeric: String? = null,
    @SerializedName("capital")
    val capital: String? = null,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("currency_name")
    val currencyName: String? = null,
    @SerializedName("currency_symbol")
    val currencySymbol: String? = null,
    @SerializedName("region")
    val region: String? = null,
    @SerializedName("subregion")
    val subregion: String? = null,
    @SerializedName("idd")
    val idd: String? = null,
    @SerializedName("alpha2")
    val alpha2: String? = null,
    @SerializedName("alpha3")
    val alpha3: String? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("language_code")
    val languageCode: String? = null

)