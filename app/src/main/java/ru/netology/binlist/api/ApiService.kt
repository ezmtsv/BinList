package ru.netology.binlist.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.netology.binlist.dto.BinRequest

interface ApiService {
    @GET("/v1/{id}")
    suspend fun getBin(
        @Path("id") id: Long,
        @Query("api_key") api_key : String
    ): Response<BinRequest>
}