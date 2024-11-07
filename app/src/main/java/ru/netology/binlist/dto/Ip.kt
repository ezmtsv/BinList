package ru.netology.binlist.dto

import com.google.gson.annotations.SerializedName


data class Ip(
    @SerializedName("IP")
    val ip: String? = null,
    @SerializedName("ip_version")
    val ipVersion: Int? = null,
    @SerializedName("valid")
    val valid: Boolean? = null,
    @SerializedName("IP_BIN_match")
    val ipBinMatch: Boolean? = null,
    @SerializedName("IP_BIN_match_message")
    val ipBinMatchMessage: String? = null,
    @SerializedName("alpha2")
    val alpha2: String? = null,
    @SerializedName("flag")
    val flag: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("region")
    val region: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("latitude")
    val latitude: Double? = null,
    @SerializedName("longitude")
    val longitude: Double? = null,
    @SerializedName("zip_code")
    val zipCode: String? = null,
    @SerializedName("isp")
    val isp: String? = null,
    @SerializedName("asn")
    val asn: String? = null,
    @SerializedName("time_zone")
    val timeZone: String? = null,
    @SerializedName("current_time")
    val currentTime: String? = null,
    @SerializedName("is_proxy")
    val isProxy: Boolean? = null

)