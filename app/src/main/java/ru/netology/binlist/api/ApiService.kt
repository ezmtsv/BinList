package ru.netology.binlist.api

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query
import ru.netology.binlist.dto.BinRequest

interface ApiService {
    @POST("/")
    suspend fun getBin(
        @Query("bin") bin: Long,
        @Query("ip") ip: String,
    ): Response<BinRequest>
}